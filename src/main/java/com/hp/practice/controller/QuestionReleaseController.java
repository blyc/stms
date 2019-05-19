package com.hp.practice.controller;

import com.hp.common.controller.BaseController;
import com.hp.common.model.UPosition;
import com.hp.common.model.UStudent;
import com.hp.common.model.VQuestionRelease;
import com.hp.core.mybatis.page.Pagination;
import com.hp.core.shiro.token.manager.TokenManager;
import com.hp.position.service.PositionService;
import com.hp.practice.service.VQuestionReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@Scope(value = "prototype")
@RequestMapping("practice")
public class QuestionReleaseController extends BaseController {
    @Autowired
    private VQuestionReleaseService vQuestionReleaseService;

    @RequestMapping(value = "releaseList")
    public ModelAndView list(ModelMap map, Integer pageNo, String findContent, VQuestionRelease record) {
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
            map.put("reachStudentBo", record);
        }

        Pagination<VQuestionRelease> page = vQuestionReleaseService.findByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("practice/releaseList");
    }
}
