package com.hp.teachingmaterial.service;

import com.hp.common.bo.KeyAndValue;
import com.hp.common.model.UClass;
import com.hp.common.model.UFeedback;
import com.hp.core.mybatis.page.Pagination;

import java.util.List;
import java.util.Map;

/**
 * Created by slm on 2018/3/29.
 */
public interface UFeedbackService {

    /*查询出所有申请表信息显示到界面*/
    public Pagination<UFeedback> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);
    /*查询所有全部校区*/
    List<KeyAndValue>selectUcompanyALLName();

    /*查询所有全部专业*/
    List<KeyAndValue>selectALLUmajor();
    /*查询所有全部班级*/
    List<KeyAndValue>selectALLUClassname();

    //查询班级人数
    List<UClass>selectALLUClassnum(UClass uClass);

    List<UFeedback> selectUFeedbackANdaid(UFeedback uFeedback);

}
