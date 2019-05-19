package com.hp.common.dao;

import com.hp.common.model.UUser;
import com.hp.permission.bo.URoleBo;

import java.util.List;
import java.util.Map;

public interface UUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UUser record);

    UUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UUser record);

    UUser login(Map<String, Object> map);

	UUser findUserByEmail(String email);

	List<URoleBo> selectRoleByUserId(Long id);

}