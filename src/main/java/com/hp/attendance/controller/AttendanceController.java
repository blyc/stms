package com.hp.attendance.controller;

import com.hp.attendance.bo.Attendancebo;
import com.hp.attendance.bo.Remarksbo;
import com.hp.attendance.service.AttendanceService;
import com.hp.common.bo.KeyAndValue;
import com.hp.common.bo.QueryBuffer;
import com.hp.common.controller.BaseController;
import com.hp.common.model.UClassAttendance;
import com.hp.common.model.UStudent;
import com.hp.common.utils.DateUtil;
import com.hp.common.utils.LoggerUtils;
import com.hp.core.mybatis.page.Pagination;
import com.hp.core.shiro.token.manager.TokenManager;
import com.hp.student.service.StudentService;
import com.hp.uclass.service.UClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 出勤controller
 *
 * @Author: boy
 * @Date: 2018/03/03
 * @Description:
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("attendance")
public class AttendanceController extends BaseController {
    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private UClassService uClassService;

    /**
     * 出勤率查询
     *
     * @param map
     * @param pageNo
     * @param findContent
     * @return
     */
    @RequestMapping(value = "list")
    public ModelAndView list(ModelMap map, Integer pageNo, String findContent,QueryBuffer queryBuffer) {
        Long cid = TokenManager.getToken().getCid();
        map.put("findContent", findContent);
        map.put("startQueryDate", queryBuffer.getStartQueryDate());
        map.put("endQueryDate", queryBuffer.getEndQueryDate());

        if (!cid.equals(MANAGE_COMPANY_ID)) {
            map.put("cid", cid);
        }
        Pagination<UClassAttendance> page = attendanceService.findByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("attendance/list");
    }

    /**
     * 获取未登记出勤班级id和name for 下拉框
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "class", method = RequestMethod.GET)
    @ResponseBody
    public List<KeyAndValue> getClass(ModelMap map) {
        Long cid = TokenManager.getToken().getCid();
        map.put("cid", cid);
        map.put("attendRecordTime", DateUtil.getCurrentDateString());
        return uClassService.findUClassByParam(map);
    }

    /**
     * 查询学生信息
     *
     * @param ccid
     * @return
     */
    @RequestMapping(value = "getClassStudent", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getClassStudent(Long ccid) {
        try {
            List<UStudent> students = studentService.findByCid(ccid);
            resultMap.put("status", 200);
            resultMap.put("entity", students);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "请刷新后再试！");
        }
        return resultMap;
    }

    /**
     * 添加班级考勤记录
     *
     * @param uClassAttendance
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addAttendance(UClassAttendance uClassAttendance) {
        try {
            UClassAttendance entity = attendanceService.insertUClassAttendance(uClassAttendance);
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

    /**
     * 添加学生考勤记录
     *
     * @param attendancebos
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addAttendance(@RequestBody List<Attendancebo> attendancebos, @PathVariable("id") Long id) {
        try {
            UClassAttendance entity = attendanceService.insertAttendance(attendancebos, id);
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

    /**
     * 查询学生考勤记录
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getDetail(@PathVariable Long id) {
        try {
            List<Attendancebo> uAttendances = attendanceService.findDetail(id);
            resultMap.put("status", 200);
            resultMap.put("entity", uAttendances);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败，请刷新后再试！");

        }
        return resultMap;
    }

    /**
     * 添加学生考勤记录备注
     *
     * @param said
     * @return
     */
    @RequestMapping(value = "remarks{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addRemarks(@RequestBody List<Remarksbo> remarksbos, @PathVariable("id") Long said) {
        try {
            UClassAttendance entity = attendanceService.insertRemarks(remarksbos, said);
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
            LoggerUtils.fmtError(getClass(), e, "添加备注报错。");
        }
        return resultMap;
    }

    /**
     * 查询学生考勤记录备注
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "remarks{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getRemarksDetail(@PathVariable Long id) {
        try {
            List<Remarksbo> uAttendancesRemarks = attendanceService.findRemarksDetail(id);
            resultMap.put("status", 200);
            resultMap.put("entity", uAttendancesRemarks);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "查询失败，请刷新后再试！");

        }
        return resultMap;
    }
}
