package com.hp.common.dao;

import com.hp.common.model.UMajor;

import java.util.List;


public interface UMajorMapper {
    public List<UMajor> findAllMajor();
    public UMajor findByName(String name);
    public UMajor selectByPrimaryKey(Long id);
    public int insert(UMajor record);
    public int updateByPrimaryKeySelective(UMajor record);
    public int deleteByPrimaryKey(Long id);

}