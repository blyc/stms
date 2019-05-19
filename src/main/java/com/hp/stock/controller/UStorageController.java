package com.hp.stock.controller;

import com.hp.common.controller.BaseController;
import com.hp.common.model.UStorage;
import com.hp.core.mybatis.page.Pagination;
import com.hp.stock.service.UStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by slm on 2018-04-15.
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("thelibrary")
public class UStorageController  extends BaseController {
    @Autowired
    private UStorageService uStorageService;
    @RequestMapping(value = "list{id}")
    public ModelAndView list(ModelMap map, Integer pageNo, String findContent,@PathVariable("id") Long id) {
        map.put("findContent",findContent);
        map.put("did", id);
        Pagination<UStorage> page = uStorageService.findByPage(map,pageNo,pageSize);
        map.put("page", page);
        return new ModelAndView("thelibrary/list");
    }

    @RequestMapping(value = "exportStudentsInfo", method = RequestMethod.GET)
    public void exportStudentsInfo(HttpServletRequest httpServletRequest, HttpServletResponse response, String fileName) {
        uStorageService.findAllInfo(response);

    }
}
