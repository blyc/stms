package com.hp.position.service;

import com.hp.common.bo.KeyAndValue;
import com.hp.common.model.UPosition;
import com.hp.core.mybatis.page.Pagination;

import java.util.List;
import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/02/11
 * @Description:
 */
public interface PositionService {
    public List<KeyAndValue> findAllPositionForSelect();

    public Pagination<UPosition> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);

    public UPosition insert(UPosition record);

    public UPosition findByName(String name);

    public UPosition selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(UPosition record);

    public Map<String, Object> deleteUPositionById(String ids);
}
