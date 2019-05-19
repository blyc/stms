package com.hp.headquarters.service;

import com.hp.common.model.VReviewCompany;
import com.hp.core.mybatis.page.Pagination;

import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/11/30
 * @Description:
 */
public interface ReviewCompanyService {
    public Pagination<VReviewCompany> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);
}
