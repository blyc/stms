package com.hp.uclass.controller;

import com.hp.common.controller.BaseController;
import com.hp.common.model.USchedule;
import com.hp.common.utils.LoggerUtils;
import com.hp.uclass.service.UScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by DELL on 2018/3/23.
 */
@Controller
public class UScheduleController extends BaseController {
    @Autowired
    private UScheduleService uScheduleService;

    @RequestMapping(value = "addUClass", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addUSchedule(USchedule uSchedule, HttpServletRequest request, ModelMap modelMap) {
        try {
            if(null == uSchedule){
                resultMap.put("status", 500);
                resultMap.put("message", "添加失败，请刷新后再试！");
                LoggerUtils.fmtError(getClass(),"添加课表报错。数据为空");
                return resultMap;
            }

            uScheduleService.insert(uSchedule);
            resultMap.put("status", 200);
            /*resultMap.put("entity", entity);*/
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败，请刷新后再试！");
            LoggerUtils.fmtError(getClass(), e, "添加班级报错。source[%s]", uSchedule.toString());
        }
        return resultMap;
    }
}
