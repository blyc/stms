package com.hp.common.dao;


import com.hp.common.model.UReviewProject;

public interface UReviewProjectMapper {
    int deleteByPrimaryKey(Long rpid);

    int insert(UReviewProject record);

    int insertSelective(UReviewProject record);

    UReviewProject selectByPrimaryKey(Long rpid);

    int updateByPrimaryKeySelective(UReviewProject record);

    int updateByPrimaryKey(UReviewProject record);
}