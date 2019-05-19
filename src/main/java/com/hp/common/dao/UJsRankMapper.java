package com.hp.common.dao;


import com.hp.common.model.UJsRank;

import java.util.List;

public interface UJsRankMapper {
    int deleteByPrimaryKey(Long lid);

    int insert(UJsRank record);

    int insertSelective(UJsRank record);

    UJsRank selectByPrimaryKey(Long lid);

    int updateByPrimaryKeySelective(UJsRank record);

    int updateByPrimaryKey(UJsRank record);

    List<UJsRank> selectAllForSelect();
}