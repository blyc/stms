package com.hp.stock.service;

import com.hp.common.model.UStorage;
import com.hp.core.mybatis.page.Pagination;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by slm on 2018-04-15.
 */
public interface UStorageService {
    public Pagination<UStorage> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);

    public void findAllInfo(HttpServletResponse response);
}
