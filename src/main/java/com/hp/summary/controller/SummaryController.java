package com.hp.summary.controller;

import com.hp.common.bo.KeyAndValue;
import com.hp.common.bo.QueryBuffer;
import com.hp.common.controller.BaseController;
import com.hp.common.model.VSummaryData;
import com.hp.common.model.VSummaryResult;
import com.hp.company.service.CompanyService;
import com.hp.core.mybatis.page.Pagination;
import com.hp.core.shiro.token.manager.TokenManager;
import com.hp.summary.bo.*;
import com.hp.summary.service.SummaryResultService;
import com.hp.summary.service.SummaryService;
import com.hp.utils.DateUtils;
import com.hp.utils.excel.ExportExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: boy
 * @Date: 2018/10/28
 * @Description:
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("summary")
public class SummaryController extends BaseController {
    @Autowired
    private SummaryService summaryService;

    @Autowired
    private SummaryResultService summaryResultService;

    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "classlist")
    public ModelAndView classlist(ModelMap map, Integer pageNo, String findContent, QueryBuffer queryBuffer) {
        Long cid = TokenManager.getToken().getCid();
        map.put("findContent", findContent);
        map.put("queryDate", queryBuffer.getQueryDate());

        if (!cid.equals(MANAGE_COMPANY_ID)) {
            map.put("cid", cid);
        }
        Pagination<VSummaryData> page = summaryService.findByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("summary/classlist");
    }

    @RequestMapping(value = "teacherlist")
    public ModelAndView teacherlist(ModelMap map, Integer pageNo, String findContent, QueryBuffer queryBuffer) {
        Long cid = TokenManager.getToken().getCid();
        map.put("findContent", findContent);
        map.put("queryDate", queryBuffer.getQueryDate());

        if (!cid.equals(MANAGE_COMPANY_ID)) {
            map.put("cid", cid);
        }
        Pagination<VSummaryData> page = summaryService.findTeacherByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("summary/teacherlist");
    }

    @RequestMapping(value = "majorlist")
    public ModelAndView majorlist(ModelMap map, Integer pageNo, String findContent, QueryBuffer queryBuffer) {
        Long cid = TokenManager.getToken().getCid();
        map.put("findContent", findContent);
        map.put("queryDate", queryBuffer.getQueryDate());

        if (!cid.equals(MANAGE_COMPANY_ID)) {
            map.put("cid", cid);
        }
        Pagination<VSummaryData> page = summaryService.findMajorByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("summary/majorlist");
    }

    @RequestMapping(value = "studentlist")
    public ModelAndView studentlist(ModelMap map, Integer pageNo,String campusid, String findContent, QueryBuffer queryBuffer) {
        Long cid = TokenManager.getToken().getCid();
        map.put("findContent", findContent);
        if (queryBuffer.getQueryDate() == null) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
            String date = df.format(new Date());
            queryBuffer.setQueryDate(date);
        }
        map.put("queryDate", queryBuffer.getQueryDate());
        if (!cid.equals(MANAGE_COMPANY_ID)) {
            map.put("cid", cid);
        } else {
            // 如果是超级管理员
            // 则默认第一次查询为总部下一位的校区
            if (campusid==null) {
                List<KeyAndValue> allCompanyForSelect = companyService.findAllCompanyForSelect();
                map.put("cid",allCompanyForSelect.get(1).getKey());
            }else {
                map.put("cid",campusid);
            }
        }
        Pagination<VSummaryData> page = summaryService.findStudentByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("summary/studentlist");
    }

    @RequestMapping(value = "resultlist")
    public ModelAndView techerResultlist(ModelMap map, Integer pageNo, String findContent, QueryBuffer queryBuffer) {
        Long cid = TokenManager.getToken().getCid();
        map.put("findContent", findContent);
        map.put("queryDate", queryBuffer.getQueryDate());

        if (!cid.equals(MANAGE_COMPANY_ID)) {
            map.put("cid", cid);
        }
        Pagination<VSummaryResult> page = summaryResultService.findByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("summary/resultlist");
    }

    @RequestMapping(value = "subsidylist")
    public ModelAndView techerSubsidylist(ModelMap map, Integer pageNo, String findContent, QueryBuffer queryBuffer) {
        Long cid = TokenManager.getToken().getCid();
        map.put("findContent", findContent);
        map.put("queryDate", queryBuffer.getQueryDate());

        if (!cid.equals(MANAGE_COMPANY_ID)) {
            map.put("cid", cid);
        }
        Pagination<VSummaryResult> page = summaryResultService.findByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("summary/subsidylist");
    }

    @RequestMapping(value = "export", method = RequestMethod.GET)
    public void exportFile(ModelMap map, Integer pageNo, String findContent, QueryBuffer queryBuffer, HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileName = "班级教学数据汇总" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            Long cid = TokenManager.getToken().getCid();
            findContent = new String(findContent.getBytes("ISO8859-1"), "utf-8");
            map.put("findContent", findContent);
            map.put("queryDate", queryBuffer.getQueryDate());

            if (!cid.equals(MANAGE_COMPANY_ID)) {
                map.put("cid", cid);
            }
            Pagination<VSummaryData> page = summaryService.findByPage(map, pageNo, -1);

            DecimalFormat df = new DecimalFormat();
            String style = "0.00%";
            df.applyPattern(style);

            List<VSummaryDataClass> vSummaryDataClasses = new ArrayList<VSummaryDataClass>();
            List<VSummaryData> list = page.getList();
            for (VSummaryData v : list) {
                VSummaryDataClass vdc = new VSummaryDataClass();
                vdc.setSummaryDate(v.getSummaryDate());
                vdc.setCompanyname(v.getCompanyname());
                vdc.setClassname(v.getClassname());
                vdc.setJsname(v.getJsname());
                vdc.setSummaryAttendance(df.format(v.getSummaryAttendance()));
                vdc.setSummaryExam(df.format(v.getSummaryExam()));
                vdc.setSummaryProject(df.format(v.getSummaryProject()));
                vdc.setSummarySatisfaction(df.format(v.getSummarySatisfaction()));

                vSummaryDataClasses.add(vdc);
            }

            new ExportExcel("班级教学数据汇总", VSummaryDataClass.class).setDataList(vSummaryDataClasses).write(request, response, fileName).dispose();
            return;
        } catch (Exception e) {

        }
    }

    @RequestMapping(value = "exportTeacher", method = RequestMethod.GET)
    public void exportTeacherFile(ModelMap map, Integer pageNo, String findContent, QueryBuffer queryBuffer, HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileName = "讲师教学数据汇总" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            Long cid = TokenManager.getToken().getCid();
            findContent = new String(findContent.getBytes("ISO8859-1"), "utf-8");
            map.put("findContent", findContent);
            map.put("queryDate", queryBuffer.getQueryDate());

            if (!cid.equals(MANAGE_COMPANY_ID)) {
                map.put("cid", cid);
            }
            Pagination<VSummaryData> page = summaryService.findTeacherByPage(map, pageNo, -1);

            DecimalFormat df = new DecimalFormat();
            String style = "0.00%";
            df.applyPattern(style);

            List<VSummaryDataTeacher> vSummaryDataTeachers = new ArrayList<VSummaryDataTeacher>();
            List<VSummaryData> list = page.getList();
            for (VSummaryData v : list) {
                VSummaryDataTeacher vdc = new VSummaryDataTeacher();
                vdc.setSummaryDate(v.getSummaryDate());
                vdc.setCompanyname(v.getCompanyname());
                vdc.setJsname(v.getJsname());
                vdc.setMajortype(v.getMajortype());
                vdc.setSummaryAttendance(df.format(v.getSummaryAttendance()));
                vdc.setSummaryExam(df.format(v.getSummaryExam()));
                vdc.setSummaryProject(df.format(v.getSummaryProject()));
                vdc.setSummarySatisfaction(df.format(v.getSummarySatisfaction()));
                vdc.setReviewProject(df.format(v.getReviewProject()));
                vdc.setReviewExam(df.format(v.getReviewExam()));
                vdc.setReviewCompany(df.format(v.getReviewCompany()));
                vdc.setReviewHead(df.format(v.getReviewHead()));
                vSummaryDataTeachers.add(vdc);
            }
            map.put("page", page);
            new ExportExcel("讲师教学数据汇总", VSummaryDataTeacher.class).setDataList(vSummaryDataTeachers).write(request, response, fileName).dispose();
            return;
        } catch (Exception e) {

        }
    }

    @RequestMapping(value = "exportMajor", method = RequestMethod.GET)
    public void exportMajorFile(ModelMap map, Integer pageNo, String findContent, QueryBuffer queryBuffer, HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileName = "专业教学数据汇总" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            Long cid = TokenManager.getToken().getCid();
            findContent = new String(findContent.getBytes("ISO8859-1"), "utf-8");
            map.put("findContent", findContent);
            map.put("queryDate", queryBuffer.getQueryDate());

            if (!cid.equals(MANAGE_COMPANY_ID)) {
                map.put("cid", cid);
            }
            Pagination<VSummaryData> page = summaryService.findMajorByPage(map, pageNo, -1);

            DecimalFormat df = new DecimalFormat();
            String style = "0.00%";
            df.applyPattern(style);

            List<VSummaryDataMajor> vSummaryDataMajors = new ArrayList<VSummaryDataMajor>();
            List<VSummaryData> list = page.getList();
            for (VSummaryData v : list) {
                VSummaryDataMajor vdc = new VSummaryDataMajor();
                vdc.setSummaryDate(v.getSummaryDate());
                vdc.setCompanyname(v.getCompanyname());
                vdc.setSummaryAttendance(df.format(v.getSummaryAttendance()));
                vdc.setSummaryExam(df.format(v.getSummaryExam()));
                vdc.setSummaryProject(df.format(v.getSummaryProject()));
                vdc.setSummarySatisfaction(df.format(v.getSummarySatisfaction()));
                vSummaryDataMajors.add(vdc);
            }

            new ExportExcel("专业教学数据汇总", VSummaryDataMajor.class).setDataList(vSummaryDataMajors).write(request, response, fileName).dispose();
            return;
        } catch (Exception e) {

        }
    }

    @RequestMapping(value = "exportStudent", method = RequestMethod.GET)
    public void exportStudentFile(ModelMap map, Integer pageNo, String findContent, QueryBuffer queryBuffer, HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileName = "学生出勤数据汇总" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            Long cid = TokenManager.getToken().getCid();
            map.put("findContent", findContent);
            map.put("queryDate", queryBuffer.getQueryDate());

            if (!cid.equals(MANAGE_COMPANY_ID)) {
                map.put("cid", cid);
            }
            Pagination<VSummaryData> page = summaryService.findStudentByPage(map, pageNo, -1);

            DecimalFormat df = new DecimalFormat();
            String style = "0.00%";
            df.applyPattern(style);

            List<VSummaryDataStudent> vSummaryDataStudents = new ArrayList<VSummaryDataStudent>();
            List<VSummaryData> list = page.getList();
            for (VSummaryData v : list) {
                VSummaryDataStudent vss = new VSummaryDataStudent();
                vss.setRecordTime(v.getRecordTime());
                vss.setCampusName(v.getCampusName());
                vss.setGrade(v.getGrade());
                vss.setMajorName(v.getMajorName());
                vss.setClassName(v.getClassName());
                vss.setStudentName(v.getStudentName());
                vss.setAttendanceRate(df.format(v.getAttendanceRate()));
                vss.setBelateCount(v.getBelateCount());
                vss.setTruantCount(v.getTruantCount());
                vss.setLeaveCount(v.getLeaveCount());
                vss.setLeaveearlyCount(v.getLeaveearlyCount());
                vSummaryDataStudents.add(vss);
            }

            new ExportExcel("学生出勤数据汇总", VSummaryDataStudent.class).setDataList(vSummaryDataStudents).write(request, response, fileName).dispose();
            return;
        } catch (Exception e) {

        }
    }

    @RequestMapping(value = "exportResult", method = RequestMethod.GET)
    public void exportResultFile(ModelMap map, Integer pageNo, String findContent, QueryBuffer queryBuffer, HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileName = "月教学考核结果" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            Long cid = TokenManager.getToken().getCid();
            findContent = new String(findContent.getBytes("ISO8859-1"), "utf-8");
            map.put("findContent", findContent);
            map.put("queryDate", queryBuffer.getQueryDate());

            if (!cid.equals(MANAGE_COMPANY_ID)) {
                map.put("cid", cid);
            }
            Pagination<VSummaryResult> page = summaryResultService.findByPage(map, pageNo, -1);


            DecimalFormat df = new DecimalFormat();
            String style = "0.00%";
            df.applyPattern(style);

            DecimalFormat df2 = new DecimalFormat();
            String style2 = "0.00";
            df2.applyPattern(style2);

            List<VSummaryDataResult> vSummaryDataResults = new ArrayList<VSummaryDataResult>();
            List<VSummaryResult> list = page.getList();
            for (VSummaryResult v : list) {
                VSummaryDataResult vdc = new VSummaryDataResult();
                vdc.setSummaryDate(v.getSummaryDate());
                vdc.setCompanyname(v.getCompanyname());
                vdc.setMajortype(v.getMajortype());
                vdc.setJsname(v.getJsname());
                vdc.setSummaryAttendance(df.format(v.getSummaryAttendance()));
                vdc.setReviewExam(df.format(v.getReviewExam()));
                vdc.setReviewProject(df.format(v.getReviewProject()));
                vdc.setReviewHead(df.format(v.getReviewHead()));
                vdc.setReviewCompany(df.format(v.getReviewCompany()));
                vdc.setSummarySatisfaction(df.format(v.getSummarySatisfaction()));
                vdc.setAvgBase(df2.format(v.getAvgBase()));
                vdc.setBase(df2.format(v.getBase()));
                vSummaryDataResults.add(vdc);
            }

            new ExportExcel("月教学考核结果", VSummaryDataResult.class).setDataList(vSummaryDataResults).write(request, response, fileName).dispose();
            return;
        } catch (Exception e) {

        }
    }

    @RequestMapping(value = "exportSubsidy", method = RequestMethod.GET)
    public void exportSubsidy(ModelMap map, Integer pageNo, String findContent, QueryBuffer queryBuffer, HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileName = "月讲师授课津贴汇总" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            Long cid = TokenManager.getToken().getCid();
            findContent = new String(findContent.getBytes("ISO8859-1"), "utf-8");
            map.put("findContent", findContent);
            map.put("queryDate", queryBuffer.getQueryDate());

            if (!cid.equals(MANAGE_COMPANY_ID)) {
                map.put("cid", cid);
            }
            Pagination<VSummaryResult> page = summaryResultService.findByPage(map, pageNo, -1);


            DecimalFormat df = new DecimalFormat();
            String style = "0.00%";
            df.applyPattern(style);

            DecimalFormat df2 = new DecimalFormat();
            String style2 = "0.00";
            df2.applyPattern(style2);

            List<VSummaryDataSubsidy> vSummaryDataSubsidies = new ArrayList<VSummaryDataSubsidy>();
            List<VSummaryResult> list = page.getList();
            for (VSummaryResult v : list) {
                VSummaryDataSubsidy vdc = new VSummaryDataSubsidy();
                vdc.setSummaryDate(v.getSummaryDate());
                vdc.setCompanyname(v.getCompanyname());
                vdc.setMajortype(v.getMajortype());
                vdc.setJsname(v.getJsname());
                vdc.setSummaryAttendance(df.format(v.getSummaryAttendance()));
                vdc.setReviewExam(df.format(v.getReviewExam()));
                vdc.setReviewProject(df.format(v.getReviewProject()));
                vdc.setReviewHead(df.format(v.getReviewHead()));
                vdc.setReviewCompany(df.format(v.getReviewCompany()));
                vdc.setSummarySatisfaction(df.format(v.getSummarySatisfaction()));
                vdc.setAvgBase(df2.format(v.getAvgBase()));
                vdc.setBase(df2.format(v.getBase()));

                vdc.setSubsidy(df2.format(v.getSubsidy()));
                vdc.setDayCount(df2.format(v.getDayCount()));
                vdc.setCourseSubsidy(df2.format(v.getCourseSubsidy()));
                if (v.getDiffBase().compareTo(BigDecimal.ZERO) == 1) {
                    vdc.setBase1(df2.format(0));
                    vdc.setBase2(df2.format(v.getBase()));
                } else {
                    vdc.setBase1(df2.format(v.getBase()));
                    vdc.setBase2(df2.format(0));
                }
                BigDecimal c = new BigDecimal(v.getCourseSubsidy()).subtract(v.getBase());
                if (c.compareTo(BigDecimal.ZERO) == 1) {
                    vdc.setDiffBase(df2.format(c));
                } else {
                    vdc.setDiffBase(df2.format(0));
                }

                vSummaryDataSubsidies.add(vdc);
            }

            new ExportExcel("月讲师授课津贴汇总", VSummaryDataSubsidy.class).setDataList(vSummaryDataSubsidies).write(request, response, fileName).dispose();
            return;
        } catch (Exception e) {

        }
    }
}
