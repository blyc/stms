package com.hp.student.service;

import com.hp.common.model.UPayment;
import com.hp.core.mybatis.page.Pagination;

import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/08/10
 * @Description:
 */
public interface PaymentService {
    public Pagination<UPayment> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);
}
