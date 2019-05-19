package com.hp.common.dao;


import com.hp.common.model.UCprizes;

import java.util.List;

public interface UCprizesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UCprizes record);

    int insertSelective(UCprizes record);

    UCprizes selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UCprizes record);

    int updateByPrimaryKey(UCprizes record);

    List<UCprizes> selectPrizes();
}