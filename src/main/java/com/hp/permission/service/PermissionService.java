package com.hp.permission.service;

import com.hp.common.model.UPermission;
import com.hp.core.mybatis.page.Pagination;
import com.hp.permission.bo.UPermissionBo;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PermissionService {

	UPermission insertSelective(UPermission record);

	Map<String, Object> deletePermissionById(String ids);

	Pagination<UPermission> findPage(Map<String,Object> resultMap, Integer pageNo,
			Integer pageSize);
	List<UPermissionBo> selectPermissionById(Long id);

	Map<String, Object> addPermission2Role(Long roleId,String ids);

	Map<String, Object> deleteByRids(String roleIds);
	//根据用户ID查询权限（permission），放入到Authorization里。
	Set<String> findPermissionByUserId(Long userId);
}
