package com.hp.department.controller;

import com.hp.common.controller.BaseController;
import com.hp.common.model.UDepartment;
import com.hp.common.utils.LoggerUtils;
import com.hp.core.mybatis.page.Pagination;
import com.hp.department.service.DepartmentSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/02/13
 * @Description:
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("department")
public class DepartmentController extends BaseController {
    @Autowired
    private DepartmentSevice departmentSevice;

    @RequestMapping(value = "list")
    public ModelAndView list(ModelMap map, Integer pageNo, String findContent) {
        map.put("findContent", findContent);
        Pagination<UDepartment> page = departmentSevice.findByPage(map, pageNo, pageSize);
        Map<String, Map<String, Long>> byEmployeeCount = departmentSevice.findByEmployeeCount();
        for (UDepartment uDepartment : page.getList()) {
            uDepartment.setCount(byEmployeeCount.get(uDepartment.getName()).get("count"));
        }
        map.put("page", page);
        return new ModelAndView("department/list");
    }

    @RequestMapping(value = "addDepartment", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addDepartment(UDepartment u) {
        try {

            UDepartment u1 = departmentSevice.findByName(u.getName());
            if (null != u1) {
                resultMap.put("message", "部门名称已经存在！");
                return resultMap;
            }
            Date date = new Date();
            u.setCreatetime(date);
            UDepartment entity = departmentSevice.insert(u);
            resultMap.put("status", 200);
            resultMap.put("entity", entity);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败，请刷新后再试！");
            LoggerUtils.fmtError(getClass(), e, "添加校区报错。source[%s]", u.toString());
        }
        return resultMap;
    }

    /**
     * 更新页面跳转
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "updateDepartMent/{id}", method = RequestMethod.GET)
    public ModelAndView updateDepartMent(@PathVariable("id") Long id, ModelMap map) {
        if (null == id) {
            LoggerUtils.fmtError(getClass(), "更新报错，参数为空。");
            return null;
        }
        UDepartment uDepartment = departmentSevice.selectByPrimaryKey(id);
        if (null == uDepartment) {
            LoggerUtils.fmtError(getClass(), "所更新的数据不存在。");
            return null;
        }
        map.put("page", uDepartment);
        return new ModelAndView("department/updateDepartMent");
    }

    @RequestMapping(value = "updateDepartMent", method = RequestMethod.POST)
    public ModelAndView updateDepartMent(UDepartment u, ModelMap map) {
        if (null == u) {
            LoggerUtils.fmtError(getClass(), "更新报错。参数为空");
            return null;
        }
        departmentSevice.updateByPrimaryKeySelective(u);
        return new ModelAndView("redirect:list.shtml");
    }

    @RequestMapping(value = "deleteDepartMentById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteDepartMentById(String ids) {
        return departmentSevice.deleteDepartMentById(ids);
    }


}
