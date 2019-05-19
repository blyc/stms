package com.hp.headquarters.controller;

import com.google.common.collect.Lists;
import com.hp.common.bo.KeyAndValue;
import com.hp.common.bo.QueryBuffer;
import com.hp.common.controller.BaseController;
import com.hp.common.model.*;
import com.hp.common.utils.DateUtil;
import com.hp.common.utils.LoggerUtils;
import com.hp.company.service.CompanyService;
import com.hp.core.mybatis.page.Pagination;
import com.hp.core.shiro.token.manager.TokenManager;
import com.hp.exam.service.ClassExamService;
import com.hp.headquarters.bo.UReviewExameDetailbo;
import com.hp.headquarters.bo.UReviewProjectDetailbo;
import com.hp.headquarters.service.ReviewCompanyService;
import com.hp.headquarters.service.ReviewExamService;
import com.hp.headquarters.service.ReviewProjectService;
import com.hp.headquarters.service.StandardService;
import com.hp.major.service.MajorService;
import com.hp.project.service.ClassProjectService;
import com.hp.summary.service.SummaryService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.math.BigDecimal.ROUND_HALF_UP;
import static java.math.BigDecimal.ROUND_UP;

/**
 * @Author: boy
 * @Date: 2018/11/06
 * @Description:
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("quarter")
public class QuarterController extends BaseController {

    @Autowired
    private StandardService standardService;

    @Autowired
    private ReviewExamService reviewExamService;

    @Autowired
    private ReviewProjectService reviewProjectService;

    @Autowired
    private ClassExamService classExamService;

    @Autowired
    private ClassProjectService classProjectService;

    @Autowired
    private SummaryService summaryService;

    @Autowired
    private ReviewCompanyService reviewCompanyService;

    @Autowired
    private MajorService majorService;

    @Autowired
    private CompanyService companyService;

    @RequestMapping("list")
    public ModelAndView list(ModelMap map, Integer pageNo, String findContent, QueryBuffer queryBuffer) {
        map.put("findContent", findContent);
        map.put("queryDate", queryBuffer.getQueryDate());
        UUser uUser = TokenManager.getToken();
        Pagination<UStandard> page = standardService.findByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("headquarters/list");
    }

    /**
     * @param uStandard
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addStandard(UStandard uStandard) {
        try {
            UUser uUser = TokenManager.getToken();
            uStandard.setCreateTime(DateUtil.getCurrentDateTime());
            uStandard.setCreateId(uUser.getUid());
            int insert = standardService.insert(uStandard);
            if (insert == 1) {
                resultMap.put("status", 500);
                resultMap.put("message", "数据重复，请检查后再试！");
            } else {
                resultMap.put("status", 200);
                resultMap.put("entity", "添加成功");
            }
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败，请刷新后再试！");
            LoggerUtils.fmtError(getClass(), e, "添加考试报错。");
        }
        return resultMap;
    }


    @RequestMapping(value = "getStandard", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getStandard(UStandard uStandard) {
        try {
            UStandard u = standardService.select(uStandard.getSid());
            if (u == null) {
                resultMap.put("status", 500);
                resultMap.put("message", "添加失败，请刷新后再试！");
                return resultMap;
            }
            resultMap.put("status", 200);
            resultMap.put("entity", u);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败，请刷新后再试！");
            LoggerUtils.fmtError(getClass(), e, "添加考试报错。");
        }
        return resultMap;
    }


    /**
     * @param uStandard
     * @return
     */
    @RequestMapping(value = "updateStandard", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateStandard(UStandard uStandard) {
        try {
            UUser uUser = TokenManager.getToken();
            uStandard.setCreateTime(DateUtil.getCurrentDateTime());
            uStandard.setCreateId(uUser.getUid());
            standardService.update(uStandard);

            resultMap.put("status", 200);
            resultMap.put("entity", "更新成功");
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "更新失败，请刷新后再试！");
            LoggerUtils.fmtError(getClass(), e, "更新月教学指标报错。");
        }
        return resultMap;
    }

    /**
     * 考试查询
     *
     * @param map
     * @param pageNo
     * @param findContent
     * @return
     */
    @RequestMapping(value = "examlist")
    public ModelAndView examlist(ModelMap map, Integer pageNo, String findContent, QueryBuffer queryBuffer) {
        Long cid = TokenManager.getToken().getCid();
        if (!cid.equals(MANAGE_COMPANY_ID)) {
            map.put("cid", cid);
        }
        map.put("findContent", findContent);
        map.put("queryDate", queryBuffer.getQueryDate());
        Pagination<UClassExam> page = classExamService.findReviewByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("headquarters/examlist", map);
    }

    /**
     * @param uReviewExameDetailbos
     * @param ceid
     * @return
     */
    @RequestMapping(value = "examlist/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateReviewExame(@RequestBody List<UReviewExameDetailbo> uReviewExameDetailbos, @PathVariable("id") Long ceid) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("ceid", ceid);
            map.put("uReviewExameDetailbos", uReviewExameDetailbos);
            int ret = reviewExamService.update(map);
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
     * 查看督查考试数据详情
     *
     * @param reid
     * @return
     */
    @RequestMapping(value = "getReviewExamDetails", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getReviewExamDetails(Long reid) {
        List<UReviewExameDetail> uReviewExameDetails = reviewExamService.select(reid);
        resultMap.put("info", uReviewExameDetails);
        return resultMap;
    }


    /**
     * 显示项目列表
     *
     * @param map
     * @param pageNo
     * @param findContent
     * @return
     */
    @RequestMapping(value = "projectlist")
    public ModelAndView projectlist(ModelMap map, Integer pageNo, String findContent, QueryBuffer queryBuffer) {
        Long cid = TokenManager.getToken().getCid();
        map.put("findContent", findContent);
        map.put("queryDate", queryBuffer.getQueryDate());
        if (!cid.equals(MANAGE_COMPANY_ID)) {
            map.put("cid", cid);
        }
        Pagination<UClassProject> page = classProjectService.findReviewByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("headquarters/projectlist", map);
    }

    /**
     * @param uReviewProjectDetailbos
     * @param cpid
     * @return
     */
    @RequestMapping(value = "projectlist/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateReviewProject(@RequestBody List<UReviewProjectDetailbo> uReviewProjectDetailbos, @PathVariable("id") Long cpid) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("cpid", cpid);
            map.put("uReviewProjectDetailbos", uReviewProjectDetailbos);
            int ret = reviewProjectService.update(map);
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
     * 查看督查项目数据详情
     *
     * @param rpid
     * @return
     */
    @RequestMapping(value = "getReviewProejctDetails", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getReviewProejctDetails(Long rpid) {
        List<UReviewProjectDetail> uReviewProjectDetails = reviewProjectService.select(rpid);
        resultMap.put("info", uReviewProjectDetails);
        return resultMap;
    }


    @RequestMapping(value = "companylist")
    public ModelAndView teacherlist(ModelMap map, Integer pageNo, String findContent, QueryBuffer queryBuffer) {
        Long cid = TokenManager.getToken().getCid();
        map.put("findContent", findContent);
        map.put("queryDate", queryBuffer.getQueryDate());

        if (!cid.equals(MANAGE_COMPANY_ID)) {
            map.put("cid", cid);
        }
        Pagination<VSummaryData> page = summaryService.findTeacherByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("headquarters/companylist");
    }

    @RequestMapping(value = "companyquarteslist")
    public ModelAndView companylist(ModelMap map, Integer pageNo, String findContent, QueryBuffer queryBuffer) {
        Long cid = TokenManager.getToken().getCid();
        map.put("findContent", findContent);
        map.put("queryDate", queryBuffer.getQueryDate());

        if (!cid.equals(MANAGE_COMPANY_ID)) {
            map.put("cid", cid);
        }
        Pagination<VReviewCompany> page = reviewCompanyService.findByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("companyquartes/companylist");
    }

    @RequestMapping(value = "addReviewCompany", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addReviewCompany(UReviewCompany uReviewCompany) {
        try {
            int ret = reviewExamService.addReviewCompany(uReviewCompany);
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


    @RequestMapping(value = "getReviewCompany", method = RequestMethod.GET)
    @ResponseBody
    public UReviewCompany getReviewCompany(String reviewTime, Long eid) {
        try {
            UReviewCompany uReviewCompany = reviewExamService.getReviewCompany(reviewTime, eid);

            return uReviewCompany;
        } catch (Exception e) {
            return null;
        }

    }


    @RequestMapping(value = "updateReviewCompany1", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateReviewCompany1(UReviewCompany uReviewCompany) {
        try {
            int ret = reviewExamService.updateReviewCompany(uReviewCompany);
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

    @RequestMapping(value = "updateReviewCompany2", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateReviewCompany2(UReviewCompany uReviewCompany) {
        try {
            uReviewCompany.setHeadflg(1L);
            int ret = reviewExamService.updateReviewCompany(uReviewCompany);
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

    @RequestMapping(value = "import", method = RequestMethod.POST)
    public ModelAndView importFile(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        try {
            ImportExcel ei = new ImportExcel(file, 1, 0);
            // 导入的list
            List<UStandardImport> list = ei.getDataList(UStandardImport.class);

            // 把校区和id放入map中 key为校区名,value为校区id
            List<KeyAndValue> allCompanyForSelect = companyService.findAllCompanyForSelect();
            Map<String, String> allCompanyForSelectMap = new HashMap<>(16);
            for (KeyAndValue keyAndValue : allCompanyForSelect) {
                allCompanyForSelectMap.put(String.valueOf(keyAndValue.getValue()), String.valueOf(keyAndValue.getKey()));
            }

            // 把专业和id放入map中 key为专业名,value为专业id
            List<KeyAndValue> allMajorForSelect = majorService.findAllMajorForSelect();
            Map<String, String> allMajorForSelectMap = new HashMap<>(16);
            for (KeyAndValue keyAndValue : allMajorForSelect) {
                allMajorForSelectMap.put(String.valueOf(keyAndValue.getValue()), String.valueOf(keyAndValue.getKey()));
            }

            // 遍历
            for (int i = 0; i < list.size(); i++) {

                UStandard uStandard = new UStandard();
                if (list.get(i).getStandardTime() == null || "".equals(list.get(i).getStandardTime())) {
                    errorMap.put("msg", "第" + (i + 1) + "条的月份为空！");
                    return redirectImportError();
                } else {
                    uStandard.setStandardTime(list.get(i).getStandardTime().trim());
                }

                if (list.get(i).getGrade() == null || "".equals(list.get(i).getGrade().trim())) {
                    errorMap.put("msg", "第" + (i + 1) + "条的年级为空！");
                    return redirectImportError();
                } else {
                    uStandard.setGrade(list.get(i).getGrade().replace("级", "").trim());
                }

                if (list.get(i).getMid() == null || "".equals(list.get(i).getMid().trim())) {
                    errorMap.put("msg", "第" + (i + 1) + "条的专业为空！");
                    return redirectImportError();
                } else {
                    String s = allMajorForSelectMap.get(list.get(i).getMid().trim());
                    if (s != null) {
                        uStandard.setMid(Long.valueOf(s));
                    } else {
                        errorMap.put("msg", "第" + (i + 1) + "条的专业不存在！");
                        return redirectImportError();
                    }
                }

                // 把为空的数据转换为0
                if (list.get(i).getWeightExam() == null || "".equals(list.get(i).getWeightExam())) list.get(i).setWeightExam(String.valueOf(0));
                uStandard.setWeightExam(Long.valueOf(list.get(i).getWeightExam().trim()));
                if (list.get(i).getStandardExam() == null || "".equals(list.get(i).getStandardExam())) list.get(i).setStandardExam(String.valueOf(0));
                uStandard.setStandardExam(new BigDecimal(list.get(i).getStandardExam().trim()));
                if (list.get(i).getWeightProject() == null || "".equals(list.get(i).getWeightProject())) list.get(i).setWeightProject(String.valueOf(0));
                uStandard.setWeightProject(Long.valueOf(list.get(i).getWeightProject().trim()));
                if (list.get(i).getStandardProject() == null || "".equals(list.get(i).getStandardProject())) list.get(i).setStandardProject(String.valueOf(0));
                uStandard.setStandardProject(new BigDecimal(list.get(i).getStandardProject().trim()));
                if (list.get(i).getWeightAttendance() == null || "".equals(list.get(i).getWeightAttendance())) list.get(i).setWeightAttendance(String.valueOf(0));
                uStandard.setWeightAttendance(Long.valueOf(list.get(i).getWeightAttendance().trim()));
                if (list.get(i).getStandardAttendance() == null || "".equals(list.get(i).getStandardAttendance())) list.get(i).setStandardAttendance(String.valueOf(0));
                uStandard.setStandardAttendance(new BigDecimal(list.get(i).getStandardAttendance().trim()));
                if (list.get(i).getWeightSatisfaction() == null || "".equals(list.get(i).getWeightSatisfaction())) list.get(i).setWeightSatisfaction(String.valueOf(0));
                uStandard.setWeightSatisfaction(Long.valueOf(list.get(i).getWeightSatisfaction().trim()));
                if (list.get(i).getStandardSatisfaction() == null || "".equals(list.get(i).getStandardSatisfaction())) list.get(i).setStandardSatisfaction(String.valueOf(0));
                uStandard.setStandardSatisfaction(new BigDecimal(list.get(i).getStandardSatisfaction().trim()));
                if (list.get(i).getWeightSchool() == null || "".equals(list.get(i).getWeightSchool())) list.get(i).setWeightSchool(String.valueOf(0));
                uStandard.setWeightSchool(Long.valueOf(list.get(i).getWeightSchool().trim()));
                if (list.get(i).getStandardSchool() == null || "".equals(list.get(i).getStandardSchool())) list.get(i).setStandardSchool(String.valueOf(0));
                uStandard.setStandardSchool(new BigDecimal(list.get(i).getStandardSchool().trim()));
                if (list.get(i).getWeightHead() == null || "".equals(list.get(i).getWeightHead())) list.get(i).setWeightHead(String.valueOf(0));
                uStandard.setWeightHead(Long.valueOf(list.get(i).getWeightHead().trim()));
                if (list.get(i).getStandardHead() == null || "".equals(list.get(i).getStandardHead())) list.get(i).setStandardHead(String.valueOf(0));
                uStandard.setStandardHead(new BigDecimal(list.get(i).getStandardHead().trim()));

                // 计算出计算结果
                uStandard.setResult(BigDecimal.valueOf(new Float(uStandard.getWeightExam()) * (new Float(String.valueOf(uStandard.getStandardExam())) / 100) + new Float(uStandard.getWeightProject()) * (new Float(String.valueOf(uStandard.getStandardProject())) / 100)
                        + new Float(uStandard.getWeightAttendance()) * (new Float(String.valueOf(uStandard.getStandardAttendance())) / 100) + new Float(uStandard.getWeightSatisfaction()) * (new Float(String.valueOf(uStandard.getStandardSatisfaction())) / 100)
                        + new Float(uStandard.getWeightSchool()) * (new Float(String.valueOf(uStandard.getStandardSchool())) / 100) + new Float(uStandard.getWeightHead()) * (new Float(String.valueOf(uStandard.getStandardHead())) / 100)));
                // 保留两位
                uStandard.setResult(uStandard.getResult().setScale(2, ROUND_HALF_UP));
                // 计算出考核基数
                uStandard.setBase(uStandard.getResult().setScale(0, ROUND_UP));

                // 判断校区
                if (list.get(i).getScope() == null || "".equals(list.get(i).getScope().trim())) {
                    errorMap.put("msg", "第" + (i + 1) + "条的校区为空！");
                    return redirectImportError();
                } else {
                    // 根据-分割校区
                    StringBuilder cids = new StringBuilder();
                    String[] split = list.get(i).getScope().trim().split("-");
                    for (String s : split) {
                        String s1 = allCompanyForSelectMap.get(s);
                        if (s1 != null) {
                            cids.append(s1).append(",");
                        } else {
                            errorMap.put("msg", "第" + (i + 1) + "条的" + s + "校区不存在！");
                            return redirectImportError();
                        }
                    }
                    // cids 为校区字符串,增加会做判断
                    uStandard.setCids(cids.substring(0, cids.length() - 1));

                    UUser uUser = TokenManager.getToken();
                    uStandard.setCreateTime(DateUtil.getCurrentDateTime());
                    uStandard.setCreateId(uUser.getUid());
                    int insert = standardService.insert(uStandard);
                    if (insert == 1) {
                        errorMap.put("msg", "第" + (i + 1) + "条的数据重复未能添加请注意检查,该数据之前的数据已插入！");
                        return redirectImportError();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorMap.put("msg", "异常错误！");
            return redirectImportError();
        }
        String basePath = request.getContextPath();
        return new ModelAndView(new RedirectView(basePath + "/quarter/list.shtml"));
    }

    /**
     * 下载空模板
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "import/template", method = RequestMethod.GET)
    public void downtemplate(HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileName = "月度考核指标导入模板.xlsx";
            List<UStandard> list = Lists.newArrayList();
            new ExportExcel("月度考核指标导入模板", UStandardImport.class, 2).write(request, response, fileName).dispose();
            return;
        } catch (Exception e) {
            return;
        }
    }
}
