package com.hp.uclass.service;

import com.hp.common.model.UClassHour;
import com.hp.core.mybatis.page.Pagination;

import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/11/23
 * @Description:
 */
public interface UClassHourService {
    /*查询出所有申请表信息显示到界面*/
    public Pagination<UClassHour> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);

    public UClassHour addUClassHour(UClassHour u);

    public int update(UClassHour record);

    public UClassHour findByPk(Long id);

    public Map<String, Object> delete(String ids);

}
