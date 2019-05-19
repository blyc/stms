package com.hp.teachingmaterial.controller;

import com.hp.common.bo.KeyAndValue;
import com.hp.common.controller.BaseController;
import com.hp.common.model.*;
import com.hp.common.utils.LoggerUtils;
import com.hp.company.service.CompanyService;
import com.hp.core.config.IConfig;
import com.hp.core.mybatis.page.Pagination;
import com.hp.core.shiro.token.manager.TokenManager;
import com.hp.major.service.MajorService;
import com.hp.teachingmaterial.service.UApplicationDetailService;
import com.hp.teachingmaterial.service.UApplicationService;
import com.hp.teachingmaterial.service.UFeedbackService;
import com.hp.uclass.service.UClassService;
import com.hp.vclass.bo.VClassBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/25 0025.
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("application")
public class UApplicationController extends BaseController {
    @Autowired
    private UApplicationService uApplicationService;
    @Autowired
    private UFeedbackService uFeedbackService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UClassService uClassService;

    @Autowired
    private UApplicationDetailService uApplicationDetailService;

    @Autowired
    private MajorService majorService;

    /**
     * 教材申请列表
     *
     * @param map
     * @param pageNo
     * @param findContent
     * @return
     */
    @RequestMapping(value = "list")
    public ModelAndView list(ModelMap map, Integer pageNo, String findContent) {
        UUser uUser = TokenManager.getToken();
        map.put("findContent", findContent);
        map.put("cid", uUser.getCid());
        Pagination<UApplication> page = uApplicationService.findByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("teachingmaterial/applicationlist");
    }

    /**
     * 教材申请明细列表
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "detaillist/{id}")
    public ModelAndView getdetail(ModelMap map, Integer pageNo, String findContent, @PathVariable("id") Long id) {
        UUser uUser = TokenManager.getToken();
        map.put("findContent", findContent);
        map.put("cid", uUser.getCid());
        map.put("aid", id);
        Pagination<UApplicationDetail> page = uApplicationDetailService.findByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("teachingmaterial/applicationdetaillist");
    }

    /**
     * 获取年级下拉框
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "getgrade", method = RequestMethod.GET)
    @ResponseBody
    public List<KeyAndValue> getGrade(ModelMap map) {
        Long cid = TokenManager.getToken().getCid();
        return uClassService.findUClassGradeByCid(cid);
    }

    /**
     * 获取专业下拉框
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "getmajor", method = RequestMethod.GET)
    @ResponseBody
    public List<KeyAndValue> getMajor(ModelMap map) {
        return majorService.findAllMajorForSelect();
    }

    /**
     * 获取订购校区名字
     *
     * @return
     */
    @RequestMapping(value = "getcompany", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getcompany() {
        UUser uUser = TokenManager.getToken();
        UCompany entity = companyService.selectByPrimaryKey(uUser.getCid());
        resultMap.put("entity", entity);
        return resultMap;
    }

    /**
     * 获取班级信息
     *
     * @param vClass
     * @return
     */
    @RequestMapping(value = "classinfo", method = RequestMethod.GET)
    @ResponseBody
    public VClassBo getClassInfo(VClass vClass) {
        UUser uUser = TokenManager.getToken();
        vClass.setCid(uUser.getCid());
        VClassBo vClassBo = uClassService.findClass(vClass);
        if (vClassBo == null) {
            return null;
        }
        return vClassBo;
    }

       /**
     * 提交订教材申请
     *
     * @param uApplication
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveUApplication(UApplication uApplication) {
        try {

            uApplicationService.saveUApplication(uApplication);
            resultMap.put("status", 200);
            resultMap.put("message", "添加成功");
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败出错");
        }
        return resultMap;
    }

    /**
     * 新增订购明细
     *
     * @param u
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addUApplicationDetail(UApplicationDetail u) {
        try {
            uApplicationDetailService.saveUapplicationDetail(u);
            resultMap.put("status", 200);
            resultMap.put("entity", "添加成功");
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败，请刷新后再试！");
            LoggerUtils.fmtError(getClass(), e, "添加考试报错。");
        }

        return resultMap;
    }

    /**
     * 订购状态更新
     *
     * @param action
     * @param id
     * @return
     */
    @RequestMapping(value = "ustate", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateState(String action, Long id) {
        try {

            UApplication uApplication = uApplicationService.updateState(action, id);
            resultMap.put("status", 200);
            resultMap.put("message", "添加成功");
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败出错");
        }
        return resultMap;
    }


    @RequestMapping(value = "insertSelective")
    @ResponseBody
    public Map<String, Object> insertSelective(UFeedback uFeedback) {
        try {
            uApplicationService.insertSelective(uFeedback);
            resultMap.put("status", 200);
            resultMap.put("message", "添加成功");
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败出错");
        }
        return resultMap;
    }


    /**
     * @param file
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/uploada")
    @ResponseBody
    public Map<String, Object> upload(MultipartFile file, HttpServletRequest request) throws IOException {

        /*获取目录地址*/
        String path = IConfig.get("uploadpath");

        /* 获取文件名字*/
        String fileName = file.getOriginalFilename();

        /*获取file文件 把目录地址 和文件名字放进去*/
        File file1 = new File(path, fileName);
        /*判断是否有这个文件*/
        if (!file1.exists()) {
            file1.mkdirs();
        }

        //MultipartFile自带的解析方法
        file.transferTo(file1);
        String a = file1.getAbsolutePath();
        resultMap.put("file", fileName);
        resultMap.put("fileName", a);
        return resultMap;

    }

    @RequestMapping(value = "updateByPrimaryKeySelective")
    @ResponseBody
    public Map<String, Object> updateByPrimaryKeySelective(UApplication uApplication) {
        uApplicationService.updateByPrimaryKeySelective(uApplication);
        return resultMap;
    }

    @RequestMapping(value = "selectUFeedbackANdaid")
    @ResponseBody
    public Map<String, Object> selectUFeedbackANdaid(UFeedback uFeedback) {
        List<UFeedback> uFeedbacks = uFeedbackService.selectUFeedbackANdaid(uFeedback);
        resultMap.put("uFeedbacks", uFeedbacks);
        return resultMap;
    }

    @RequestMapping(value = "exportStudentsInfo", method = RequestMethod.GET)
    public void exportStudentsInfo(HttpServletRequest httpServletRequest, HttpServletResponse response, String fileName) {
        uApplicationService.findAllInfo(response);

    }

    @RequestMapping(value = "selectByUdetails")
    @ResponseBody
    public Map<String, Object> selectByUdetails(Udetails udetails) {

        List<Udetails> udetails1 = uApplicationService.selectUdetailsprice(udetails);
        resultMap.put("udetails1", udetails1);
        return resultMap;
    }
}
