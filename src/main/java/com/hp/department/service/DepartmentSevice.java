package com.hp.department.service;

import com.hp.common.bo.KeyAndValue;
import com.hp.common.model.UDepartment;
import com.hp.core.mybatis.page.Pagination;

import java.util.List;
import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/02/11
 * @Description:
 */
public interface DepartmentSevice {
    public List<KeyAndValue> findAllDepartmentForSelect();

    public Pagination<UDepartment> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);

    public UDepartment selectByPrimaryKey(Long id);

    public UDepartment insert(UDepartment record);

    public UDepartment findByName(String name);

    public Map<String, Map<String, Long>> findByEmployeeCount();

    public int updateByPrimaryKeySelective(UDepartment record);

    public Map<String, Object> deleteDepartMentById(String ids);
}
