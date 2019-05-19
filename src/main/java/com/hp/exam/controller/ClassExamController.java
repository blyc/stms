package com.hp.exam.controller;

import com.hp.common.bo.KeyAndValue;
import com.hp.common.bo.QueryBuffer;
import com.hp.common.controller.BaseController;
import com.hp.common.model.UClass;
import com.hp.common.model.UClassExam;
import com.hp.common.model.VClass;
import com.hp.common.model.VSummaryExam;
import com.hp.common.utils.LoggerUtils;
import com.hp.core.mybatis.page.Pagination;
import com.hp.core.shiro.token.manager.TokenManager;
import com.hp.exam.bo.ExportVSummaryExambo;
import com.hp.exam.service.ClassExamService;
import com.hp.exam.service.StudentExamService;
import com.hp.exam.service.VSummaryExamService;
import com.hp.student.bo.StudentRearchBo;
import com.hp.uclass.service.UClassService;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 晏利花 on 2018/3/20.
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("exam")
public class ClassExamController extends BaseController {
    @Autowired
    private ClassExamService classExamService;
    @Autowired
    private UClassService uClassService;
    @Autowired
    private StudentExamService studentExamService;
    @Autowired
    private VSummaryExamService vSummaryExamService;

    /**
     * 考试查询
     *
     * @param map
     * @param pageNo
     * @param findContent
     * @return
     */
    @RequestMapping(value = "list")
    public ModelAndView list(ModelMap map, Integer pageNo, String findContent,QueryBuffer queryBuffer) {
        Long cid = TokenManager.getToken().getCid();
        if (!cid.equals(MANAGE_COMPANY_ID)) {
            map.put("cid", cid);
        }
        map.put("findContent", findContent);
        map.put("queryDate", queryBuffer.getQueryDate());
        Pagination<UClassExam> page = classExamService.findByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("exam/list", map);
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
     * 获取班级信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "classinfo", method = RequestMethod.GET)
    @ResponseBody
    public UClass getClassForExam(Long id) {
        //查询班级名
        UClass uClass = uClassService.selectByPrimaryKey(id);
        return uClass;
    }

    /**
     * 申请考试
     *
     * @param uClassExam
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addClassExam(UClassExam uClassExam) {
        try {
            studentExamService.insertBatch(uClassExam);
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
     * 记录异常
     *
     * @param uClassExam
     * @return
     */
    @RequestMapping(value = "addExamEvent", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addExamEvent(UClassExam uClassExam) {
        if (uClassExam.getExamevent() == null || uClassExam.getExamevent() == "") {
            uClassExam.setExamevent("无");
        }
        try {
            classExamService.updateExameventByPrimaryKey(uClassExam);
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
     * 导出考试成绩
     *
     * @param map
     * @param pageNo
     * @param findContent
     * @param record
     * @param request
     * @param response
     */
    @RequestMapping(value = "export", method = RequestMethod.GET)
    public void exportFile(ModelMap map, Integer pageNo, String findContent, StudentRearchBo record, HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileName = "班级考试数据汇总" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            Long cid = TokenManager.getToken().getCid();
            map.put("findContent", findContent);

            if (!cid.equals(MANAGE_COMPANY_ID)) {
                map.put("cid", cid);
            }
            Pagination<VSummaryExam> page = vSummaryExamService.findByPage(map, pageNo, -1);

            DecimalFormat df = new DecimalFormat();
            String style = "0.00%";

            List<ExportVSummaryExambo> exambos = new ArrayList<ExportVSummaryExambo>();
            List<VSummaryExam> list = page.getList();
            for (VSummaryExam v : list) {
                VClass vClass = v.getvClass();
                ExportVSummaryExambo vdc = new ExportVSummaryExambo();
                vdc.setSummaryTime(v.getSummaryTime());
                df.applyPattern(style);
                vdc.setSummaryExam(df.format(v.getSummaryExam()));
                if (vClass != null) {
                    vdc.setCompanyName(vClass.getCompanyname());
                    vdc.setClassName(vClass.getClassname());
                    vdc.setJsname(vClass.getJsname());
                    vdc.setBzrname(vClass.getBzrname());
                }
                exambos.add(vdc);
            }

            new ExportExcel("班级教学数据汇总", ExportVSummaryExambo.class).setDataList(exambos).write(request, response, fileName).dispose();
            return;
        } catch (Exception e) {

        }
    }

    /**
     * 删除考试
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "deleteExam", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteExam(String ids) {
        return classExamService.delete(ids);
    }


}
