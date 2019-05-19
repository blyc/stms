package com.hp.student.controller;

import com.google.common.collect.Lists;
import com.hp.common.bo.KeyAndValue;
import com.hp.common.controller.BaseController;
import com.hp.common.model.UClass;
import com.hp.common.model.UStudent;
import com.hp.common.model.UUser;
import com.hp.common.utils.InfoVerify;
import com.hp.common.utils.LoggerUtils;
import com.hp.company.service.CompanyService;
import com.hp.core.mybatis.page.Pagination;
import com.hp.core.shiro.token.manager.TokenManager;
import com.hp.major.service.MajorService;
import com.hp.student.bo.StudentRearchBo;
import com.hp.student.service.StudentService;
import com.hp.uclass.service.UClassService;
import com.hp.utils.DateUtils;
import com.hp.utils.excel.ExportExcel;
import com.hp.utils.excel.ImportExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * 学员信息controller
 *
 * @Author: boy
 * @Date: 2018/02/10
 * @Description:
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("student")
public class StudentController extends BaseController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private MajorService majorService;

    @Autowired
    private UClassService uClassService;

    @Autowired
    private CompanyService companyService;

    /**
     * 学员信息显示
     *
     * @param map
     * @param pageNo
     * @param findContent
     * @return
     */
    @RequestMapping(value = "list")
    public ModelAndView list(ModelMap map, Integer pageNo, String findContent, StudentRearchBo record) {
        Long cid = TokenManager.getToken().getCid();
        if (!cid.equals(MANAGE_COMPANY_ID)) {
            map.put("cid", cid);
        }

        /*一般查询*/
        map.put("findContent", findContent);

        /*高级查询*/
        if (record != null) {
            if (record.getMid() != null) {
                map.put("mid", record.getMid());
            }
            if (record.getCid() != null) {
                map.put("cid", record.getCid());
            }
            if (record.getCname() != null) {
                map.put("cname", record.getCname());
            }
            if (record.getJsname() != null) {
                map.put("jsname", record.getJsname());
            }
            if (record.getBzrname() != null) {
                map.put("bzrname", record.getBzrname());
            }
            if (record.getGrade() != null) {
                map.put("grade", record.getGrade());
            }
            map.put("reachStudentBo", record);
        }

        Pagination<UStudent> page = studentService.findByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("student/list");
    }

    /**
     * 新增学员
     *
     * @param uStudent
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addStudent(UStudent uStudent) {
        try {
            if (uStudent == null) {
                resultMap.put("status", 500);
                resultMap.put("message", "参数错误！");
                return resultMap;
            }
            UStudent entity = studentService.insertSelective(uStudent);
            if (null == entity) {
                resultMap.put("status", 500);
                resultMap.put("message", "身份证号已经存在！");
                return resultMap;
            }
            resultMap.put("status", 200);
            resultMap.put("entity", entity);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败，请刷新后再试！");
            LoggerUtils.fmtError(getClass(), e, "添加用户报错。source[%s]", uStudent.toString());
        }
        return resultMap;
    }

    /**
     * 更新学员信息
     *
     * @param uStudent
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updateStudent(UStudent uStudent) {
        try {
            if (uStudent == null) {
                resultMap.put("status", 500);
                resultMap.put("message", "参数错误！");
                return resultMap;
            }
            int ret = studentService.updateByPrimaryKeySelective(uStudent);

            if (ret < 0) {
                resultMap.put("status", 500);
                resultMap.put("message", "更新失败！");
                return resultMap;
            }
            resultMap.put("status", 200);
            resultMap.put("entity", uStudent);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "更新失败，请刷新后再试！");
            LoggerUtils.fmtError(getClass(), e, "更新学生报错。source[%s]", uStudent.toString());
        }
        return resultMap;
    }

    /**
     * 获取专业for 下拉框 -- 以后修改为从redis里面取
     *
     * @return
     */
    @RequestMapping(value = "getmajor", method = RequestMethod.GET)
    @ResponseBody
    public List<KeyAndValue> getMajor() {
        return majorService.findAllMajorForSelect();
    }


    /**
     * 获取班级信息 for 下拉框 -- 以后修改为从redis里面取
     *
     * @param map
     * @param uClass
     * @return
     */
    @RequestMapping(value = "getclass", method = RequestMethod.GET)
    @ResponseBody
    public List<KeyAndValue> getClass(ModelMap map, UClass uClass) {
        Long cid = TokenManager.getToken().getCid();
        map.put("cid", cid);
        map.put("mid", uClass.getMid());
        map.put("grade", uClass.getGrade());
        return uClassService.findUClassByParam(map);
    }


    /**
     * 获取校区信息
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "getcompany", method = RequestMethod.GET)
    @ResponseBody
    public List<KeyAndValue> getCompany(ModelMap map) {
        return companyService.findAllCompanyForSelect();
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
            String fileName = "学生信息导入模板.xlsx";
            List<UStudent> list = Lists.newArrayList();
            new ExportExcel("学生信息导入模板", UStudent.class, 2).write(request, response, fileName).dispose();
            return;
        } catch (Exception e) {
            return;
        }
    }

    /**
     * 导出学生信息
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
            String fileName = "学生信息数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            Long cid = TokenManager.getToken().getCid();

            if (!cid.equals(MANAGE_COMPANY_ID)) {
                map.put("cid", cid);
            }

            findContent = new String(findContent.getBytes("ISO8859-1"), "utf-8");
            map.put("findContent", findContent);

           /*高级查询*/
            if (record != null) {
                if (record.getMid() != null && !record.getMid().equals("")) {
                    map.put("mid", record.getMid());
                }
                if (record.getCid() != null && !record.getCid().equals("")) {
                    map.put("cid", record.getCid());
                }
                if (record.getCname() != null && !record.getCname().equals("")) {
                    String cname = new String(record.getCname().getBytes("ISO8859-1"), "utf-8");
                    map.put("cname", cname);
                }
                if (record.getJsname() != null && !record.getJsname().equals("")) {
                    String jsname = new String(record.getJsname().getBytes("ISO8859-1"), "utf-8");
                    map.put("jsname", jsname);
                }
                if (record.getBzrname() != null && record.getBzrname().equals("")) {
                    String bzrname = new String(record.getBzrname().getBytes("ISO8859-1"), "utf-8");
                    map.put("bzrname", bzrname);
                }
                if (record.getGrade() != null && !record.getGrade().equals("")) {
                    String grade = new String(record.getGrade().getBytes("ISO8859-1"), "utf-8");
                    map.put("grade", grade);
                }
            }
            map.put("reachStudentBo", record);
            Pagination<UStudent> page = studentService.findByPage(map, pageNo, -1);
            new ExportExcel("学生信息数据", UStudent.class).setDataList(page.getList()).write(request, response, fileName).dispose();
            return;
        } catch (Exception e) {

        }
    }

    @RequestMapping(value = "import", method = RequestMethod.POST)
    public ModelAndView importFile(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        int successNum = 0;
        int failureNum = 0;
        boolean majorFlg = false;
        Boolean companyFlg = false;

        try {
            ImportExcel ei = new ImportExcel(file, 1, 0);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            UUser uUser = TokenManager.getToken();

            List<UStudent> list = ei.getDataList(UStudent.class);
            List<KeyAndValue> majors = majorService.findAllMajorForSelect();
            List<KeyAndValue> companys = companyService.findAllCompanyForSelect();

            for (UStudent u : list) {
                companyFlg = false;
                majorFlg = false;

                for (KeyAndValue company : companys) {
                    if (company.getValue().equals(u.getWcompany().trim())) {
                        Long studentCid = (Long) company.getKey();
                        if (studentCid.equals(uUser.getCid())) {
                            companyFlg = true;
                        } else {
                            companyFlg = false;
                        }
                        break;
                    }
                }

                if (companyFlg == false) {
                    errorMap.put("msg", u.getName() + "的校区错误！");
                    return redirectImportError();
                }

                if (u.getIdcard() == null || u.getIdcard().trim().equals("")) {
                    errorMap.put("msg", u.getName() + "的身份证号为空！");
                    return redirectImportError();
                }

                if (InfoVerify.is18ByteIdCardComplex(u.getIdcard()) == false) {
                    errorMap.put("msg", u.getName() + "的身份证号格式错误！");
                    return redirectImportError();
                }


                if (studentService.findByIdCard(u.getIdcard()) != null) {
                    errorMap.put("msg", u.getName() + "的身份证号已存在！");
                    return redirectImportError();
                }


                if (u.getInputBirthday() == null || u.getInputBirthday().equals("")) {
                    errorMap.put("msg", u.getName() + "的出生日期为空！");
                    return redirectImportError();
                } else {
                    try {
                        u.setBirthday(sdf.parse(u.getInputBirthday()));
                    } catch (ParseException e) {
                        errorMap.put("msg", u.getName() + "的出生日期格式错误！");
                        return redirectImportError();
                    }

                }

                if (u.getGrade() == null || u.getGrade().equals("")) {
                    errorMap.put("msg", u.getName() + "的年级为空！");
                    return redirectImportError();
                }

                if (u.getGrade().contains("级")) {
                    errorMap.put("msg", u.getName() + "的年级填写格式错误！");
                    return redirectImportError();
                }

                for (KeyAndValue major : majors) {
                    if (major.getValue().equals(u.getuMajorName().trim())) {
                        u.setMid((Long) major.getKey());
                        majorFlg = true;
                    }
                }

                if (majorFlg == false) {
                    errorMap.put("msg", u.getName() + "的专业不存在！");
                    return redirectImportError();
                }
            }

            for (UStudent u : list) {
                UStudent result = studentService.insertSelective(u);
                if (result == null) {
                    failureNum++;
                } else {
                    successNum++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorMap.put("msg", "异常错误！");
            return redirectImportError();
        }
        String basePath = request.getContextPath();
        return new ModelAndView(new RedirectView(basePath + "/student/list.shtml"));
    }
}
