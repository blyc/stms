package com.hp.common.controller;

import com.hp.common.beanvalidator.BeanValidators;
import com.hp.common.utils.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.*;
import java.util.Map.Entry;

public class BaseController {


    protected int pageNo = 1;
    public static int pageSize = 10;
    protected final static Logger logger = Logger.getLogger(BaseController.class);
    protected Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    public static String URL404 = "/404.html";

    public static String IMPORTERROR = "common/import_error";

    private final static String PARAM_PAGE_NO = "pageNo";

    protected String pageSizeName = "pageSize";

    protected Long MANAGE_COMPANY_ID = 3L;
    protected Map<String, Object> errorMap = new LinkedHashMap<String, Object>();
    /**
     * 验证Bean实例对象
     */
    @Autowired
    protected Validator validator;

    /**
     * 服务端参数有效性验证
     *
     * @param object 验证的实体对象
     * @param groups 验证组
     * @return 验证成功：返回true；严重失败：将错误信息添加到 message 中
     */
    protected boolean beanValidator(Model model, Object object, Class<?>... groups) {
        try {
            BeanValidators.validateWithException(validator, object, groups);
        } catch (ConstraintViolationException ex) {
            List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
            list.add(0, "数据验证失败：");
            //addMessage(model, list.toArray(new String[]{}));
            return false;
        }
        return true;
    }

    /**
     * 服务端参数有效性验证
     *
     * @param object 验证的实体对象
     * @param groups 验证组
     * @return 验证成功：返回true；严重失败：将错误信息添加到 flash message 中
     */
    protected boolean beanValidator(RedirectAttributes redirectAttributes, Object object, Class<?>... groups) {
        try {
            BeanValidators.validateWithException(validator, object, groups);
        } catch (ConstraintViolationException ex) {
            List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
            list.add(0, "数据验证失败：");
            //addMessage(redirectAttributes, list.toArray(new String[]{}));
            return false;
        }
        return true;
    }

    /**
     * 服务端参数有效性验证
     *
     * @param object 验证的实体对象
     * @param groups 验证组，不传入此参数时，同@Valid注解验证
     * @return 验证成功：继续执行；验证失败：抛出异常跳转400页面。
     */
    protected void beanValidator(Object object, Class<?>... groups) {
        BeanValidators.validateWithException(validator, object, groups);
    }


    /**
     * 往Request里带值
     *
     * @param request
     * @param key
     * @param value
     */
    protected static void setValue2Request(HttpServletRequest request, String key, Object value) {
        request.setAttribute(key, value);
    }

    /**
     * [获取session]
     *
     * @param request
     * @return
     */
    public static HttpSession getSession(HttpServletRequest request) {
        return request.getSession();
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        BaseController.pageSize = pageSize;
    }

    public ModelAndView redirect(String redirectUrl, Map<String, Object>... parament) {
        ModelAndView view = new ModelAndView(new RedirectView(redirectUrl));
        if (null != parament && parament.length > 0) {
            view.addAllObjects(parament[0]);
        }
        return view;
    }

    public ModelAndView redirect404() {
        return new ModelAndView(new RedirectView(URL404));
    }

    public ModelAndView redirectImportError() {
        return new ModelAndView(IMPORTERROR,errorMap);
    }

    @SuppressWarnings("unchecked")
    protected Map<String, Object> prepareParams(Object obj, HttpServletRequest request) throws Exception {
        if (request != null) {
            String pageNoStr = (String) request.getParameter(PARAM_PAGE_NO),
                    pageSizeStr = (String) request.getParameter(pageSizeName);
            if (StringUtils.isNotBlank(pageNoStr)) {
                pageNo = Integer.parseInt(pageNoStr);
            }
            if (StringUtils.isNotBlank(pageSizeStr)) {
                pageSize = Integer.parseInt(pageSizeStr);
            }
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params = BeanUtils.describe(obj);
        params = handleParams(params);
        // 回填值项
        //BeanUtils.populate(obj, params);
        return params;
    }

    private Map<String, Object> handleParams(Map<String, Object> params) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (null != params) {
            Set<Entry<String, Object>> entrySet = params.entrySet();

            for (Iterator<Entry<String, Object>> it = entrySet.iterator(); it.hasNext(); ) {
                Entry<String, Object> entry = it.next();
                if (entry.getValue() != null) {
                    result.put(entry.getKey(), StringUtils.trimToEmpty((String) entry.getValue()));
                }
            }
        }
        return result;
    }

}
