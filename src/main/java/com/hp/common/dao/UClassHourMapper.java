package com.hp.common.dao;


import com.hp.common.model.UClassHour;

public interface UClassHourMapper {
    int deleteByPrimaryKey(Long uchid);

    int insert(UClassHour record);

    int insertSelective(UClassHour record);

    UClassHour selectByPrimaryKey(Long uchid);

    int updateByPrimaryKeySelective(UClassHour record);

    int updateByPrimaryKey(UClassHour record);
}