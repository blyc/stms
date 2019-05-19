package com.hp.common.dao;


import com.hp.common.model.UReviewProjectDetail;

import java.util.List;

public interface UReviewProjectDetailMapper {
    int deleteByPrimaryKey(Long rpdid);

    int insert(UReviewProjectDetail record);

    int insertSelective(UReviewProjectDetail record);

    UReviewProjectDetail selectByPrimaryKey(Long rpdid);

    int updateByPrimaryKeySelective(UReviewProjectDetail record);

    int updateByPrimaryKey(UReviewProjectDetail record);

    List<UReviewProjectDetail> selectByRpid(Long rpid);
}