package com.hp.common.dao;

import com.hp.common.model.UDepartment;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;


public interface UDepartmentMapper {
    public List<UDepartment> findAllDepartMent();

    public UDepartment findByName(String name);

    @MapKey("name")
    public Map<String, Map<String, Long>> findByEmployeeCount();

    public UDepartment selectByPrimaryKey(Long id);

    public int insert(UDepartment record);

    public int updateByPrimaryKeySelective(UDepartment record);

    public int deleteByPrimaryKey(Long id);
}
