package com.hp.common.dao;

import com.hp.common.model.UStandard;

import java.util.List;

public interface UStandardMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(UStandard record);

    int insertSelective(UStandard record);

    UStandard selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(UStandard record);

    int updateByPrimaryKey(UStandard record);

    List<UStandard> selectIsRepetition(UStandard record);
}