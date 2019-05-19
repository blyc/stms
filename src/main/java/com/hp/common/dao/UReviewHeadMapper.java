package com.hp.common.dao;


import com.hp.common.model.UReviewHead;

public interface UReviewHeadMapper {
    int deleteByPrimaryKey(Long urhid);

    int insert(UReviewHead record);

    int insertSelective(UReviewHead record);

    UReviewHead selectByPrimaryKey(Long urhid);

    int updateByPrimaryKeySelective(UReviewHead record);

    int updateByPrimaryKey(UReviewHead record);
}