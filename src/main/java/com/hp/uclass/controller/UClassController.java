package com.hp.uclass.controller;

import com.hp.common.bo.KeyAndValue;
import com.hp.common.controller.BaseController;
import com.hp.common.model.UClass;
import com.hp.core.shiro.token.manager.TokenManager;
import com.hp.uclass.service.UClassService;
import com.hp.vclass.service.VClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2018/2/25 0025.
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("uclass")
public class UClassController extends BaseController {

    @Autowired
    private UClassService uclassService;
    @Autowired
    private VClassService vclassService;


    @RequestMapping(value = "selectClass", method = RequestMethod.GET)
    @ResponseBody
    public List<KeyAndValue> getClass(ModelMap map, UClass uClass) {
        Long cid = TokenManager.getToken().getCid();
        map.put("cid",cid);
        map.put("mid", uClass.getMid());
        map.put("grade", uClass.getGrade());
        return uclassService.findUClassByParam(map);
    }

    @RequestMapping(value = "selectUClassByCid", method = RequestMethod.POST)
    @ResponseBody
    public List<UClass> selectUClassByCid() {
        return uclassService.findUClassByCid();
    }

}
