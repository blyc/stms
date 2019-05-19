package com.hp.student.controller;

import com.hp.common.bo.KeyAndValue;
import com.hp.common.controller.BaseController;
import com.hp.common.model.UClass;
import com.hp.common.model.UStudent;
import com.hp.common.model.VClass;
import com.hp.common.utils.DateUtil;
import com.hp.common.utils.LoggerUtils;
import com.hp.core.mybatis.page.Pagination;
import com.hp.core.shiro.token.manager.TokenManager;
import com.hp.student.service.StudentService;
import com.hp.uclass.service.UClassService;
import com.hp.vclass.service.VClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author: boy
 * @Date: 2018/07/29
 * @Description:
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("divide")
public class DivideClassController extends BaseController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private VClassService vclassService;

    @Autowired
    private UClassService uClassService;

    /**
     * 分班系统列表
     *
     * @param map
     * @param pageNo
     * @param findContent
     * @return
     */
    @RequestMapping(value = "list")
    public ModelAndView list(ModelMap map, Integer pageNo, String findContent) {
        Long cid = TokenManager.getToken().getCid();
        map.put("findContent", findContent);

        if (!cid.equals(MANAGE_COMPANY_ID)) {
            map.put("cid", cid);
        }
        map.put("state", "删除");
        Pagination<VClass> page = vclassService.findByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("divide/list");
    }

    /**
     * 新增班级
     *
     * @param u
     * @param request
     * @param modelMap
     * @param mids
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addUClass(UClass u, HttpServletRequest request, ModelMap modelMap,
                                         @RequestParam(value = "mids[]") Integer[] mids) {
        try {
            if (null == u) {
                resultMap.put("status", 500);
                resultMap.put("message", "添加失败，请刷新后再试！");
                LoggerUtils.fmtError(getClass(), "添加班级报错。参数为空");
                return resultMap;
            }

            boolean newClassOutOfError = false;
            for (int i = 0; i < mids.length; i++) {
                int tinum = Integer.parseInt(request.getParameter("t"+mids[i]+"num"));
                int oriinum = Integer.parseInt(request.getParameter("ori"+mids[i]+"num"));

                if(tinum+oriinum>15){
                    newClassOutOfError = true;
                }
            }
            if (newClassOutOfError) {
                resultMap.put("status", 500);
                resultMap.put("message", "超出创建班范围！");
                LoggerUtils.fmtError(getClass(), "添加班级报错。参数为空");
                return resultMap;
            }

            UClass entity = uClassService.insert(u, request);
            resultMap.put("status", 200);
            resultMap.put("entity", entity);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败，请刷新后再试！");
            LoggerUtils.fmtError(getClass(), e, "添加班级报错。source[%s]", u.toString());
        }
        return resultMap;
    }

    /**
     * 删除班级
     *
     * @param u
     * @param request
     * @return
     */
    @RequestMapping(value = "delUClass",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delUClass(UClass u, HttpServletRequest request) {
        try {
            if (null == u) {
                resultMap.put("status", 500);
                resultMap.put("message", "删除失败，请刷新后再试！");
                LoggerUtils.fmtError(getClass(), "删除班级报错。参数为空");
                return resultMap;
            }
            uClassService.delete(u, request);
            resultMap.put("status", 200);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "删除失败，请刷新后再试！");
            LoggerUtils.fmtError(getClass(), e, "删除班级报错。source[%s]", u.toString());
        }
        return resultMap;
    }

    /**
     * 分班信息修改（添加老师）
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "addTeacherClass", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addTeacherClass(HttpServletRequest request) {
        try {
            //保存老师-学生关系
            uClassService.insertTeacherClass(request);
            resultMap.put("status", 200);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败，请刷新后再试！");
        }
        return resultMap;
    }

    /**
     * 分班信息修改（移除学员）
     *
     * @param request
     * @param cid
     * @return
     */
    @RequestMapping(value = "shiftoutStudent", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> shiftoutStudent(HttpServletRequest request, @RequestParam Long cid) {
        String sidset = request.getParameter("chk_value");
        String[] sid = sidset.split(",");
        List<UStudent> uStudentList = new ArrayList<UStudent>();
        UStudent uStudent = null;
        for (int i = 0; i < sid.length; i++) {
            uStudent = studentService.findByPrimaryKey(Long.valueOf(sid[i]));
            uStudent.setCid(null);
            uStudentList.add(uStudent);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("updatecid", cid);
        map.put("list", uStudentList);
        try {
            studentService.updateCidBySid(map, 1);
            resultMap.put("status", 200);
            resultMap.put("message", "更新成功");
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "更新失败，请刷新后再试！");
            LoggerUtils.fmtError(getClass(), e, "更新学生报错。source[%s]");
        }
        return resultMap;
    }

    /**
     * 分班信息修改（添加学员）
     *
     * @param request
     * @param cid
     * @return
     */
    @RequestMapping(value = "shiftinStudent", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> shiftinStudent(HttpServletRequest request, @RequestParam Long cid) {
        String sidset = request.getParameter("chk_value");
        String[] sid = sidset.split(",");
        List<UStudent> uStudentList = new ArrayList<UStudent>();
        UStudent uStudent = null;
        for (int i = 0; i < sid.length; i++) {
            uStudent = studentService.findByPrimaryKey(Long.valueOf(sid[i]));
            uStudentList.add(uStudent);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("cid", cid);
        map.put("updatecid", cid);
        map.put("list", uStudentList);
        try {
            studentService.updateCidBySid(map, 0);
            resultMap.put("status", 200);
            resultMap.put("message", "更新成功");
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "更新失败，请刷新后再试！");
            LoggerUtils.fmtError(getClass(), e, "更新学生报错。source[%s]");
        }
        return resultMap;
    }

    /**
     * 获取年级信息for select
     *
     * @return
     */
    @RequestMapping(value = "getGrade", method = RequestMethod.GET)
    @ResponseBody
    public List<KeyAndValue> getGrade() {
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


    /**
     * 分班信息修改（查询分班学员信息）
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "fs", method = RequestMethod.GET)
    @ResponseBody
    public List findStudent(HttpServletRequest request) {
        String name = request.getParameter("name");
        String ccid = request.getParameter("ccid");
        String mid = request.getParameter("mid");
        if (name != null && !"".equals(name)) {
            name = name + "%";
        }
        if (ccid == null) {
            if (name == null || "".equals(name)) {
                return findWithout(Long.parseLong(mid));
            } else {
                return findByNameWithout(Long.parseLong(mid), name);
            }
        } else {
            if (name == null || "".equals(name)) {
                return findByCid(ccid);
            } else {
                return findByCidName(ccid, name);
            }
        }
    }

    /**
     * 分班信息修改（查询分班老师信息)
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "ft", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findTeacher(HttpServletRequest request) {
        try {
            //班级id
            String ccid = request.getParameter("ccid");
            Map map = new HashMap();
            map.put("ccid", ccid);
            Map rmap = uClassService.findByCidAndPosition(map);
            rmap.put("status", 200);
            return rmap;
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败，请刷新后再试！");
        }
        return resultMap;
    }

    /**
     * 分班信息修改(显示班级数量)
     *
     * @param grade
     * @return
     */
    @RequestMapping(value = "getClassCount", method = RequestMethod.GET)
    @ResponseBody
    public List<KeyAndValue> getClassCount(String grade) {

        return uClassService.findMajorClassCountByGrade(grade);
    }



    /*---------------------------------------------------------------------------------*/

    /**
     * 查询指定班级中所有学生
     *
     * @param ccid
     * @return
     */
    private List findByCid(String ccid) {
        List<UStudent> uStudentList = null;
        try {
            uStudentList = studentService.findByCid(Long.valueOf(ccid));
            return uStudentList;
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败，请刷新后再试！");
        }
        return uStudentList;
    }

    /**
     * 根据姓名模糊查询指定班级中的学生
     *
     * @param ccid 班级编号
     * @param name 姓名
     * @return List
     */
    private List findByCidName(String ccid, String name) {
        List<UStudent> uStudentList = null;
        try {
            uStudentList = studentService.findByCidName(Long.valueOf(ccid), name);
            return uStudentList;
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败，请刷新后再试！");
        }
        return uStudentList;
    }

    /**
     * 查找没有班级的学生
     *
     * @return
     */
    private List findWithout(Long mid) {
        List<UStudent> uStudentList = null;
        try {
            uStudentList = studentService.findWithoutClass(mid);
            return uStudentList;
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败，请刷新后再试！");
        }
        return uStudentList;
    }

    /**
     * 根据姓名查找没有班级的学生
     *
     * @param mid
     * @param name
     * @return
     */
    private List findByNameWithout(Long mid, String name) {
        List<UStudent> uStudentList = null;
        try {
            uStudentList = studentService.findByNameWithoutClass(mid, name);
            return uStudentList;
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败，请刷新后再试！");
        }
        return uStudentList;
    }

}
