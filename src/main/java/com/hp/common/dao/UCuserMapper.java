package com.hp.common.dao;


import com.hp.common.model.UCuser;

import java.util.List;

public interface UCuserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UCuser record);

    int insertSelective(UCuser record);

    UCuser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UCuser record);

    int updateByPrimaryKey(UCuser record);

    List<UCuser> selectWCJ();

    List<UCuser> selectHJ(Long flg);
}