package com.hp.headquarters.service;

import com.hp.common.model.UReviewCompany;
import com.hp.common.model.UReviewExameDetail;
import com.hp.common.model.UReviewHead;

import java.util.List;
import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/11/09
 * @Description:
 */
public interface ReviewExamService {

    public int update(Map<String, Object> map);

    public List<UReviewExameDetail> select(Long reid);

    public int addReviewCompany(UReviewCompany record);

    public int updateReviewCompany(UReviewCompany record);

    public UReviewCompany getReviewCompany(String reviewTime,Long eid);

    public int addReviewHead(UReviewHead record);

    public int updateReviewHead(UReviewHead record);

    public UReviewHead getReviewHead(Long urhid);
}
