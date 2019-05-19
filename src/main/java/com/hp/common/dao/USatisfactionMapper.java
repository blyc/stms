package com.hp.common.dao;

import com.hp.common.model.USatisfaction;

public interface USatisfactionMapper {
    int deleteByPrimaryKey(Long usid);

    int insert(USatisfaction record);

    int insertSelective(USatisfaction record);

    USatisfaction selectByPrimaryKey(Long usid);

    int updateByPrimaryKeySelective(USatisfaction record);

    int updateByPrimaryKey(USatisfaction record);
}