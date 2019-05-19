package com.hp.project.service;

import com.hp.common.model.UClassProject;
import com.hp.core.mybatis.page.Pagination;

import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/03/29
 * @Description:
 */
public interface ClassProjectService {
    public Pagination<UClassProject> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);

    public Pagination<UClassProject> findReviewByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);

    public Map<String, Object> delete(String ids);
}
