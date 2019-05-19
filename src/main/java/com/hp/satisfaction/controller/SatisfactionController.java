package com.hp.satisfaction.controller;

import com.hp.common.bo.KeyAndValue;
import com.hp.common.bo.QueryBuffer;
import com.hp.common.controller.BaseController;
import com.hp.common.model.USatisfaction;
import com.hp.common.model.UUser;
import com.hp.core.mybatis.page.Pagination;
import com.hp.core.shiro.token.manager.TokenManager;
import com.hp.employee.service.EmployeeService;
import com.hp.satisfaction.service.SatisfactionService;
import com.hp.uclass.service.UClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/08/10
 * @Description:
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("satisfaction")
public class SatisfactionController extends BaseController {

    @Autowired
    private SatisfactionService satisfactionService;
    @Autowired
    private UClassService uClassService;

    @Autowired
    private EmployeeService employeeService;

    /**
     * @param map
     * @param pageNo
     * @param findContent
     * @return
     */
    @RequestMapping(value = "list")
    public ModelAndView list(ModelMap map, Integer pageNo, String findContent,QueryBuffer queryBuffer) {
        UUser uUser = TokenManager.getToken();
        map.put("findContent", findContent);
        map.put("queryDate", queryBuffer.getQueryDate());
        if (!uUser.getCid().equals(MANAGE_COMPANY_ID)) {
            map.put("cid", uUser.getCid());
        }
        Pagination<USatisfaction> page = satisfactionService.findByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("satisfaction/list");
    }

    /**
     * @param uSatisfaction
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addUSatisfaction(USatisfaction uSatisfaction) {
        try {
            satisfactionService.addUSatisfaction(uSatisfaction);
            resultMap.put("status", 200);
            resultMap.put("message", "添加成功");
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败出错");
        }
        return resultMap;
    }

    /**
     * 删除满意度
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "deleteUSatisfaction", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteUSatisfaction(String ids) {
        return satisfactionService.delete(ids);
    }

    /**
     * 获取班级下拉列表
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "class", method = RequestMethod.GET)
    @ResponseBody
    public List<KeyAndValue> getClass(ModelMap map, Long eid) {
        Long cid = TokenManager.getToken().getCid();
        map.put("cid", cid);
        map.put("eid", eid);
        return uClassService.findUClassByParam(map);
    }

    @RequestMapping(value = "teacher", method = RequestMethod.GET)
    @ResponseBody
    public List<KeyAndValue> getTeacher(ModelMap map) {
        //Pagination<USatisfaction> page = satisfactionService.findByPage(map, pageNo, pageSize);
        List<KeyAndValue> allEmployeeForSelect = employeeService.findAllEmployeeForSelect();
//        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
//        Date date = new Date();
//        for (KeyAndValue keyAndValue : allEmployeeForSelect) {
//            for (USatisfaction uSatisfaction : page.getList()) {
//                if (keyAndValue.getKey()==uSatisfaction.getEid()) {
//                    Date surveyTime = uSatisfaction.getSurveyTime();
//                    if (ft.format(surveyTime).equals(ft.format(date))) {
//                        allEmployeeForSelect.remove(keyAndValue);
//                    }
//                }
//            }
//        }
        return allEmployeeForSelect;
    }


}
