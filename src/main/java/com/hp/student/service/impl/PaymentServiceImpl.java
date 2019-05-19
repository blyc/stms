package com.hp.student.service.impl;

import com.hp.common.dao.UPaymentMapper;
import com.hp.common.model.UPayment;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.core.mybatis.page.Pagination;
import com.hp.student.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/08/10
 * @Description:
 */
@Service
@Transactional(readOnly = true)
public class PaymentServiceImpl extends BaseMybatisDao<UPaymentMapper> implements PaymentService {
    @Override
    public Pagination<UPayment> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }

}
