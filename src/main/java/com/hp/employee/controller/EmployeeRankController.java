package com.hp.employee.controller;

import com.google.common.collect.Lists;
import com.hp.common.controller.BaseController;
import com.hp.common.model.UJsRank;
import com.hp.common.utils.LoggerUtils;
import com.hp.core.mybatis.page.Pagination;
import com.hp.employee.service.EmployeeRankService;
import com.hp.student.bo.StudentRearchBo;
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
import java.util.List;
import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/11/28
 * @Description:
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("rank")
public class EmployeeRankController extends BaseController {

    @Autowired
    private EmployeeRankService employeeRankService;

    @RequestMapping(value = "list")
    public ModelAndView list(ModelMap map, Integer pageNo, String findContent) {
        /*一般查询*/
        map.put("findContent", findContent);

        Pagination<UJsRank> page = employeeRankService.findByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("rank/list");
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addUJsRank(UJsRank uJsRank) {
        try {
            employeeRankService.addUJsRank(uJsRank);
            resultMap.put("status", 200);
            resultMap.put("message", "添加成功");
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败出错");
        }
        return resultMap;
    }


    @RequestMapping(value = "getUJsRank", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getUJsRank(Long lid) {
        try {

            UJsRank entity = employeeRankService.findByPk(lid);

            if (null == entity) {
                resultMap.put("message", "薪资不存存在！");
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
     * 更新薪资体系信息
     *
     * @param uJsRank
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updateUJsRank(UJsRank uJsRank) {
        try {
            if (uJsRank == null) {
                resultMap.put("status", 500);
                resultMap.put("message", "参数错误！");
                return resultMap;
            }
            int ret = employeeRankService.update(uJsRank);

            if (ret < 0) {
                resultMap.put("status", 500);
                resultMap.put("message", "更新失败！");
                return resultMap;
            }
            resultMap.put("status", 200);
            resultMap.put("entity", uJsRank);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "更新失败，请刷新后再试！");
            LoggerUtils.fmtError(getClass(), e, "更新薪资体系报错。source[%s]", uJsRank.toString());
        }
        return resultMap;
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

    }

    @RequestMapping(value = "import", method = RequestMethod.POST)
    public ModelAndView importFile(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        try {
            ImportExcel ei = new ImportExcel(file, 1, 0);
            List<UJsRank> list = ei.getDataList(UJsRank.class);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getType() == null || "".equals(list.get(i).getType().trim())) {
                    errorMap.put("msg", "第" + i + "条的岗位代码为空！");
                    return redirectImportError();
                }
                if (list.get(i).getLevel() == null || "".equals(list.get(i).getLevel().trim())) {
                    errorMap.put("msg", "第" + i + "条的级别为空！");
                    return redirectImportError();
                }
                if (list.get(i).getSubsidy() == null) {
                    errorMap.put("msg", "第" + i + "条的薪资为空！");
                    return redirectImportError();
                }

                employeeRankService.addUJsRank(list.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorMap.put("msg", "异常错误！");
            return redirectImportError();
        }
        String basePath = request.getContextPath();
        return new ModelAndView(new RedirectView(basePath + "/rank/list.shtml"));
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
            String fileName = "薪资体系导入模板.xlsx";
            List<UJsRank> list = Lists.newArrayList();
            new ExportExcel("薪资体系导入模板", UJsRank.class, 2).write(request, response, fileName).dispose();
            return;
        } catch (Exception e) {
            return;
        }
    }
}
