package com.hp.common.dao;


import com.hp.common.model.UApplicationDetail;

public interface UApplicationDetailMapper {
    int deleteByPrimaryKey(Long adid);

    int insert(UApplicationDetail record);

    int insertSelective(UApplicationDetail record);

    UApplicationDetail selectByPrimaryKey(Long adid);

    int updateByPrimaryKeySelective(UApplicationDetail record);

    int updateByPrimaryKey(UApplicationDetail record);
}