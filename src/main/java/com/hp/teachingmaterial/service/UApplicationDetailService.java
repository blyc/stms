package com.hp.teachingmaterial.service;

import com.hp.common.model.UApplicationDetail;
import com.hp.core.mybatis.page.Pagination;

import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/08/09
 * @Description:
 */
public interface UApplicationDetailService {

    public Pagination<UApplicationDetail> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);

    public UApplicationDetail saveUapplicationDetail(UApplicationDetail u);

}
