package com.hp.interview.service;

import com.hp.common.model.UInterView;
import com.hp.core.mybatis.page.Pagination;

import java.util.Map;

/**
 * Created by lenovo on 2018/4/12.
 */
public interface InterViewService {
    public Pagination<UInterView> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);

    public UInterView insertInterView(UInterView record);


}
