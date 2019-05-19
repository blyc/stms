package com.hp.teachingmaterial.service.impl;

import com.hp.common.bo.KeyAndValue;
import com.hp.common.dao.*;
import com.hp.common.model.UClass;
import com.hp.common.model.UCompany;
import com.hp.common.model.UFeedback;
import com.hp.common.model.Udetails;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.core.mybatis.page.Pagination;
import com.hp.teachingmaterial.service.UFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by slm on 2018/3/29.
 */
@Service
public class UFeedbackServiceimpl extends BaseMybatisDao<UFeedbackMapper> implements UFeedbackService {
    @Autowired
    private UFeedbackMapper uFeedbackMapper;
    @Autowired
    private UCompanyMapper uCompanyMapper;
    @Autowired
    private UApplicationMapper uApplicationMapper;
    @Autowired
    private UMajorMapper uMajorMapper;
    @Autowired
    private UClassMapper uClassMapper;
    @Autowired
    private UdetailsMapper udetailsMapper;
    @Override
    public Pagination<UFeedback> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }

    @Override
    public List<KeyAndValue> selectUcompanyALLName() {
        List<KeyAndValue> list = new LinkedList<KeyAndValue>();
         List<UCompany> uCompanies = uCompanyMapper.findAllCompany();
       for (UCompany c:uCompanies){
           KeyAndValue keyAndValue = new KeyAndValue(c.getName(), c.getName());
           list.add(keyAndValue);

       }

        return list;
    }

    @Override
    public List<KeyAndValue> selectALLUmajor() {
        Udetails udetails1 = new Udetails();
        List<KeyAndValue> list = new LinkedList<KeyAndValue>();
        List<Udetails> udetails = udetailsMapper.selectUdetailsMid(udetails1);
        for(Udetails m:udetails){
            KeyAndValue keyAndValue = new KeyAndValue(m.getDid(), m.getuMajor().getName());
            list.add(keyAndValue);
        }
        return list;
    }

    @Override
    public List<KeyAndValue> selectALLUClassname() {
        List<KeyAndValue> list = new LinkedList<KeyAndValue>();
        List<UClass> uClasses = uClassMapper.selectALLUClassname();
        for(UClass c:uClasses){
            KeyAndValue keyAndValue = new KeyAndValue(c.getName(),c.getName());
            list.add(keyAndValue);
        }
        return list;
    }

    @Override
    public List<UClass> selectALLUClassnum(UClass uClass) {
     return uClassMapper.selectALLUClassnum(uClass);
    }

    @Override
    public  List<UFeedback> selectUFeedbackANdaid(UFeedback uFeedback) {
        return uFeedbackMapper.selectUFeedbackANdaid(uFeedback);
    }


}
