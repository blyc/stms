package com.hp.interview.service;

import com.hp.common.model.UInterViewInfo;
import com.hp.core.mybatis.page.Pagination;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2018/4/12.
 */
public interface InterViewInfoService {
    public Pagination<UInterViewInfo> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);

    public List<UInterViewInfo> selectByCid(Long cid);

    public Double selectComrateByCid(Long cid);

    public UInterViewInfo insertInterViewInfo(UInterViewInfo record);


}
