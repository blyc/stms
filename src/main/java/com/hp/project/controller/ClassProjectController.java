package com.hp.project.controller;

import com.hp.common.bo.KeyAndValue;
import com.hp.common.bo.QueryBuffer;
import com.hp.common.controller.BaseController;
import com.hp.common.model.UClassProject;
import com.hp.common.utils.DateUtil;
import com.hp.common.utils.LoggerUtils;
import com.hp.core.mybatis.page.Pagination;
import com.hp.core.shiro.token.manager.TokenManager;
import com.hp.project.service.ClassProjectService;
import com.hp.project.service.StudentProjectService;
import com.hp.uclass.service.UClassService;
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
 * @Author: boy
 * @Date: 2018/03/29
 * @Description:
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("project")
public class ClassProjectController extends BaseController {

    @Autowired
    private ClassProjectService classProjectService;
    @Autowired
    private UClassService uClassService;
    @Autowired
    private StudentProjectService studentProjectService;

    /**
     * 显示项目列表
     *
     * @param map
     * @param pageNo
     * @param findContent
     * @return
     */
    @RequestMapping(value = "list")
    public ModelAndView list(ModelMap map, Integer pageNo, String findContent, QueryBuffer queryBuffer) {
        Long cid = TokenManager.getToken().getCid();
        map.put("findContent", findContent);
        map.put("queryDate", queryBuffer.getQueryDate());
        if (!cid.equals(MANAGE_COMPANY_ID)) {
            map.put("cid", cid);
        }
        Pagination<UClassProject> page = classProjectService.findByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("project/list", map);
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
     * 添加项目申请信息
     *
     * @param uClassProject
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addProject(UClassProject uClassProject) {
//        Date projectdate = uClassProject.getStartTime();
//        Date nowdate = DateUtil.getCurrentDateTime();
//
//        if (projectdate.getTime() < nowdate.getTime()) {
//            resultMap.put("projectdate", 1);
//            resultMap.put("message", "添加项目日期错误");
//        } else {
        try {
            uClassProject.setStartTime(DateUtil.getCurrentDateTime());
            uClassProject.setStartEnd(DateUtil.getCurrentDateTime());
            studentProjectService.insertBatch(uClassProject);
            resultMap.put("status", 200);
            resultMap.put("entity", "添加成功");
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败，请刷新后再试！");
            LoggerUtils.fmtError(getClass(), e, "添加用户报错。source");
        }
//        }
        return resultMap;
    }


    /**
     * 删除项目
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "deleteProject", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteProject(String ids) {
        return classProjectService.delete(ids);
    }


}
