package com.hp.common.dao;

import com.hp.common.model.UEmployee;

import java.util.List;
import java.util.Map;


public interface UEmployeeMapper {

    public int insert(UEmployee record);

    public List<UEmployee> findAllEmployee(Map map);

    int updateByPrimaryKeySelective(UEmployee record);

    int updateByPrimaryKey(UEmployee record);

    UEmployee selectByPrimaryKey(Long eid);


}
