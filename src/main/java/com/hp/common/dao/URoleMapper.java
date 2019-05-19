package com.hp.common.dao;

import com.hp.common.model.URole;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface URoleMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(URole record);

    Set<String> findRoleByUserId(Long id);

	List<URole> findNowAllPermission(Map<String, Object> map);
	
	void initData();
}