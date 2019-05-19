package com.hp.common.dao;

import com.hp.common.model.UClassAttendance;

import java.util.Map;

public interface UClassAttendanceMapper {

    int insertSelective(UClassAttendance record);

    UClassAttendance selectByPrimaryKey(Long caid);

    UClassAttendance selectClassAttendaceByDate(Map map);

    int updateByPrimaryKeySelective(UClassAttendance record);

}