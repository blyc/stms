package com.hp.common.dao;

import com.hp.common.model.UStorage;
import com.hp.common.model.UStorageALL;

import java.util.List;

public interface UStorageMapper {
    int insertUStorage(UStorage uStorage);

    List<UStorageALL> findAllUSTorage();

}
