package com.hp.headquarters.service;

import com.hp.common.model.UReviewProjectDetail;

import java.util.List;
import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/11/12
 * @Description:
 */
public interface ReviewProjectService {
    public int update(Map<String, Object> map);

    public List<UReviewProjectDetail> select(Long rpid);
}
