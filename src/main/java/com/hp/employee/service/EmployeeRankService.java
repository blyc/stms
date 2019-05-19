package com.hp.employee.service;

import com.hp.common.bo.KeyAndValue;
import com.hp.common.model.UJsRank;
import com.hp.core.mybatis.page.Pagination;

import java.util.List;
import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/11/28
 * @Description:
 */
public interface EmployeeRankService {
    /*查询出所有申请表信息显示到界面*/
    public Pagination<UJsRank> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);

    public UJsRank addUJsRank(UJsRank u);

    public List<KeyAndValue> findAllUJsRankForSelect();

    public int update(UJsRank record);

    public UJsRank findByPk(Long id);

}
