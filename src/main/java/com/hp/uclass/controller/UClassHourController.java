package com.hp.uclass.controller;

import com.hp.common.bo.QueryBuffer;
import com.hp.common.controller.BaseController;
import com.hp.common.model.UClassHour;
import com.hp.common.utils.LoggerUtils;
import com.hp.core.mybatis.page.Pagination;
import com.hp.core.shiro.token.manager.TokenManager;
import com.hp.uclass.service.UClassHourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/11/23
 * @Description:
 */
@Controller
@Scope(value = "prototype")
@RequestMapping(" uclasshour")
public class UClassHourController extends BaseController {

    @Autowired
    private UClassHourService uClassHourService;

    @RequestMapping(value = "list")
    public ModelAndView list(ModelMap map, Integer pageNo, String findContent, QueryBuffer queryBuffer) {
        Long cid = TokenManager.getToken().getCid();
        if (!cid.equals(MANAGE_COMPANY_ID)) {
            map.put("cid", cid);
        }

        /*一般查询*/
        map.put("findContent", findContent);
        map.put("queryDate", queryBuffer.getQueryDate());

        Pagination<UClassHour> page = uClassHourService.findByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("class/courselist");
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addUClassHour(UClassHour uClassHour) {
        try {
            uClassHourService.addUClassHour(uClassHour);
            resultMap.put("status", 200);
            resultMap.put("message", "添加成功");
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败出错");
        }
        return resultMap;
    }

    @RequestMapping(value = "getUClassHour", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getUClassHour(Long uchid) {
        try {

            UClassHour entity = uClassHourService.findByPk(uchid);

            if (null == entity) {
                resultMap.put("message", "课时不存存在！");
                return resultMap;
            }

            resultMap.put("status", 200);
            resultMap.put("entity", entity);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败，请刷新后再试！");
        }
        return resultMap;
    }

    /**
     * 更新课时信息
     *
     * @param uClassHour
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updateUJsRank(UClassHour uClassHour) {
        try {
            if (uClassHour == null) {
                resultMap.put("status", 500);
                resultMap.put("message", "参数错误！");
                return resultMap;
            }
            int ret = uClassHourService.update(uClassHour);

            if (ret < 0) {
                resultMap.put("status", 500);
                resultMap.put("message", "更新失败！");
                return resultMap;
            }
            resultMap.put("status", 200);
            resultMap.put("entity", uClassHour);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "更新失败，请刷新后再试！");
            LoggerUtils.fmtError(getClass(), e, "更新课时报错。source[%s]", uClassHour.toString());
        }
        return resultMap;
    }
    /**
     * 删除课时
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "deleteUclasshour", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteUclasshour(String ids) {
        return uClassHourService.delete(ids);
    }


}
