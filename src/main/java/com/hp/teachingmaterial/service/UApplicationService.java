package com.hp.teachingmaterial.service;

import com.hp.common.model.UApplication;
import com.hp.common.model.UFeedback;
import com.hp.common.model.Udetails;
import com.hp.core.mybatis.page.Pagination;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by slm on 2018/3/23.
 */
public interface UApplicationService {


    /*查询出所有申请表信息显示到界面*/
    public Pagination<UApplication> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);

    /*查询申请单 和书籍信息*/
    public  UApplication findById(Long id);

    /*添加反馈表*/
    public int insertSelective(UFeedback uFeedback);

    /*根据校区查询数据*/
    public  List<UApplication> selectUApplicationBycampus(UApplication uApplication);

    public int saveUApplication(UApplication uApplication);

    public int updateByPrimaryKeySelective(UApplication uApplication);

    public void findAllInfo(HttpServletResponse response);

    public  List<Udetails> selectUdetailsprice(Udetails udetails);

    public UApplication updateState(String action, Long id);

}
