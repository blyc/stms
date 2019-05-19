package com.hp.common.dao;


import com.hp.common.model.UReviewCompany;

public interface UReviewCompanyMapper {
    int deleteByPrimaryKey(Long urcid);

    int insert(UReviewCompany record);

    int insertSelective(UReviewCompany record);

    UReviewCompany selectByPrimaryKey(UReviewCompany record);

    int updateByPrimaryKeySelective(UReviewCompany record);

    int updateByPrimaryKey(UReviewCompany record);
}