package com.hp.exam.service.impl;

import com.hp.common.dao.VSummaryExamMapper;
import com.hp.common.model.VSummaryExam;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.core.mybatis.page.Pagination;
import com.hp.exam.service.VSummaryExamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/11/01
 * @Description:
 */
@Service
@Transactional(readOnly = true)
public class VSummaryExamServiceImpl extends BaseMybatisDao<VSummaryExamMapper> implements VSummaryExamService {
    @Override
    public Pagination<VSummaryExam> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }
}
