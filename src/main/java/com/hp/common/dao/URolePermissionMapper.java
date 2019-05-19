package com.hp.common.dao;

import com.hp.common.model.URolePermission;

import java.util.List;
import java.util.Map;

public interface URolePermissionMapper {

    int insertSelective(URolePermission record);

	List<URolePermission> findRolePermissionByPid(Long id);

    int deleteByRid(Long id);

    int deleteByRids(Map<String,Object> resultMap);
}