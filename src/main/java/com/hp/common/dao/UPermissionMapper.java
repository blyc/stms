package com.hp.common.dao;

import com.hp.common.model.UPermission;
import com.hp.permission.bo.UPermissionBo;

import java.util.List;
import java.util.Set;

public interface UPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(UPermission record);

    List<UPermissionBo> selectPermissionById(Long id);
	//根据用户ID获取权限的Set集合
	Set<String> findPermissionByUserId(Long id);
}