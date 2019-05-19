package com.hp.course.controller;

import com.hp.common.controller.BaseController;
import com.hp.common.model.USchedule;
import com.hp.common.model.VClass;
import com.hp.common.utils.LoggerUtils;
import com.hp.course.service.ScheduleService;
import com.hp.vclass.service.VClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@Scope(value = "prototype")
@RequestMapping("course")
public class CourseController extends BaseController {

    @Autowired
    private VClassService vClassService;
    @Autowired
    private ScheduleService scheduleService;


    /**
     * 校区教务查看课表
     *
     * @param map
     * @param findContent
     * @param id
     * @return
     */
    @RequestMapping(value = "schedulelist_{id}")
    public ModelAndView schedulelist(ModelMap map, String findContent, @PathVariable("id") Long id) {
        map.put("findContent", findContent);
        map.put("cid", id);
        return new ModelAndView("course/schedulelist");
    }


    @RequestMapping(value = "schedulelistall", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> schedulelistall(Long id) {
        List<USchedule> list = scheduleService.selectUScheduleAllByccidService(id);
        resultMap.put("list", list);
        return resultMap;
    }

    /**
     * 查询讲师
     *
     * @param cid
     * @return
     */
    @RequestMapping(value = "getClasstname", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getClasstname(Long cid) {
        try {
            VClass vClass = vClassService.SelectVClassByccidService(cid);
            resultMap.put("status", 200);
            resultMap.put("vClass", vClass);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "请刷新后再试！");
        }
        return resultMap;
    }

    /**
     * 添加课表
     *
     * @param uSchedule
     * @return
     */
    @RequestMapping(value = "addschedule", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addschedule(USchedule uSchedule) {
        try {
            boolean ss = scheduleService.insert(uSchedule);
            if (ss == true) {
                resultMap.put("status", 200);
                resultMap.put("message", "添加成功");
            } else {
                resultMap.put("status", 500);
                resultMap.put("message", "添加失败，课程冲突！");
            }
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败，请刷新后再试！");
            LoggerUtils.fmtError(getClass(), e, "添加课表报错。");
        }
        return resultMap;
    }

    /**
     * 课表导出
     * @param ccid
     * @param response
     */
    @RequestMapping(value = "exportschedule_{id}", method = RequestMethod.GET)
    public void exportschedule(@PathVariable("id") Long ccid, HttpServletResponse response) {
        scheduleService.findAllInfo(response, ccid);
    }
}
