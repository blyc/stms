package com.hp.common.dao;

import com.hp.common.model.UInterViewInfo;

import java.util.List;

public interface UInterViewInfoMapper {

    int insertSelective(UInterViewInfo record);

    List<UInterViewInfo> selectByCid(Long cid);

    Double selectComrateByCid(Long cid);

    int insertBatch(List<UInterViewInfo> uInterViewInfoList);

   int  updateUInterViewInfo(UInterViewInfo record);

}