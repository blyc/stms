package com.hp.headquarters.service;

import com.hp.common.model.UStandard;
import com.hp.core.mybatis.page.Pagination;

import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/11/06
 * @Description:
 */
public interface StandardService {
    public Pagination<UStandard> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);

    public UStandard select(Long sid);

    public int insert(UStandard uStandard);

    public int update(UStandard uStandard);

}
