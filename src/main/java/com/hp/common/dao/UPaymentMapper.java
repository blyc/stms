package com.hp.common.dao;

import com.hp.common.model.UPayment;

public interface UPaymentMapper {
    int deleteByPrimaryKey(Long upid);

    int insert(UPayment record);

    int insertSelective(UPayment record);

    UPayment selectByPrimaryKey(Long upid);

    int updateByPrimaryKeySelective(UPayment record);

    int updateByPrimaryKey(UPayment record);
}