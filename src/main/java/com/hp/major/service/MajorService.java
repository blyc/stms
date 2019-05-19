package com.hp.major.service;

import com.hp.common.bo.KeyAndValue;
import com.hp.common.model.UMajor;
import com.hp.core.mybatis.page.Pagination;

import java.util.List;
import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/02/11
 * @Description:
 */
public interface MajorService{
    public List<KeyAndValue> findAllMajorForSelect();
    public Pagination<UMajor> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);
    public UMajor findByName(String name);
    public UMajor selectByPrimaryKey(Long id);
    public UMajor insert(UMajor record);
    public int updateByPrimaryKeySelective(UMajor record);

    Map<String, Object> deleteMajorById(String ids);
}
