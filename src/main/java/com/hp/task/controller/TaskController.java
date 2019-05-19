package com.hp.task.controller;

import com.hp.common.bo.KeyAndValue;
import com.hp.common.controller.BaseController;
import com.hp.common.model.UTask;
import com.hp.common.model.UTaskAccomplish;
import com.hp.common.model.UUser;
import com.hp.common.utils.DateUtil;
import com.hp.common.utils.LoggerUtils;
import com.hp.core.mybatis.page.Pagination;
import com.hp.core.shiro.token.manager.TokenManager;
import com.hp.task.bo.UTaskAccomplishbo;
import com.hp.task.bo.UTaskbo;
import com.hp.task.service.TaskAccomplishService;
import com.hp.task.service.TaskService;
import com.hp.uclass.service.UClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by Dragon on 2018/4/11 0011.
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("task")
public class TaskController extends BaseController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UClassService uClassService;
    @Autowired
    private TaskAccomplishService taskAccomplishService;


    /**
     * 显示作业列表
     *
     * @param map
     * @param pageNo
     * @param findContent
     * @return
     */
    @RequestMapping(value = "list")
    public ModelAndView list(ModelMap map, Integer pageNo, String findContent) {
        UUser uUser = TokenManager.getToken();
        map.put("findContent", findContent);
        if (!uUser.getCid().equals(MANAGE_COMPANY_ID)) {
            map.put("cid", uUser.getCid());
        }
        Pagination<UTask> page = taskService.findBypage(map, pageNo, pageSize);
        map.put("page", page);
        String currentDateString = DateUtil.getCurrentDateString("yyyy/MM/dd");
        return new ModelAndView("task/list", map);
    }

    /**
     * 获取班级下拉列表
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "class", method = RequestMethod.GET)
    @ResponseBody
    public List<KeyAndValue> getClass(ModelMap map) {
        Long cid = TokenManager.getToken().getCid();
        map.put("cid", cid);
        return uClassService.findUClassByParam(map);
    }

    /**
     * 添加作业
     *
     * @param uTask
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addtask(UTask uTask) {
        try {
            taskService.insertUtask(uTask);
            resultMap.put("status", 200);
            resultMap.put("entity", "添加成功");
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败，请刷新后再试！");
            LoggerUtils.fmtError(getClass(), e, "添加作业报错。");
        }

        return resultMap;
    }

    /**
     * 获取学生姓名
     *
     * @param tid
     * @return
     */
    @RequestMapping(value = "getstudentname", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getstudentname(Long tid) {
        List<UTaskAccomplish> uTaskAccomplishList = taskAccomplishService.findUtaskaccomplishName(tid);
        resultMap.put("info", uTaskAccomplishList);
        return resultMap;
    }

    /**
     * 更新作业完成情况
     *
     * @param uTaskAccomplishbos
     * @param tid
     * @return
     */
    @RequestMapping(value = "updateGrade/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateWritten(@RequestBody List<UTaskAccomplishbo> uTaskAccomplishbos, @PathVariable("id") Long tid) {

        try {
            int ret = taskAccomplishService.updateByPrimaryKey(uTaskAccomplishbos, tid);
            if (ret < 0) {
                resultMap.put("status", 500);
                resultMap.put("message", "添加失败，请刷新后再试！");
            } else {
                resultMap.put("status", 200);
            }
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败，请刷新后再试！");
            LoggerUtils.fmtError(getClass(), e, "添加成绩报错。");
        }
        return resultMap;
    }

    /**
     * 查询学生作业完成情况
     *
     * @param tid
     * @return
     */
    @RequestMapping(value = "getstudenttask", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getstudenttask(Long tid) {
        List<UTaskAccomplish> uTaskAccomplishList = taskAccomplishService.findExamDetails(tid);
        resultMap.put("info", uTaskAccomplishList);

        UTaskbo uTaskbo = taskAccomplishService.findUTaskboeBo(tid);
        resultMap.put("gradeinfo", uTaskbo);
        return resultMap;
    }

//    //导出学生信息
//    @RequestMapping(value = "exportgrade_{id}", method = RequestMethod.GET)
//    public void exportGrade(HttpServletRequest httpServletRequest, HttpServletResponse response, @PathVariable("id") Long tid) {
//        taskAccomplishService.exportStudentProjectGradeService(response, tid);
//
//    }

}