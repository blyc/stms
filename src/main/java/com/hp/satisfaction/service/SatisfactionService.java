package com.hp.satisfaction.service;

import com.hp.common.model.USatisfaction;
import com.hp.core.mybatis.page.Pagination;

import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/08/10
 * @Description:
 */
public interface SatisfactionService {
    /*查询出所有申请表信息显示到界面*/
    public Pagination<USatisfaction> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);

    public USatisfaction addUSatisfaction(USatisfaction u);

    public Map<String, Object> delete(String ids);


}
