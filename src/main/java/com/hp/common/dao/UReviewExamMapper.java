package com.hp.common.dao;


import com.hp.common.model.UReviewExam;

public interface UReviewExamMapper {
    int deleteByPrimaryKey(Long reid);

    int insert(UReviewExam record);

    int insertSelective(UReviewExam record);

    UReviewExam selectByPrimaryKey(Long reid);

    int updateByPrimaryKeySelective(UReviewExam record);

    int updateByPrimaryKey(UReviewExam record);
}