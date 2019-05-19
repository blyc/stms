package com.hp.common.dao;

import com.hp.common.model.UUserRole;

import java.util.List;
import java.util.Map;

public interface UUserRoleMapper {

    int insertSelective(UUserRole record);

	int deleteByUserId(Long id);

	int deleteRoleByUserIds(Map<String, Object> resultMap);

	List<Long> findUserIdByRoleId(Long id);
}