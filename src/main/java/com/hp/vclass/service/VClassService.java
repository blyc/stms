package com.hp.vclass.service;

import com.hp.common.model.VClass;
import com.hp.core.mybatis.page.Pagination;

import java.util.Map;

/**
 * Created by Administrator on 2018/2/25 0025.
 */
public interface VClassService {

    //分页查询
    public Pagination<VClass> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);

    //查询
    public VClass findVClassByCcid(Long ccid);

    public VClass SelectVClassByccidService(Long ccid);

}
