package com.hp.exam.service.impl;

import com.hp.common.dao.UClassExamMapper;
import com.hp.common.dao.UStudentExamMapper;
import com.hp.common.model.UClassExam;
import com.hp.common.utils.LoggerUtils;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.core.mybatis.page.Pagination;
import com.hp.exam.service.ClassExamService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 晏利花 on 2018/3/20.
 */
@Service
@Transactional(readOnly = true)
public class ClassExamServiceImpl extends BaseMybatisDao<UClassExamMapper> implements ClassExamService {
    @Autowired
    private UClassExamMapper uClassExamMapper;
    @Autowired
    private UStudentExamMapper uStudentExamMapper;

    @Override
    public Pagination<UClassExam> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }

    @Override
    public Pagination<UClassExam> findReviewByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage("findReviewAll","findReviewCount",resultMap, pageNo, pageSize);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateExameventByPrimaryKey(UClassExam record) {
        return uClassExamMapper.updateExameventByPrimaryKey(record);
    }

    @Override
    @Transactional(readOnly = false)
    public Map<String, Object> delete(String ids) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            int count = 0;
            String[] idArray = new String[]{};
            if (StringUtils.contains(ids, ",")) {
                idArray = ids.split(",");
            } else {
                idArray = new String[]{ids};
            }

            for (String id : idArray) {
                uStudentExamMapper.deleteByCeid(new Long(id));
                count += uClassExamMapper.deleteByPrimaryKey(new Long(id));
            }
            resultMap.put("status", 200);
            resultMap.put("count", count);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "根据IDS删除项目出现错误，ids[%s]", ids);
            resultMap.put("status", 500);
            resultMap.put("message", "删除出现错误，请刷新后再试！");
        }
        return resultMap;
    }


}
