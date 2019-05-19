package com.hp.common.dao;

import com.hp.common.model.UTheibrary;
import com.hp.common.model.UTheibraryALL;

import java.util.List;


public interface UTheibraryMapper {
    /*添加出库记录*/
    int insertUTheibrary(UTheibrary uTheibrary);

    List<UTheibraryALL> findAllUTheibrary();
}
