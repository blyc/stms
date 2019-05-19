package com.hp.stock.controller;

import com.hp.common.bo.KeyAndValue;
import com.hp.common.controller.BaseController;
import com.hp.common.model.UStorage;
import com.hp.common.model.Udetails;
import com.hp.core.mybatis.page.Pagination;
import com.hp.stock.service.UdetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by slm on 2018-04-15.
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("stock")
public class UdetailsController extends BaseController {
    @Autowired
    private UdetailsService udetailsService;


    @RequestMapping(value = "list")
    public ModelAndView list(ModelMap map, Integer pageNo, String findContent) {
        map.put("findContent",findContent);

        Pagination<Udetails> page = udetailsService.findByPage(map,pageNo,pageSize);
        map.put("page", page);
        return new ModelAndView("stock/list");
    }

    @RequestMapping(value = "findAllUdetails",method = RequestMethod.POST)
    @ResponseBody
    public List<KeyAndValue> selectALLUmajor(){

        return udetailsService.selectUdetails();
    }
    @RequestMapping(value = "insertUStorage")
    @ResponseBody
    public Map<String,Object> insertUStorage(UStorage uStorage,Udetails udetails){
        System.out.println("---------------------------");
        try {
            Date nowTime=new Date();
            uStorage.setSdate(nowTime);
            udetailsService.insertUStorage(uStorage,udetails);
            resultMap.put("status", 200);
            resultMap.put("message", "添加成功");
        }catch (Exception e){
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败出错");
        }
        return  resultMap;
    }

}
