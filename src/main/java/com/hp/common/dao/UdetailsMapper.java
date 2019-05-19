package com.hp.common.dao;

import com.hp.common.model.UMajor;
import com.hp.common.model.Udetails;

import java.util.List;


public interface UdetailsMapper {
     List<Udetails>selectUdetails(Udetails udetails);
    int updateUdetails(Udetails udetails);
    List<Udetails> selectUdetailsDname(UMajor uMajor);
    int updateUdetailsCk(Udetails udetails);
    /*添加库存*/
    int insertUdetails(Udetails udetails);

    List<Udetails> selectUdetailsMid(Udetails udetails);
    List<Udetails> selectUdetailsdid(Udetails udetails);
}
