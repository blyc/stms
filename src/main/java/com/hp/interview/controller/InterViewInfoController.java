package com.hp.interview.controller;

import com.hp.common.controller.BaseController;
import com.hp.common.model.UInterViewInfo;
import com.hp.common.utils.LoggerUtils;
import com.hp.core.mybatis.page.Pagination;
import com.hp.interview.service.InterViewInfoService;
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

/**
 * Created by lenovo on 2018/4/18.
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("interview")
public class InterViewInfoController extends BaseController {
    @Autowired
    private InterViewInfoService interViewInfoService;

    /**
     * 访谈记录查询
     * @param map
     * @param pageNo
     * @param findContent
     * @param iid
     * @return
     */
    @RequestMapping("infolist/{iid}")
    @ResponseBody
    public ModelAndView infolist(ModelMap map, Integer pageNo, String findContent, @PathVariable("iid") Long iid) {
        map.put("findContent", findContent);
        map.put("iid", iid);
        Pagination<UInterViewInfo> page = interViewInfoService.findByPage(map, pageNo, pageSize);
        map.put("page", page);
        map.put("isableinset", 1);
        return new ModelAndView("interview/infolist");
    }

    /**
     * 登记访谈记录
     * @param uInterViewInfo
     * @return
     */
    @RequestMapping(value = "registerInterViewInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> registerInterViewInfo(UInterViewInfo uInterViewInfo) {
        try {

            UInterViewInfo entity = interViewInfoService.insertInterViewInfo(uInterViewInfo);
            if (null == entity) {
                resultMap.put("status", 500);
                resultMap.put("message", "登记失败，超过访谈时间！");
            }
            resultMap.put("status", 200);
            resultMap.put("entity", entity);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "登记失败，请刷新后再试！");
            LoggerUtils.fmtError(getClass(), e, "登记数据报错。");
        }
        return resultMap;
    }
}
