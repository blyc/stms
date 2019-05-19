package com.hp.common.dao;


import com.hp.common.model.UReviewExameDetail;

import java.util.List;

public interface UReviewExameDetailMapper {
    int deleteByPrimaryKey(Long redid);

    int insert(UReviewExameDetail record);

    int insertSelective(UReviewExameDetail record);

    UReviewExameDetail selectByPrimaryKey(Long redid);

    List<UReviewExameDetail> selectByReid(Long reid);

    int updateByPrimaryKeySelective(UReviewExameDetail record);

    int updateByPrimaryKey(UReviewExameDetail record);
}