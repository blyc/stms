package com.hp.project.controller;

import com.hp.common.controller.BaseController;
import com.hp.common.model.UStudent;
import com.hp.common.model.UStudentProject;
import com.hp.common.utils.LoggerUtils;
import com.hp.project.bo.UStudentProjectbo;
import com.hp.project.service.StudentProjectService;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 金梦杰 on 2018/4/7/007.
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("project")
public class StudentProjectController extends BaseController {

    @Autowired
    private StudentProjectService studentProjectService;


    /***
     * 获取学生信息
     * @param cpid
     * @return
     */
    @RequestMapping(value = "getstudentname", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getstudentname(Long cpid) {
        List<UStudentProject> uStudentProjects = studentProjectService.findProjectStudentName(cpid);
        resultMap.put("info", uStudentProjects);
        return resultMap;
    }

    /**
     * 登记考试成绩
     *
     * @param uStudentProjectbos
     * @param cpid
     * @return
     */
    @RequestMapping(value = "updateGrade/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateWritten(@RequestBody List<UStudentProjectbo> uStudentProjectbos, @PathVariable("id") Long cpid) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("cpid", cpid);
            map.put("uStudentProjectbos", uStudentProjectbos);
            int ret = studentProjectService.updateProjectByPrimaryKeyService(map);

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
     * 查询项目完成情况
     *
     * @param cpid
     * @return
     */
    @RequestMapping(value = "getprojectdetails", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getprojectdetails(Long cpid) {
        try {
            List<UStudentProject> uStudentProjectList = studentProjectService.showStudentProjectQualifiedInfoService(cpid);
            resultMap.put("status", 200);
            resultMap.put("info", uStudentProjectList);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "查询出现异常");
            LoggerUtils.fmtError(getClass(), e, "查询出现异常");
        }
        return resultMap;
    }


    /**
     * 下载项目模板
     *
     * @param map
     * @param pageNo
     * @param findContent
     * @param request
     * @param response
     */
    @RequestMapping(value = "import/template", method = RequestMethod.GET)
    public void exportFile(ModelMap map, Integer pageNo, String findContent, HttpServletRequest request, HttpServletResponse response) {
        try {
            Long cpid = Long.parseLong(request.getParameter("cpid"));
            String className = new String(request.getParameter("classname").getBytes("ISO-8859-1"), "utf-8");


            String fileName = className + "项目数据模板" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            List<UStudentProject> uStudentProjects = studentProjectService.findProjectStudentName(cpid);
            List<UStudentProjectbo> exportList = new ArrayList<UStudentProjectbo>();

            for (UStudentProject uStudentProject : uStudentProjects) {
                UStudentProjectbo ue = new UStudentProjectbo();
                ue.setUname(uStudentProject.getuStudent().getName());
                exportList.add(ue);
            }

            new ExportExcel(className + "项目数据", UStudentProjectbo.class).setDataList(exportList).write(request, response, fileName).dispose();
            return;
        } catch (Exception e) {

        }
    }

    /**
     * 导入项目成绩
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

            List<UStudentProjectbo> studentProjectbos = ei.getDataList(UStudentProjectbo.class);

            Long cpid = Long.parseLong(request.getParameter("cpid"));
            List<UStudentProject> uStudentProjects = studentProjectService.findProjectStudentName(cpid);

            for (UStudentProjectbo u : studentProjectbos) {

                if(u.getUname()==null || u.getUname().equals("")){
                    errorMap.put("msg", "学生姓名存在空值!");
                    return redirectImportError();
                }

                flg = false;

                for (UStudentProject ue : uStudentProjects) {
                    UStudent student = ue.getuStudent();
                    if (u.getUname().equals(student.getName())) {
                        u.setSpid(ue.getSpid());
                        flg = true;
                        break;
                    }
                }


                if (flg == false) {
                    errorMap.put("msg", u.getUname() + "不存在！");
                    return redirectImportError();
                }

                if (u.getStrvsubmit() != null && u.getStrvsubmit().trim().equals("是")) {
                    u.setVsubmit(1L);
                } else  {
                    u.setVsubmit(0L);
                }


                if (u.getStrvpass() != null && u.getStrvpass().trim().equals("是")) {
                    u.setVpass(1L);
                } else {
                    u.setVpass(0L);
                }

                //没有提交
                if (u.getVsubmit() == 0) {
                    if (u.getVaddr() != null && !u.getVaddr().trim().equals("")) {
                        errorMap.put("msg", u.getUname() + "的\"项目讲解提交视频地址\"不为空!");
                        return redirectImportError();
                    }

                    if (u.getVtime() != null && !u.getVtime().trim().equals("")) {
                        errorMap.put("msg", u.getUname() + "的\"时长\"不为空!");
                        return redirectImportError();
                    }

                    if (u.getDrate() != null && !u.getDrate().equals("")) {
                        errorMap.put("msg", u.getUname() + "的\"项目视频提交率\"不为空!");
                        return redirectImportError();
                    }

                    if (u.getVpass() == 1) {
                        errorMap.put("msg", u.getUname() + "的项目\"视频是否提交\"和\"视频是否合格\"填写错误!");
                        return redirectImportError();
                    }

                } else {
                    if (u.getVaddr() == null || u.getVaddr().trim().equals("")) {
                        errorMap.put("msg", u.getUname() + "的\"项目讲解提交视频地址\"为空!");
                        return redirectImportError();
                    }

                    if (u.getVtime() == null || u.getVtime().trim().equals("")) {
                        errorMap.put("msg", u.getUname() + "的\"时长\"为空!");
                        return redirectImportError();
                    }

                    if (u.getDrate() == null || u.getDrate().equals("")) {
                        errorMap.put("msg", u.getUname() + "的\"项目视频提交率\"为空!");
                        return redirectImportError();
                    }
                }


                if (u.getDrate() != null && !u.getDrate().equals("")) {
                    u.setRate(new BigDecimal(u.getDrate() * 100));
                }

                if (u.getDcoderate() != null && !u.getDcoderate().equals("")) {
                    u.setCoderate(new BigDecimal(u.getDcoderate() * 100));
                }

                successNum++;
            }

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("cpid", cpid);
            map.put("uStudentProjectbos", studentProjectbos);
            studentProjectService.updateProjectByPrimaryKeyService(map);

        } catch (Exception e) {
            e.printStackTrace();
            return redirectImportError();
        }

        String basePath = request.getContextPath();
        return new ModelAndView(new RedirectView(basePath+"/project/list.shtml"));
    }

}
