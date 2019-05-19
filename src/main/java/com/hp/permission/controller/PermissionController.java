package com.hp.permission.controller;

import com.hp.common.controller.BaseController;
import com.hp.common.model.UPermission;
import com.hp.common.utils.LoggerUtils;
import com.hp.core.mybatis.page.Pagination;
import com.hp.permission.service.PermissionService;
import com.hp.utils.DateUtils;
import com.hp.utils.excel.ExportExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 开发公司：itboy.net<br/>
 * 版权：itboy.net<br/>
 * <p>
 * <p>
 * 用户权限管理
 * <p>
 * <p>
 * <p>
 * 区分　责任人　日期　　　　说明<br/>
 * 创建　周柏成　2016年5月26日 　<br/>
 * <p>
 * *******
 * <p>
 *
 * @author zhou-baicheng
 * @version 1.0, 2016年5月26日 <br/>
 * @email i@itboy.net
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("permission")
public class PermissionController extends BaseController {

    @Autowired
    PermissionService permissionService;

    /**
     * 权限列表
     *
     * @param findContent 查询内容
     * @param pageNo      页码
     * @param modelMap    参数回显
     * @return
     */
    @RequestMapping(value = "index")
    public ModelAndView index(String findContent, ModelMap modelMap, Integer pageNo) {
        modelMap.put("findContent", findContent);
        Pagination<UPermission> permissions = permissionService.findPage(modelMap, pageNo, pageSize);
        return new ModelAndView("permission/index", "page", permissions);
    }

    /**
     * 导出
     *
     * @param ModelMap
     * @param pageNo
     * @param findContent
     * @param request
     * @param response
     */
    @RequestMapping(value = "export", method = RequestMethod.GET)
    public void exportFile(String findContent, ModelMap modelMap, Integer pageNo, HttpServletRequest request, HttpServletResponse response) {
        modelMap.put("findContent", findContent);
        Pagination<UPermission> permissions = permissionService.findPage(modelMap, pageNo, -1);

        String fileName = "权限信息数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
        try {
            new ExportExcel("权限信息数据", UPermission.class).setDataList(permissions.getList()).write(request, response, fileName).dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    /**
     * 权限添加
     *
     * @param psermission
     * @return
     */
    @RequestMapping(value = "addPermission", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addPermission(UPermission psermission) {
        try {
            UPermission entity = permissionService.insertSelective(psermission);
            resultMap.put("status", 200);
            resultMap.put("entity", entity);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败，请刷新后再试！");
            LoggerUtils.fmtError(getClass(), e, "添加权限报错。source[%s]", psermission.toString());
        }
        return resultMap;
    }

    /**
     * 删除权限，根据ID，但是删除权限的时候，需要查询是否有赋予给角色，如果有角色在使用，那么就不能删除。
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "deletePermissionById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteRoleById(String ids) {
        return permissionService.deletePermissionById(ids);
    }
}
