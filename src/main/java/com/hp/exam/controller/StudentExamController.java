package com.hp.exam.controller;

import com.hp.common.controller.BaseController;
import com.hp.common.model.UStudent;
import com.hp.common.model.UStudentExam;
import com.hp.common.utils.LoggerUtils;
import com.hp.exam.bo.UCLassExamebo;
import com.hp.exam.bo.UStudentExambo;
import com.hp.exam.service.StudentExamService;
import com.hp.utils.DateUtils;
import com.hp.utils.excel.ExportExcel;
import com.hp.utils.excel.ImportExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 晏利花 on 2018/3/22.
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("studentexamlist")
public class StudentExamController extends BaseController {
    @Autowired
    private StudentExamService studentExamService;

    /**
     * 参加考试学生姓名
     *
     * @param ceid
     * @return
     */
    @RequestMapping(value = "getStudentName", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getStudentName(Long ceid) {
        List<UStudentExam> uStudentExamList = studentExamService.findExamStudentName(ceid);
        resultMap.put("info", uStudentExamList);
        return resultMap;
    }

    /**
     * 登记学生考试成绩
     *
     * @param studentExambos
     * @param ceid
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateWritten(@RequestBody List<UStudentExambo> studentExambos, @PathVariable("id") Long ceid) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("ceid", ceid);
            map.put("studentExambos", studentExambos);
            int ret = studentExamService.updateByPrimaryKey(map);
            if (ret < 0) {
                resultMap.put("status", 500);
                resultMap.put("message", "添加失败，请刷新后再试！");
            } else {
                resultMap.put("status", 200);
            }
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败，请刷新后再试！");
            LoggerUtils.fmtError(getClass(), e, "添加出勤报错。");
        }
        return resultMap;
    }

    /**
     * 查看学生考试成绩
     *
     * @param ceid
     * @return
     */
    @RequestMapping(value = "getExamDetails", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getExamDetails(Long ceid) {

        List<UStudentExam> uStudentExamList = studentExamService.findExamDetails(ceid);
        resultMap.put("info", uStudentExamList);
        UCLassExamebo ucLassExamebo = studentExamService.findClassExameBo(ceid);
        resultMap.put("gradeinfo", ucLassExamebo);
        return resultMap;
    }

    /**
     * @param map
     * @param pageNo
     * @param findContent
     * @param request
     * @param response
     */
    @RequestMapping(value = "import/template", method = RequestMethod.GET)
    public void exportFile(ModelMap map, Integer pageNo, String findContent, HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            Long ceid = Long.parseLong(request.getParameter("ceid"));
            String className = new String(request.getParameter("classname").getBytes("ISO-8859-1"), "utf-8");

            String fileName = className + "考试数据模板" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            List<UStudentExam> uStudentExamList = studentExamService.findExamStudentName(ceid);
            List<UStudentExambo> exportList = new ArrayList<UStudentExambo>();

            for (UStudentExam uStudentExam : uStudentExamList) {
                UStudentExambo ue = new UStudentExambo();
                ue.setUserName(uStudentExam.getuStudent().getName());
                exportList.add(ue);
            }

            new ExportExcel(className + "考试数据", UStudentExambo.class).setDataList(exportList).write(request, response, fileName).dispose();
            return;
        } catch (Exception e) {

        }
    }

    /**
     * 导入考试成绩
     *
     * @param file
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public ModelAndView importFile(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        int successNum = 0;
        int failureNum = 0;
        boolean flg = false;
        try {
            ImportExcel ei = new ImportExcel(file, 1, 0);

            List<UStudentExambo> studentExambos = ei.getDataList(UStudentExambo.class);

            Long ceid = Long.parseLong(request.getParameter("ceid"));
            List<UStudentExam> uStudentExamList = studentExamService.findExamStudentName(ceid);
            for (UStudentExambo u : studentExambos) {

                if(u.getUserName()==null || u.getUserName().equals("")){
                    errorMap.put("msg", "学生姓名存在空值!");
                    return redirectImportError();
                }


                flg = false;
                for (UStudentExam ue : uStudentExamList) {
                    UStudent student = ue.getuStudent();
                    if (u.getUserName().equals(student.getName())) {
                        u.setUseid(ue.getUseid());
                        flg = true;
                        break;
                    }
                }

                if (flg == false) {
                    errorMap.put("msg", u.getUserName() + "不存在！");
                    return redirectImportError();
                }

                if(u.getExamgrade()==null || u.getExamgrade().equals("") || u.getExamgrade()<0){
                    errorMap.put("msg", u.getUserName() + "的成绩错误！");
                    return redirectImportError();
                }

                if(u.getEaddr()==null || u.getEaddr().equals("")){
                    errorMap.put("msg", u.getUserName() + "的试卷地址为空！");
                    return redirectImportError();
                }
            }

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("ceid", ceid);
            map.put("studentExambos", studentExambos);
            studentExamService.updateByPrimaryKey(map);
        } catch (Exception e) {
            e.printStackTrace();
            return redirectImportError();
        }
        String basePath = request.getContextPath();
        return new ModelAndView(new RedirectView(basePath+"/exam/list.shtml"));
    }
}
