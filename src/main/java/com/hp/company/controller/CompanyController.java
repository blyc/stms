package com.hp.company.controller;

import com.hp.common.controller.BaseController;
import com.hp.common.model.UCompany;
import com.hp.common.utils.LoggerUtils;
import com.hp.company.service.CompanyService;
import com.hp.core.mybatis.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


@Controller
@Scope(value = "prototype")
@RequestMapping("company")
public class CompanyController extends BaseController {
    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "list")
    public ModelAndView list(ModelMap map, Integer pageNo, String findContent) {
        map.put("findContent", findContent);
        Pagination<UCompany> page = companyService.findByPage(map, pageNo, pageSize);
        Map<String, Map<String, Long>> sMap = companyService.findByStudentCount();
        for (UCompany uCompany : page.getList()) {
            uCompany.setCount(sMap.get(uCompany.getName()).get("count"));
        }
        map.put("page", page);
        return new ModelAndView("company/list");
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addCompany(UCompany u) {
        try {
            if (null == u) {
                resultMap.put("status", 500);
                resultMap.put("message", "添加失败，请刷新后再试！");
                LoggerUtils.fmtError(getClass(), "添加校区报错。参数为空");
                return resultMap;
            }
            UCompany u1 = companyService.findByName(u.getName());
            if (null != u1) {
                resultMap.put("message", "校区名称已经存在！");
                return resultMap;
            }
            UCompany entity = companyService.insert(u);
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
     * @param cid
     * @return
     */
    @RequestMapping(value = "updateCompany/{cid}", method = RequestMethod.GET)
    public ModelAndView updateCompany(@PathVariable("cid") Long cid, ModelMap map) {
        if (null == cid) {
            LoggerUtils.fmtError(getClass(), "更新校区报错，参数为空。");
            return null;
        }
        UCompany uCompany = companyService.selectByPrimaryKey(cid);
        if (null == uCompany) {
            LoggerUtils.fmtError(getClass(), "所更新的校区不存在。");
            return null;
        }
        map.put("page", uCompany);
        return new ModelAndView("company/updateCompany");
    }

    @RequestMapping(value = "updateCompany", method = RequestMethod.POST)
    public ModelAndView updateCompany(UCompany u, ModelMap map) {
        if (null == u) {
            LoggerUtils.fmtError(getClass(), "更新校区报错。参数为空");
            return null;
        }
        companyService.updateByPrimaryKeySelective(u);
        return new ModelAndView("redirect:list.shtml");
    }

    @RequestMapping(value = "deleteCompanyById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteCompanyById(String ids) {
        return companyService.deleteCompanyById(ids);
    }
}
