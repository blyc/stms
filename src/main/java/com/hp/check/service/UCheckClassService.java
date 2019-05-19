package com.hp.check.service;

import com.hp.common.model.UCheckClass;
import com.hp.core.mybatis.page.Pagination;

import java.util.Map;

/**
 * Created by lenovo on 2018/3/28.
 */
public interface UCheckClassService {
    //分页
    public Pagination<UCheckClass> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);
    //添加数据
    public UCheckClass insertCheckClass(UCheckClass record);
    //通过Chid更新数据
    public UCheckClass updateUCheckClassByChid(UCheckClass record);
}
