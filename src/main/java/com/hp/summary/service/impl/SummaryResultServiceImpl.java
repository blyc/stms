package com.hp.summary.service.impl;

import com.hp.common.dao.VSummaryResultMapper;
import com.hp.common.model.VSummaryResult;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.core.mybatis.page.Pagination;
import com.hp.summary.service.SummaryResultService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/11/21
 * @Description:
 */
@Service
@Transactional(readOnly = true)
public class SummaryResultServiceImpl extends BaseMybatisDao<VSummaryResultMapper> implements SummaryResultService {
    @Override
    public Pagination<VSummaryResult> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }
}
