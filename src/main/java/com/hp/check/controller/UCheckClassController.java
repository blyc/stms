package com.hp.check.controller;

import com.hp.check.service.UCheckClassService;
import com.hp.common.bo.KeyAndValue;
import com.hp.common.controller.BaseController;
import com.hp.common.model.UCheckClass;
import com.hp.common.model.UUser;
import com.hp.common.model.VClass;
import com.hp.common.utils.LoggerUtils;
import com.hp.core.mybatis.page.Pagination;
import com.hp.core.shiro.token.manager.TokenManager;
import com.hp.uclass.service.UClassService;
import com.hp.vclass.service.VClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2018/3/27.
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("check")
public class UCheckClassController extends BaseController {
    @Autowired
    private UCheckClassService uCheckClassService;
    @Autowired
    private VClassService vClassService;
    @Autowired
    private UClassService uClassService;

    /**
     * 校区教务查课列表
     *
     * @param map
     * @param pageNo
     * @param findContent
     * @return
     */
    @RequestMapping(value = "list")
    public ModelAndView list(ModelMap map, Integer pageNo, String findContent) {
        UUser uUser = TokenManager.getToken();
        Long cid = uUser.getCid();
        map.put("findContent", findContent);
        if (!cid.equals(MANAGE_COMPANY_ID)) {
            map.put("cid", cid);
        }
        Pagination<UCheckClass> page = uCheckClassService.findByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("check/list");
    }

    /**
     * 获取班级下拉列表
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "class", method = RequestMethod.GET)
    @ResponseBody
    public List<KeyAndValue> getClassForExam(ModelMap map) {
        Long cid = TokenManager.getToken().getCid();
        map.put("cid", cid);
        return uClassService.findUClassByParam(map);
    }

    @RequestMapping(value = "selectVClassByCcid", method = RequestMethod.GET)
    @ResponseBody
    public List<VClass> selectVClassByCcid(@RequestParam Long ccid) {
        List<VClass> vClassList = new ArrayList<VClass>();
        VClass vClass = vClassService.findVClassByCcid(ccid);
        vClassList.add(vClass);
        return vClassList;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addCheckClass(UCheckClass uCheckClass) {
        try {
            Long cid = TokenManager.getToken().getCid();
            Date nowdate = new Date();
            uCheckClass.setChdate(nowdate);
            uCheckClass.setCid(cid);

            UCheckClass entity = uCheckClassService.insertCheckClass(uCheckClass);
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

    @RequestMapping(value = "updateCheck", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateClass(UCheckClass record) {
        try {

            UCheckClass entity = uCheckClassService.updateUCheckClassByChid(record);
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
