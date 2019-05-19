package com.hp.summary.service;

import com.hp.common.model.VSummaryResult;
import com.hp.core.mybatis.page.Pagination;

import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/11/21
 * @Description:
 */
public interface SummaryResultService {
    public Pagination<VSummaryResult> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);

}
