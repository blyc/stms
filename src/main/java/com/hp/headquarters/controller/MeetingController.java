package com.hp.headquarters.controller;

import com.hp.common.controller.BaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: boy
 * @Date: 2018/12/16
 * @Description:
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("meeting")
public class MeetingController extends BaseController {
//    @Autowired
//    private MeetingService meetingService;
//    private static Long WCJ = 0L;
//    private static Long CGJ = 1L;
//
//    @RequestMapping(value = "getCuser", method = RequestMethod.GET)
//    @ResponseBody
//    public Map<String, Object> getCuser() {
//        List<UCuser> uCuserList = meetingService.selectCjUser();
//        resultMap.put("info", uCuserList);
//        return resultMap;
//    }
//
//    @RequestMapping(value = "getHCuser", method = RequestMethod.GET)
//    @ResponseBody
//    public Map<String, Object> getHCuser(Long flg) {
//        List<UCuser> uCuserList = meetingService.selectHjUser(flg);
//        resultMap.put("info", uCuserList);
//        return resultMap;
//    }
//
//    @RequestMapping(value = "cj/{url}")
//    public ModelAndView go2(@PathVariable("url") String url, ModelMap map) {
//
//        String viewName ="meeting/cj/lucky5";
//        UCtype record = new UCtype();
//
//        if (url.equals("j1")) {
//            record.setTname("特等奖");
//            viewName = "meeting/cj/lucky";
//        } else if (url.equals("j2")) {
//            record.setTname("一等奖");
//            viewName = "meeting/cj/lucky";
//        } else if (url.equals("j3")) {
//            record.setTname("二等奖");
//            viewName = "meeting/cj/lucky5";
//        } else if (url.equals("j4")) {
//            record.setTname("三等奖");
//            viewName = "meeting/cj/lucky5";
//        } else if (url.equals("j5")) {
//            record.setTname("幸运奖");
//            viewName = "meeting/cj/lucky5";
//        }
//        UCtype uCtype = meetingService.selectTypeByName(record);
//        map.put("ctype", uCtype);
//        return new ModelAndView(viewName);
//    }
//
//    @RequestMapping(value = "prizes")
//    public ModelAndView prizes(ModelMap map) {
//        List<UCprizes> uCuserList = meetingService.selectPrizes();
//        for (int i = 0; i < uCuserList.size(); i++) {
//            UCprizes uCprizes = uCuserList.get(i);
//            map.put("prizes_" + i, uCprizes.getImg());
//        }
//        map.put("prizescount", uCuserList.size());
//        return new ModelAndView("meeting/prizes/index");
//    }
//
//
//    @RequestMapping(value = "updateprizes", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Object> updatePrizes(UCprizes uCprizes) {
//        try {
//            uCprizes.setFlg(1L);
//            meetingService.updatePrize(uCprizes);
//            resultMap.put("status", 200);
//        } catch (Exception e) {
//            resultMap.put("status", 500);
//            resultMap.put("message", "添加失败，请刷新后再试！");
//        }
//        return resultMap;
//    }
//
//    @RequestMapping(value = "index")
//    public ModelAndView index(ModelMap map) {
//        return new ModelAndView("meeting/index");
//    }
//
//    @RequestMapping(value = "list")
//    public ModelAndView list(ModelMap map) {
//        return new ModelAndView("meeting/list");
//    }
//
//
//    @RequestMapping(method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Object> updateFlg(String ids,Long flg) {
//        try {
//            meetingService.update(ids,flg);
//            resultMap.put("status", 200);
//        } catch (Exception e) {
//            resultMap.put("status", 500);
//            resultMap.put("message", "添加失败，请刷新后再试！");
//        }
//        return resultMap;
//    }
//
//    /**
//     * 下载空模板
//     *
//     * @param request
//     * @param response
//     */
//    @RequestMapping(value = "import/template", method = RequestMethod.GET)
//    public void downtemplate(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            String fileName = "年会名单导入模板.xlsx";
//            List<UCuser> list = Lists.newArrayList();
//            new ExportExcel("年会名单", UCuser.class, 2).write(request, response, fileName).dispose();
//            return;
//        } catch (Exception e) {
//            return;
//        }
//    }
//
//    @RequestMapping(value = "import", method = RequestMethod.POST)
//    public ModelAndView importFile(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, RedirectAttributes redirectAttributes) {
//        int successNum = 0;
//        int failureNum = 0;
//
//        try {
//            ImportExcel ei = new ImportExcel(file, 1, 0);
//            List<UCuser> list = ei.getDataList(UCuser.class);
//
//            for (UCuser u : list) {
//                meetingService.insert(u);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return redirectImportError();
//        }
//        String basePath = request.getContextPath();
//        return new ModelAndView(new RedirectView(basePath + "/meeting/list.shtml"));
//    }
//
//
//    @RequestMapping(value = "show")
//    public ModelAndView show(ModelMap map) {
//        return new ModelAndView("meeting/screen/show");
//    }
//
//    @RequestMapping(value = "user")
//    public ModelAndView user(ModelMap map) {
//        return new ModelAndView("meeting/screen/user");
//    }
//
//    @RequestMapping(value = "addscreen", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Object> addScreene(UCscreen uCscreen) {
//        try {
//            UCscreen entity = meetingService.insertScreen(uCscreen);
//            if (null == entity) {
//                resultMap.put("status", 500);
//                resultMap.put("message", "添加失败，请刷新后再试");
//                return resultMap;
//            }
//            resultMap.put("status", 200);
//            resultMap.put("entity", entity);
//        } catch (Exception e) {
//            resultMap.put("status", 500);
//            resultMap.put("message", "添加失败，请刷新后再试！");
//        }
//        return resultMap;
//    }
//
//    @RequestMapping(value = "getscreen", method = RequestMethod.GET)
//    @ResponseBody
//    public Map<String, Object> getScreen(Long count) {
//        try {
//            List<UCscreen> uCscreens = meetingService.selectUcreen(count);
//            resultMap.put("status", 200);
//            resultMap.put("entity", uCscreens);
//        } catch (Exception e) {
//            resultMap.put("status", 500);
//            resultMap.put("message", "添加失败，请刷新后再试！");
//        }
//        return resultMap;
//
//
//    }

}
