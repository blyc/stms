package com.hp.interview.controller;

import com.hp.common.bo.KeyAndValue;
import com.hp.common.controller.BaseController;
import com.hp.common.model.UInterView;
import com.hp.common.model.UUser;
import com.hp.common.utils.LoggerUtils;
import com.hp.core.mybatis.page.Pagination;
import com.hp.core.shiro.token.manager.TokenManager;
import com.hp.interview.service.InterViewInfoService;
import com.hp.interview.service.InterViewService;
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
 * Created by lenovo on 2018/4/13.
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("interview")
public class InterViewController extends BaseController {

    @Autowired
    private InterViewService interViewService;
    @Autowired
    private UClassService uClassService;
    @Autowired
    private InterViewInfoService interViewInfoService;

    @RequestMapping("list")
    public ModelAndView list(ModelMap map, Integer pageNo, String findContent) {
        map.put("findContent", findContent);
        UUser uUser = TokenManager.getToken();
        map.put("cid", uUser.getCid());
        Pagination<UInterView> page = interViewService.findByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("interview/list");
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
     * 增加访谈
     * @param uInterView
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addInterView(UInterView uInterView) {
        try {

            UInterView entity = interViewService.insertInterView(uInterView);
            if (null == entity) {
                resultMap.put("status", 500);
                resultMap.put("message", "添加失败，请刷新后再试！");
            }
            resultMap.put("status", 200);
            resultMap.put("entity", entity);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败，请刷新后再试！");
            LoggerUtils.fmtError(getClass(), e, "添加数据报错。");
        }

        return resultMap;
    }

}
