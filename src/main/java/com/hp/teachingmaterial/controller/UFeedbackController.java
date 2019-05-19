package com.hp.teachingmaterial.controller;

import com.hp.common.bo.KeyAndValue;
import com.hp.common.controller.BaseController;
import com.hp.common.model.UApplication;
import com.hp.common.model.UClass;
import com.hp.common.model.UFeedback;
import com.hp.core.mybatis.page.Pagination;
import com.hp.teachingmaterial.service.UApplicationService;
import com.hp.teachingmaterial.service.UFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by slm on 2018/3/29.
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("feedback")
public class UFeedbackController extends BaseController {
    @Autowired
    private UFeedbackService uFeedbackService;
    @Autowired
    private UApplicationService uApplicationService;

    @RequestMapping(value = "list")
    public ModelAndView list(ModelMap map, Integer pageNo, String findContent) {
        map.put("findContent", findContent);

        Pagination<UFeedback> page = uFeedbackService.findByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("feedback/list");
    }

    @RequestMapping(value = "selectUcompanyALLName", method = RequestMethod.POST)
    @ResponseBody
    public List<KeyAndValue> selectUcompanyALLName() {
        return uFeedbackService.selectUcompanyALLName();
    }

    @RequestMapping(value = "selectUApplicationBycampus")
    @ResponseBody
    public Map<String, Object> selectUApplicationBycampus(UApplication uApplication) {
        System.out.println("123456" + uApplication.getCampus());
        List<UApplication> uApplications = uApplicationService.selectUApplicationBycampus(uApplication);
        resultMap.put("uApplications", uApplications);
        return resultMap;
    }


    @RequestMapping(value = "selectALLUmajor", method = RequestMethod.GET)
    @ResponseBody
    public List<KeyAndValue> selectALLUmajor() {
        return uFeedbackService.selectALLUmajor();
    }

    @RequestMapping(value = "selectALLUClassname", method = RequestMethod.GET)
    @ResponseBody
    public List<KeyAndValue> selectALLUClassname() {

        return uFeedbackService.selectALLUClassname();
    }

    @RequestMapping(value = "selectALLUClassnum")
    @ResponseBody
    public Map<String, Object> selectALLUClassnum(UClass uClass) {
        List<UClass> uClasses = uFeedbackService.selectALLUClassnum(uClass);
        resultMap.put("uClasses", uClasses);
        return resultMap;
    }


}
