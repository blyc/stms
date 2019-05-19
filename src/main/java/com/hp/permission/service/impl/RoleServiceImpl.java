package com.hp.permission.service.impl;

import com.hp.common.dao.URoleMapper;
import com.hp.common.model.URole;
import com.hp.common.utils.LoggerUtils;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.core.mybatis.page.Pagination;
import com.hp.core.shiro.token.manager.TokenManager;
import com.hp.permission.bo.RolePermissionAllocationBo;
import com.hp.permission.service.RoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Transactional(readOnly = true)
@SuppressWarnings("unchecked")
public class RoleServiceImpl extends BaseMybatisDao<URoleMapper> implements RoleService {

	@Autowired
	URoleMapper roleMapper;

	@Transactional(readOnly = false)
	public int deleteByPrimaryKey(Long id) {
		return roleMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional(readOnly = false)
	public int insertSelective(URole record) {
		return roleMapper.insertSelective(record);
	}


	@Override
	public Pagination<URole> findPage(Map<String, Object> resultMap,
			Integer pageNo, Integer pageSize) {
		return super.findPage(resultMap, pageNo, pageSize);
	}
	@Override
	public Pagination<RolePermissionAllocationBo> findRoleAndPermissionPage(
			Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
		return super.findPage("findRoleAndPermission", "findCount", resultMap, pageNo, pageSize);
	}
	@Override
	@Transactional(readOnly = false)
	public Map<String, Object> deleteRoleById(String ids) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			int count=0;
			String resultMsg = "删除成功。";
			String[] idArray = new String[]{};
			if(StringUtils.contains(ids, ",")){
				idArray = ids.split(",");
			}else{
				idArray = new String[]{ids};
			}
			
			c:for (String idx : idArray) {
				Long id = new Long(idx);
				if(new Long(1).equals(id)){
					resultMsg = "操作成功，But'系统管理员不能删除。";
					continue c;
				}else{
					count+=this.deleteByPrimaryKey(id);
				}
			}
			resultMap.put("status", 200);
			resultMap.put("count", count);
			resultMap.put("resultMsg", resultMsg);
		} catch (Exception e) {
			LoggerUtils.fmtError(getClass(), e, "根据IDS删除用户出现错误，ids[%s]", ids);
			resultMap.put("status", 500);
			resultMap.put("message", "删除出现错误，请刷新后再试！");
		}
		return resultMap;
	}

	@Override
	public Set<String> findRoleByUserId(Long userId) {
		return roleMapper.findRoleByUserId(userId);
	}

	@Override
	public List<URole> findNowAllPermission() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", TokenManager.getUserId());
		return roleMapper.findNowAllPermission(map);
	}
	/**
	 * 每20分钟执行一次
	 */
	@Override
	@Transactional(readOnly = false)
	public void initData() {
		roleMapper.initData();
	}
	
}
