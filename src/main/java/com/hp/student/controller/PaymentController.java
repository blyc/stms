package com.hp.student.controller;

import com.hp.common.controller.BaseController;
import com.hp.common.model.UClass;
import com.hp.common.model.UPayment;
import com.hp.common.model.VClass;
import com.hp.common.utils.LoggerUtils;
import com.hp.core.mybatis.page.Pagination;
import com.hp.core.shiro.token.manager.TokenManager;
import com.hp.core.statics.Constant;
import com.hp.student.bo.Paymentbo;
import com.hp.student.service.PaymentService;
import com.hp.uclass.service.UClassService;
import com.hp.vclass.service.VClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/08/10
 * @Description:
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("payment")
public class PaymentController extends BaseController {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private VClassService vClassService;

    @Autowired
    private UClassService uClassService;

    @RequestMapping(value = "s1list")
    public ModelAndView s1list(ModelMap map, Integer pageNo, String findContent) {
        Long cid = TokenManager.getToken().getCid();
        map.put("findContent", findContent);
        map.put("stage", Constant.LEARNING_STAGE_S1);
        map.put("nozero", "nozero");
//        map.put("cid", cid);
        if (!cid.equals(MANAGE_COMPANY_ID)) {
            map.put("cid", cid);
        }
        Pagination<VClass> page = vClassService.findByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("payment/list");
    }

    @RequestMapping(value = "infolist")
    public ModelAndView infolist(ModelMap map, Integer pageNo, String findContent) {
        Long cid = TokenManager.getToken().getCid();
        map.put("findContent", findContent);
        if (!cid.equals(MANAGE_COMPANY_ID)) {
            map.put("cid", cid);
        }
        Pagination<UPayment> page = paymentService.findByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("payment/infolist");
    }

    @RequestMapping(value = "s2list")
    public ModelAndView s2list(ModelMap map, Integer pageNo, String findContent) {
        Long cid = TokenManager.getToken().getCid();
        map.put("findContent", findContent);
        map.put("stage", Constant.LEARNING_STAGE_S2);
        if (!cid.equals(MANAGE_COMPANY_ID)) {
            map.put("cid", cid);
        }
        map.put("nozero", "nozero");
        Pagination<VClass> page = vClassService.findByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("payment/list");
    }


    /**
     * 更新学生升学情况
     *
     * @param paymentbos
     * @param id
     * @return
     */
    @RequestMapping(value = "do/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addAttendance(@RequestBody List<Paymentbo> paymentbos, @PathVariable("id") Long id) {
        try {

            UClass entity = uClassService.updateStage(paymentbos, id);
            if (null == entity) {
                resultMap.put("status", 500);
                resultMap.put("message", "添加失败，请刷新后再试！");
            } else {
                resultMap.put("status", 200);
                resultMap.put("entity", entity);
            }
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败，请刷新后再试！");
            LoggerUtils.fmtError(getClass(), e, "添加出勤报错。");
        }
        return resultMap;
    }
}
