package com.hp.headquarters.service.impl;

import com.hp.common.dao.*;
import com.hp.common.model.*;
import com.hp.common.utils.DateUtil;
import com.hp.core.shiro.token.manager.TokenManager;
import com.hp.headquarters.bo.UReviewExameDetailbo;
import com.hp.headquarters.service.ReviewExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/11/09
 * @Description:
 */
@Service
@Transactional(readOnly = true)
public class ReviewExamServiceImpl implements ReviewExamService {
    @Autowired
    private UStudentExamMapper uStudentExamMapper;

    @Autowired
    private UClassExamMapper uClassExamMapper;

    @Autowired
    private UReviewExamMapper uReviewExamMapper;

    @Autowired
    private UReviewExameDetailMapper uReviewExameDetailMapper;

    @Autowired
    private UReviewCompanyMapper uReviewCompanyMapper;

    private UReviewHeadMapper uReviewHeadMapper;

    @Override
    @Transactional(readOnly = false)
    public int update(Map<String, Object> map) {
        List<UReviewExameDetailbo> uReviewExameDetailbos = (List<UReviewExameDetailbo>) map.get("uReviewExameDetailbos");
        Long ceid = (Long) map.get("ceid");

        UReviewExam uReviewExam = new UReviewExam();
        uReviewExam.setCeid(ceid);
        uReviewExamMapper.insertSelective(uReviewExam);

        Long reid = uReviewExam.getReid();

        List<UStudentExam> uStudentExams = uStudentExamMapper.selectExamDetails(ceid);
        UClassExam uClassExam = uClassExamMapper.selectByPrimaryKey(ceid);

        Double rateStandard = null;

        if(uClassExam!=null){
            rateStandard = uClassExam.getRateStandard();
        }

        if(rateStandard==null){
            rateStandard = 60D;
        }


        Map<Long, UReviewExameDetailbo> uReviewMap = new HashMap<Long, UReviewExameDetailbo>();

        for (UReviewExameDetailbo u : uReviewExameDetailbos) {
            uReviewMap.put(u.getUseid(), u);
        }

        //抽查个数
        int reviewCount = uReviewExameDetailbos.size();
        //检查合格个数
        int reviewokcount = 0;
        //班级考试人数
        int studentcount = uStudentExams.size();
        //考试合格个数
        int gradeokcount = 0;

        for (UStudentExam us : uStudentExams) {
            if (uReviewMap != null) {
                UReviewExameDetailbo ur = uReviewMap.get(us.getUseid());
                if (ur != null) {
                    if (ur.getReviewOk() == true) {
                        reviewokcount++;
                    }

                    if (ur.getReviewGrade().doubleValue() >= rateStandard) {
                        gradeokcount++;
                    }
                    UReviewExameDetail detail = new UReviewExameDetail();
                    detail.setCid(us.getCid());
                    detail.setCcid(us.getCcid());
                    detail.setSid(us.getSid());
                    detail.setReviewOk(ur.getReviewOk());
                    detail.setReviewGrade(ur.getReviewGrade());
                    detail.setRemake(ur.getRemake());
                    detail.setUseid(ur.getUseid());
                    detail.setReid(uReviewExam.getReid());
                    uReviewExameDetailMapper.insertSelective(detail);
                } else {
                    if (us.getExamgrade() >= rateStandard) {
                        gradeokcount++;
                    }
                }
            } else {
                if (us.getExamgrade() >= rateStandard) {
                    gradeokcount++;
                }
            }
        }

        uReviewExam.setCid(uClassExam.getCid());
        uReviewExam.setCcid(uClassExam.getCcid());
        uReviewExam.setReviewTime(uClassExam.getExamtime());
        uReviewExam.setReviewNum((long) reviewCount);
        uReviewExam.setNoqualifiedNum((long) (reviewCount - reviewokcount));
        uReviewExam.setQualifiedNum((long) reviewokcount);
        uReviewExam.setReviewRate(new BigDecimal(reviewokcount).divide(new BigDecimal(reviewCount), 4, BigDecimal.ROUND_HALF_UP));
        uReviewExam.setQualified(new BigDecimal(gradeokcount).divide(new BigDecimal(studentcount), 4, BigDecimal.ROUND_HALF_UP));
        return uReviewExamMapper.updateByPrimaryKeySelective(uReviewExam);
    }

    @Override
    public List<UReviewExameDetail> select(Long reid) {
        return uReviewExameDetailMapper.selectByReid(reid);
    }

    @Override
    @Transactional(readOnly = false)
    public int addReviewCompany(UReviewCompany uReviewCompany) {
        UUser uUser= TokenManager.getToken();
        uReviewCompany.setCid(uUser.getCid());
        uReviewCompany.setQualified(uReviewCompany.getQualified().divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP));
        uReviewCompany.setReviewTime(DateUtil.getCurrentDateTime());
        uReviewCompany.setHeadflg(0L);
        return uReviewCompanyMapper.insertSelective(uReviewCompany);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateReviewCompany(UReviewCompany record) {
        record.setQualified(record.getQualified().divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP));
        return uReviewCompanyMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public UReviewCompany getReviewCompany(String reviewTime, Long eid) {
        UReviewCompany uReviewCompany = new UReviewCompany();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        uReviewCompany.setEid(eid);
        try {
            uReviewCompany.setReviewTime(sdf.parse(reviewTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        uReviewCompany = uReviewCompanyMapper.selectByPrimaryKey(uReviewCompany);
        if (uReviewCompany != null) {
            uReviewCompany.setQualified(uReviewCompany.getQualified().multiply(new BigDecimal(100)));
        }
        return uReviewCompany;
    }

    @Override
    @Transactional(readOnly = false)
    public int addReviewHead(UReviewHead record) {
        return 0;
    }

    @Override
    @Transactional(readOnly = false)
    public int updateReviewHead(UReviewHead record) {
        return 0;
    }

    @Override
    public UReviewHead getReviewHead(Long urhid) {
        return uReviewHeadMapper.selectByPrimaryKey(urhid);
    }
}
