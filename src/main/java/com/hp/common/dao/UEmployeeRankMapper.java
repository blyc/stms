package com.hp.common.dao;


import com.hp.common.model.UEmployeeRank;

import java.util.Map;

public interface UEmployeeRankMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UEmployeeRank record);

    int insertSelective(UEmployeeRank record);

    UEmployeeRank selectByPrimaryKey(Long id);

    UEmployeeRank selectByMap(Map<String, Object> map);

    int updateByPrimaryKeySelective(UEmployeeRank record);

    int updateByPrimaryKey(UEmployeeRank record);
}