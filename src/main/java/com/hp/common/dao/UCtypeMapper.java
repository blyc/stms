package com.hp.common.dao;


import com.hp.common.model.UCtype;

public interface UCtypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UCtype record);

    int insertSelective(UCtype record);

    UCtype selectByPrimaryKey(Long id);

    UCtype selectByTname(UCtype record);

    int updateByPrimaryKeySelective(UCtype record);

    int updateByPrimaryKey(UCtype record);

    int updateTcountByPrimaryKey(UCtype record);


}