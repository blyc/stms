package com.hp.stock.service;

import com.hp.common.bo.KeyAndValue;
import com.hp.common.model.UStorage;
import com.hp.common.model.Udetails;
import com.hp.core.mybatis.page.Pagination;

import java.util.List;
import java.util.Map;

/**
 * Created by slm on 2018-04-15.
 */
public interface UdetailsService {
    public Pagination<Udetails> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);
    List<KeyAndValue> selectUdetails();
    int insertUStorage(UStorage uStorage,Udetails udetails);
}
