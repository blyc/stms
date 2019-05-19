package com.hp.headquarters.service.impl;

import com.hp.common.dao.UReviewCompanyMapper;
import com.hp.common.model.VReviewCompany;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.core.mybatis.page.Pagination;
import com.hp.headquarters.service.ReviewCompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/11/30
 * @Description:
 */
@Service
@Transactional(readOnly = true)
public class ReviewCompanyServiceImpl extends BaseMybatisDao<UReviewCompanyMapper> implements ReviewCompanyService {
    @Override
    public Pagination<VReviewCompany> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }
}
