package com.hp.major.controller;

import com.hp.common.bo.KeyAndValue;
import com.hp.common.controller.BaseController;
import com.hp.common.model.UMajor;
import com.hp.common.utils.LoggerUtils;
import com.hp.core.mybatis.page.Pagination;
import com.hp.major.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/02/12
 * @Description:
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("major")
public class MajorController extends BaseController {
    @Autowired
    private MajorService majorService;

    @RequestMapping(value = "list")
    public ModelAndView list(ModelMap map, Integer pageNo, String findContent) {
        map.put("findContent", findContent);
        Pagination<UMajor> page = majorService.findByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("major/list");
    }

    /**
     * 获取专业信息for select
     *
     */
    @RequestMapping(value = "getMajor", method = RequestMethod.GET)
    @ResponseBody
    public List<KeyAndValue> getMajor() {
        return majorService.findAllMajorForSelect();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addMajor(UMajor u) {
        try {
            if (null == u) {
                resultMap.put("status", 500);
                resultMap.put("message", "添加失败，请刷新后再试！");
                LoggerUtils.fmtError(getClass(), "添加专业报错。参数为空");
                return resultMap;
            }
            UMajor u1 = majorService.findByName(u.getName());
            if (null != u1) {
                resultMap.put("message", "专业名称已经存在！");
                return resultMap;
            }
            UMajor entity = majorService.insert(u);
            resultMap.put("status", 200);
            resultMap.put("entity", entity);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败，请刷新后再试！");
            LoggerUtils.fmtError(getClass(), e, "添加专业报错。source[%s]", u.toString());
        }
        return resultMap;
    }

    /**
     * 更新页面跳转
     *
     * @param mid
     * @return
     */
    @RequestMapping(value = "updateMajor/{mid}", method = RequestMethod.GET)
    public ModelAndView updateMajor(@PathVariable("mid") Long mid, ModelMap map) {
        if (null == mid) {
            LoggerUtils.fmtError(getClass(), "更新专业报错，参数为空。");
            return null;
        }
        UMajor uMajor = majorService.selectByPrimaryKey(mid);
        if (null == uMajor) {
            LoggerUtils.fmtError(getClass(), "所更新的专业不存在。");
            return null;
        }
        map.put("page", uMajor);
        return new ModelAndView("major/updateMajor");
    }

    @RequestMapping(value = "updateMajor", method = RequestMethod.POST)
    public ModelAndView updateMajor(UMajor u, ModelMap map) {
        if (null == u) {
            LoggerUtils.fmtError(getClass(), "更新专业报错。参数为空");
            return null;
        }
        majorService.updateByPrimaryKeySelective(u);
        return new ModelAndView("redirect:list.shtml");
    }

    @RequestMapping(value = "deleteMajorById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteMajorById(String ids) {
        return majorService.deleteMajorById(ids);
    }

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public ModelAndView list() {
        return new ModelAndView("major/test");
    }


}
