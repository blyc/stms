package com.hp.headquarters.service.impl;

import com.hp.common.dao.UMajorMapper;
import com.hp.common.dao.UStandardMapper;
import com.hp.common.model.UMajor;
import com.hp.common.model.UStandard;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.core.mybatis.page.Pagination;
import com.hp.headquarters.service.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/11/06
 * @Description:
 */
@Service
@Transactional(readOnly = true)
public class StandardServiceImpl extends BaseMybatisDao<UStandardMapper> implements StandardService {
    @Autowired
    private UStandardMapper uStandardMapper;

    @Autowired
    private UMajorMapper uMajorMapper;

    @Override
    public Pagination<UStandard> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }

    @Override
    public UStandard select(Long sid) {
        UStandard uStandard = uStandardMapper.selectByPrimaryKey(sid);
        if (uStandard != null) {
            uStandard.setStandardExam(uStandard.getStandardExam().multiply(new BigDecimal(100)));
            uStandard.setStandardAttendance(uStandard.getStandardAttendance().multiply(new BigDecimal(100)));
            uStandard.setStandardProject(uStandard.getStandardProject().multiply(new BigDecimal(100)));
            uStandard.setStandardSatisfaction(uStandard.getStandardSatisfaction().multiply(new BigDecimal(100)));
            uStandard.setStandardHead(uStandard.getStandardHead().multiply(new BigDecimal(100)));
            uStandard.setStandardSchool(uStandard.getStandardSchool().multiply(new BigDecimal(100)));
        }
        return uStandard;
    }

    @Override
    @Transactional(readOnly = false)
    public int insert(UStandard uStandard) {

        uStandard.setStandardExam(uStandard.getStandardExam().divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP));
        uStandard.setStandardAttendance(uStandard.getStandardAttendance().divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP));
        uStandard.setStandardProject(uStandard.getStandardProject().divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP));
        uStandard.setStandardSatisfaction(uStandard.getStandardSatisfaction().divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP));
        uStandard.setStandardHead(uStandard.getStandardHead().divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP));
        uStandard.setStandardSchool(uStandard.getStandardSchool().divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP));

        List<UMajor> uMajors = uMajorMapper.findAllMajor();

        for (UMajor u : uMajors) {
            if (u.getMid().equals(uStandard.getMid())) {
                uStandard.setMajorType(u.getId());
                break;
            }
        }

        String strcids = uStandard.getCids();
        String[] cids = strcids.split(",");
        for (String cid : cids) {
            uStandard.setCid(Long.parseLong(cid));
            List<UStandard> uStandardList = uStandardMapper.selectIsRepetition(uStandard);
            if (uStandardList.size() != 0) {
                return 1;
            }
        }

        for (int i = 0; i < cids.length; i++) {
            uStandard.setCid(Long.parseLong(cids[i]));
            uStandardMapper.insertSelective(uStandard);
        }
        return 0;
    }

    @Override
    @Transactional(readOnly = false)
    public int update(UStandard uStandard) {
        uStandard.setStandardExam(uStandard.getStandardExam().divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP));
        uStandard.setStandardAttendance(uStandard.getStandardAttendance().divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP));
        uStandard.setStandardProject(uStandard.getStandardProject().divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP));
        uStandard.setStandardSatisfaction(uStandard.getStandardSatisfaction().divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP));
        uStandard.setStandardHead(uStandard.getStandardHead().divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP));
        uStandard.setStandardSchool(uStandard.getStandardSchool().divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP));
        return uStandardMapper.updateByPrimaryKeySelective(uStandard);
    }


}
