package com.hp.company.service;

import com.hp.common.bo.KeyAndValue;
import com.hp.common.model.UCompany;
import com.hp.core.mybatis.page.Pagination;

import java.util.List;
import java.util.Map;


public interface CompanyService{
    public List<KeyAndValue> findAllCompanyForSelect();
    public Pagination<UCompany> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);
    public UCompany findByName(String name);
    public Map<String,Map<String,Long>> findByStudentCount();
    public UCompany selectByPrimaryKey(Long id);
    public UCompany insert(UCompany record);
    public int updateByPrimaryKeySelective(UCompany record);

    Map<String, Object> deleteCompanyById(String ids);

}
