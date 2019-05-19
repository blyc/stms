package com.hp.common.dao;


import com.hp.common.model.UCscreen;

import java.util.List;
import java.util.Map;

public interface UCscreenMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UCscreen record);

    int insertSelective(UCscreen record);

    UCscreen selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UCscreen record);

    int updateByPrimaryKey(UCscreen record);

    List<UCscreen> selectByLimit(Map<String,Object> map);
}