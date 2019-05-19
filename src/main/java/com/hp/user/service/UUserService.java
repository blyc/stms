package com.hp.user.service;

import com.hp.common.model.UUser;
import com.hp.core.mybatis.page.Pagination;
import com.hp.permission.bo.URoleBo;
import com.hp.permission.bo.UserRoleAllocationBo;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;

public interface UUserService {

    UUser insert(UUser record);

    int updateByPrimaryKeySelective(UUser record);

    UUser login(String email, String pswd);

    UUser findUserByEmail(String email);

    /**
     * ¸ù¾Ýid²éÑ¯
     *
     * @param id
     * @return
     */
    UUser selectByPrimaryKey(Long id);

    Pagination<UUser> findByPage(Map<String, Object> resultMap, Integer pageNo,
                                 Integer pageSize);

	Map<String, Object> deleteUserById(String ids);

	Map<String, Object> updateForbidUserById(Long id, Long status);

	Pagination<UserRoleAllocationBo> findUserAndRole(ModelMap modelMap,
			Integer pageNo, Integer pageSize);

	List<URoleBo> selectRoleByUserId(Long id);

	Map<String, Object> addRole2User(Long userId, String ids);

	Map<String, Object> deleteRoleByUserIds(String userIds);
}
