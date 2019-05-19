package com.hp.position.controller;

import com.hp.common.controller.BaseController;
import com.hp.common.model.UPosition;
import com.hp.common.utils.LoggerUtils;
import com.hp.core.mybatis.page.Pagination;
import com.hp.position.service.PositionService;
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
 * @Author: boy
 * @Date: 2018/02/13
 * @Description:
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("position")
public class PositionController extends BaseController {
    @Autowired
    private PositionService positionService;

    @RequestMapping(value = "list")
    public ModelAndView list(ModelMap map, Integer pageNo, String findContent) {

        map.put("findContent", findContent);

        Pagination<UPosition> page = positionService.findByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("position/list");
    }

    @RequestMapping(value = "addPosition", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addPosition(UPosition u) {
        try {

            UPosition u1 = positionService.findByName(u.getName());
            if (null != u1) {
                resultMap.put("message", "校区名称已经存在！");
                return resultMap;
            }
            UPosition entity = positionService.insert(u);
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
    @RequestMapping(value = "updatePosition/{id}", method = RequestMethod.GET)
    public ModelAndView updatePosition(@PathVariable("id") Long id, ModelMap map) {
        if (null == id) {
            LoggerUtils.fmtError(getClass(), "更新报错，参数为空。");
            return null;
        }
        UPosition uPosition = positionService.selectByPrimaryKey(id);
        if (null == uPosition) {
            LoggerUtils.fmtError(getClass(), "所更新的数据不存在。");
            return null;
        }
        map.put("page", uPosition);
        return new ModelAndView("position/updatePosition");
    }

    @RequestMapping(value = "updatePosition", method = RequestMethod.POST)
    public ModelAndView updatePosition(UPosition u, ModelMap map) {
        if (null == u) {
            LoggerUtils.fmtError(getClass(), "更新报错。参数为空");
            return null;
        }
        positionService.updateByPrimaryKeySelective(u);
        return new ModelAndView("redirect:list.shtml");
    }

    @RequestMapping(value = "deletePositionById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deletePositionById(String ids) {
        return positionService.deleteUPositionById(ids);
    }
}
