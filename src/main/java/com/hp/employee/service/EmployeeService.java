package com.hp.employee.service;

import com.hp.common.bo.KeyAndValue;
import com.hp.common.model.UEmployee;
import com.hp.core.mybatis.page.Pagination;

import java.util.List;
import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/02/10
 * @Description:
 */
public interface EmployeeService {
    public Pagination<UEmployee> findByPage(Map<String, Object> resultMap, Integer pageNo,
                                     Integer pageSize);

    public UEmployee insert(UEmployee record);

    public List<KeyAndValue> findAllEmployeeForSelect();

    public UEmployee findByPk(Long id);

    public int update(UEmployee record);

    public List<UEmployee> findByTel(String tel);

    public Map<String, Object> deleteEmployeeById(String ids);

}
