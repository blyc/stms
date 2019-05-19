package com.hp.permission.service;

import com.hp.common.model.URole;
import com.hp.core.mybatis.page.Pagination;
import com.hp.permission.bo.RolePermissionAllocationBo;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RoleService {

	int insertSelective(URole record);

	Pagination<URole> findPage(Map<String, Object> resultMap, Integer pageNo,
			Integer pageSize);

	Map<String, Object> deleteRoleById(String ids);

	Pagination<RolePermissionAllocationBo> findRoleAndPermissionPage(
			Map<String, Object> resultMap, Integer pageNo, Integer pageSize);
	//根据用户ID查询角色（role），放入到Authorization里。
	Set<String> findRoleByUserId(Long userId);

	List<URole> findNowAllPermission();
	//初始化数据
	void initData();
}
