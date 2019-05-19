package com.hp.exam.service;

import com.hp.common.model.VSummaryExam;
import com.hp.core.mybatis.page.Pagination;

import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/11/01
 * @Description:
 */
public interface VSummaryExamService {
    public Pagination<VSummaryExam> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);

}
