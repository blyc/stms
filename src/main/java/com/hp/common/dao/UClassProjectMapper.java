package com.hp.common.dao;

import com.hp.common.model.UClassProject;

public interface UClassProjectMapper {

    /*增加项目申请*/
    int insert(UClassProject record);
    /*处理表示位*/
    int updateFlgByPrimaryKey(Long ceid);
    /*根据项目cpid修改项目合格率*/
    int updateProjectGaradeBycpid(UClassProject uClassProject);

    UClassProject selectByPrimaryKey(Long cpid);

    int deleteByPrimaryKey(Long cpid);


}