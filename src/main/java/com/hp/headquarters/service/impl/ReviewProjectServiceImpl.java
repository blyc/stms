package com.hp.headquarters.service.impl;

import com.hp.common.dao.UClassProjectMapper;
import com.hp.common.dao.UReviewProjectDetailMapper;
import com.hp.common.dao.UReviewProjectMapper;
import com.hp.common.dao.UStudentProjectMapper;
import com.hp.common.model.UClassProject;
import com.hp.common.model.UReviewProject;
import com.hp.common.model.UReviewProjectDetail;
import com.hp.common.model.UStudentProject;
import com.hp.headquarters.bo.UReviewProjectDetailbo;
import com.hp.headquarters.service.ReviewProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/11/12
 * @Description:
 */
@Service
@Transactional(readOnly = true)
public class ReviewProjectServiceImpl implements ReviewProjectService {
    @Autowired
    private UReviewProjectMapper uReviewProjectMapper;

    @Autowired
    private UStudentProjectMapper uStudentProjectMapper;

    @Autowired
    private UClassProjectMapper uClassProjectMapper;

    @Autowired
    private UReviewProjectDetailMapper uReviewProjectDetailMapper;

    @Override
    @Transactional(readOnly = false)
    public int update(Map<String, Object> map) {
        List<UReviewProjectDetailbo> uReviewProjectDetailbos = (List<UReviewProjectDetailbo>) map.get("uReviewProjectDetailbos");
        Long cpid = (Long) map.get("cpid");

        UReviewProject uReviewProject = new UReviewProject();
        uReviewProject.setCpid(cpid);
        uReviewProjectMapper.insertSelective(uReviewProject);

        Long rpid = uReviewProject.getRpid();
        List<UStudentProject> uStudentProjects = uStudentProjectMapper.selectProjectDetails(cpid);

        UClassProject uClassProject = uClassProjectMapper.selectByPrimaryKey(cpid);

        Map<Long, UReviewProjectDetailbo> uReviewMap = new HashMap<Long, UReviewProjectDetailbo>();

        for (UReviewProjectDetailbo u : uReviewProjectDetailbos) {
            uReviewMap.put(u.getSpid(), u);
        }

        //抽查个数
        int reviewCount = uReviewProjectDetailbos.size();
        //检查合格个数
        int reviewokcount = 0;
        //班级项目提交人数
        // int studentcount = uStudentProjects.size();
        Long studentcount=0L;
        //考试合格个数
        Long gradeokcount = 0L;

        for (UStudentProject us : uStudentProjects) {
            if (uReviewMap != null) {
                UReviewProjectDetailbo ur = uReviewMap.get(us.getSpid());
                if (ur != null) {
                    if (ur.getReviewOk() == true) {
                        reviewokcount++;
                    }

                    if (ur.getReviewOk() == true) {
                        gradeokcount++;
                    }

                    UReviewProjectDetail detail = new UReviewProjectDetail();
                    detail.setCid(us.getCid());
                    detail.setCcid(us.getCcid());
                    detail.setSid(us.getSid());
                    detail.setReviewOk(ur.getReviewOk());
                    detail.setReviewGrade(ur.getReviewGrade());
                    detail.setRemake(ur.getRemake());
                    detail.setSpid(ur.getSpid());
                    detail.setRpid(rpid);
                    uReviewProjectDetailMapper.insertSelective(detail);
                } else {
                    if (us.getVpass() == 1) {
                        gradeokcount++;
                    }
                }
            } else {
                if (us.getVpass() == 1) {
                    gradeokcount++;
                }
            }

            if (us.getVsubmit() != -1) {
                studentcount = studentcount + us.getVsubmit();
            }
        }


        uReviewProject.setCid(uClassProject.getCid());
        uReviewProject.setCcid(uClassProject.getCcid());
        uReviewProject.setReviewTime(uClassProject.getStartTime());
        uReviewProject.setReviewNum((long) reviewCount);
        uReviewProject.setNoqualifiedNum((long) (reviewCount - reviewokcount));
        uReviewProject.setQualifiedNum((long) reviewokcount);
        if(reviewCount!=0){
            uReviewProject.setReviewRate(new BigDecimal(reviewokcount).divide(new BigDecimal(reviewCount), 4, BigDecimal.ROUND_HALF_UP));
        }
        if(studentcount!=0){
            uReviewProject.setQualified(new BigDecimal(gradeokcount).divide(new BigDecimal(studentcount), 4, BigDecimal.ROUND_HALF_UP));
        }


        return uReviewProjectMapper.updateByPrimaryKeySelective(uReviewProject);
    }

    @Override
    public List<UReviewProjectDetail> select(Long rpid) {
        return uReviewProjectDetailMapper.selectByRpid(rpid);
    }
}
