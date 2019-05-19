package com.hp.employee.controller;

import com.google.common.collect.Lists;
import com.hp.common.bo.KeyAndValue;
import com.hp.common.controller.BaseController;
import com.hp.common.model.UEmployee;
import com.hp.common.utils.DateUtil;
import com.hp.common.utils.LoggerUtils;
import com.hp.company.service.CompanyService;
import com.hp.core.mybatis.page.Pagination;
import com.hp.core.shiro.token.manager.TokenManager;
import com.hp.department.service.DepartmentSevice;
import com.hp.employee.service.EmployeeRankService;
import com.hp.employee.service.EmployeeService;
import com.hp.position.service.PositionService;
import com.hp.utils.DateUtils;
import com.hp.utils.PingYinUtil;
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
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: boy
 * @Date: 2018/02/10
 * @Description:
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("employee")
public class EmployeeController extends BaseController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private DepartmentSevice departmentSevice;
    @Autowired
    private PositionService positionService;
    @Autowired
    private EmployeeRankService employeeRankService;

    @RequestMapping(value = "list")
    public ModelAndView list(ModelMap map, Integer pageNo, String findContent) {
        map.put("findContent", findContent);
        Long cid = TokenManager.getToken().getCid();

        if (!cid.equals(MANAGE_COMPANY_ID)) {
            map.put("cid", cid);
        }
        Pagination<UEmployee> page = employeeService.findByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("employee/list");
    }

    /**
     * 导出
     *
     * @param map
     * @param pageNo
     * @param findContent
     * @param request
     * @param response
     */
    @RequestMapping(value = "export", method = RequestMethod.GET)
    public void exportFile(ModelMap map, Integer pageNo, String findContent, HttpServletRequest request, HttpServletResponse response) {
        map.put("findContent", findContent);
        Long cid = TokenManager.getToken().getCid();
        if (!cid.equals(MANAGE_COMPANY_ID)) {
            map.put("cid", cid);
        }
        Pagination<UEmployee> page = employeeService.findByPage(map, pageNo, -1);

        String fileName = "员工信息数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
        try {
            new ExportExcel("员工信息数据", UEmployee.class).setDataList(page.getList()).write(request, response, fileName).dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    /**
     * 校区下拉框
     *
     * @return
     */
    @RequestMapping(value = "selectCompany")
    @ResponseBody
    public List<KeyAndValue> selectCompany() {
        List<KeyAndValue> companyList =  companyService.findAllCompanyForSelect();
        Long cid = TokenManager.getToken().getCid();
        if (!cid.equals(MANAGE_COMPANY_ID)) {
            List<KeyAndValue> ret = new ArrayList<KeyAndValue>();
            for(KeyAndValue k :companyList){
                if(k.getKey().equals(cid)){
                    KeyAndValue kv = new KeyAndValue(k.getKey(),k.getValue());
                    ret.add(kv);
                    break;
                }
            }
            return ret;
        }
        return companyList;
    }

    @RequestMapping(value = "selectDepartMent")
    @ResponseBody
    public List<KeyAndValue> selectDepartMent() {
        return departmentSevice.findAllDepartmentForSelect();
    }

    @RequestMapping(value = "selectPosition")
    @ResponseBody
    public List<KeyAndValue> selectPosition() {
        return positionService.findAllPositionForSelect();
    }

    @RequestMapping(value = "selectRank")
    @ResponseBody
    public List<KeyAndValue> selectRank() {
        return employeeRankService.findAllUJsRankForSelect();
    }

    /*
    查询校区所有员工
     */
    @RequestMapping(value = "selectEmployee")
    @ResponseBody
    public List<KeyAndValue> selectEmployee() {
        return employeeService.findAllEmployeeForSelect();
    }


    /*年级下拉框*/
    @RequestMapping(value = "addClass")
    @ResponseBody
    public List<KeyAndValue> addClass() {
        Date date = new Date();
        String yearTimestr = DateUtil.getYear(date);
        Integer nowyear = Integer.parseInt(yearTimestr.substring(2, 4));
        List<KeyAndValue> list = new LinkedList<KeyAndValue>();
        for (int i = 13; i <= nowyear; i++) {
            KeyAndValue keyAndValue = new KeyAndValue(i, i + "级");
            list.add(keyAndValue);
        }
        return list;
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addEmployee(UEmployee uEmployee) {
        try {

            Long cid = TokenManager.getToken().getCid();
            if (!cid.equals(MANAGE_COMPANY_ID)) {
                uEmployee.setCid(cid);
            }

            List<UEmployee> uEmployees = employeeService.findByTel(uEmployee.getTel());
            if (uEmployees != null && uEmployees.size() != 0) {
                resultMap.put("status", 502);
                resultMap.put("message", "电话" + uEmployee.getTel() + "重复");
                return resultMap;
            }

            UEmployee entity = employeeService.insert(uEmployee);

            if (null == entity) {
                resultMap.put("message", "用户名已经存在！");
                return resultMap;
            }


            resultMap.put("status", 200);
            resultMap.put("entity", entity);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败，请刷新后再试！");
            LoggerUtils.fmtError(getClass(), e, "添加用户报错。source[%s]", uEmployee.toString());
        }
        return resultMap;
    }


    /**
     * 更新员工信息
     *
     * @param uEmployee
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updateEmployee(UEmployee uEmployee) {
        try {
            if (uEmployee == null) {
                resultMap.put("status", 500);
                resultMap.put("message", "参数错误！");
                return resultMap;
            }
            int ret = employeeService.update(uEmployee);

            if (ret < 0) {
                resultMap.put("status", 500);
                resultMap.put("message", "更新失败！");
                return resultMap;
            }
            resultMap.put("status", 200);
            resultMap.put("entity", uEmployee);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "更新失败，请刷新后再试！");
            LoggerUtils.fmtError(getClass(), e, "更新员工报错。source[%s]", uEmployee.toString());
        }
        return resultMap;
    }


    @RequestMapping(value = "deleteEmployee", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteEmployee(String ids) {
        return employeeService.deleteEmployeeById(ids);
    }


    @RequestMapping(value = "getEmployee", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getEmployee(Long eid) {
        try {

            UEmployee entity = employeeService.findByPk(eid);

            if (null == entity) {
                resultMap.put("message", "用户不存存在！");
                return resultMap;
            }

            resultMap.put("status", 200);
            resultMap.put("entity", entity);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败，请刷新后再试！");
        }
        return resultMap;
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
            String fileName = "员工数据导入模板.xlsx";
            List<UEmployee> list = Lists.newArrayList();
            new ExportExcel("员工数据导入模板", UEmployee.class, 2).write(request, response, fileName).dispose();
            return;
        } catch (Exception e) {
            return;
        }
    }

    @RequestMapping(value = "import", method = RequestMethod.POST)
    public ModelAndView importFile(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        int successNum = 0;
        int failureNum = 0;
        Boolean companyFlg = false;
        Boolean departmentFlg = false;
        Boolean positionsFlg = false;

        StringBuilder failureMsg = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Long cid = TokenManager.getToken().getCid();
            ImportExcel ei = new ImportExcel(file, 1, 0);

            List<UEmployee> list = ei.getDataList(UEmployee.class);

            List<KeyAndValue> companys = companyService.findAllCompanyForSelect();
            List<KeyAndValue> departments = departmentSevice.findAllDepartmentForSelect();
            List<KeyAndValue> positions = positionService.findAllPositionForSelect();

            for (UEmployee u : list) {
                companyFlg = false;
                departmentFlg = false;
                positionsFlg = false;

                for (KeyAndValue company : companys) {
                    if (company.getValue().equals(u.getuCompanyName().trim())) {
                        u.setCid((Long) company.getKey());
                        if (!cid.equals(MANAGE_COMPANY_ID) && cid.equals(u.getCid())) {
                            companyFlg = true;
                        }
                        break;
                    }
                }
                for (KeyAndValue department : departments) {
                    if (department.getValue().equals(u.getuDepartmentName().trim())) {
                        u.setDid((Long) department.getKey());
                        departmentFlg = true;
                        break;
                    }
                }
                for (KeyAndValue position : positions) {
                    if (position.getValue().equals(u.getuPositionName().trim())) {
                        u.setPid((Long) position.getKey());
                        positionsFlg = true;
                        break;
                    }
                }

                u.setEntryday(sdf.parse(u.getStrEntryday()));
                u.setPinyin(PingYinUtil.getFullSpell(u.getName()));

                if (companyFlg == false || departmentFlg == false || positionsFlg == false) {
                    StringBuffer msg = new StringBuffer();
                    if (companyFlg == false) {
                        msg.append("校区错误，");
                    }
                    if (departmentFlg == false) {
                        msg.append("部门错误，");
                    }

                    if (positionsFlg == false) {
                        msg.append("部门错误，");
                    }

                    errorMap.put("msg", msg.toString());
                    return redirectImportError();
                }

                List<UEmployee> uEmployees = employeeService.findByTel(u.getTel());
                if (uEmployees != null && uEmployees.size() != 0) {
                    errorMap.put("msg", "电话" + u.getTel() + "重复");
                    return redirectImportError();
                }
            }

            for (UEmployee u : list) {
                UEmployee entity = employeeService.insert(u);
                if (entity == null) {
                    failureNum++;
                } else {
                    successNum++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return redirectImportError();
        }
        String basePath = request.getContextPath();
        return new ModelAndView(new RedirectView(basePath + "/employee/list.shtml"));
    }
}
