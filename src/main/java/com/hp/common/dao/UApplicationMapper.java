package com.hp.common.dao;

import com.hp.common.model.UApplication;

import java.util.List;

public interface UApplicationMapper {


    UApplication selectByPrimaryKey(Long aid);

    /*根据校区查询数据*/
    List<UApplication> selectUApplicationBycampus(UApplication uApplication);

    int saveUApplication(UApplication uApplication);

    int updateByPrimaryKeySelective(UApplication uApplication);

    List<UApplication> selectALL();
}
