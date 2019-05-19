package com.hp.core.tags;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.apache.shiro.SecurityUtils;

import java.util.List;

/**
 * @Author: boy
 * @Date: 2018/02/17
 * @Description:
 */
public class HasAnyPermissions implements TemplateMethodModelEx {
    @Override
    public Object exec(List list) throws TemplateModelException {
        if(list.size()<=0){
            throw new TemplateModelException("HasAnyPermissions - 错误参数");
        }
        org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
        int index = 0;
        for (Object s : list){
            index++;
            SimpleScalar str = (SimpleScalar ) s;
            try {
                subject.checkPermission(str.getAsString());
            }catch (Exception e){
                if(index==list.size()){
                    return false;
                }
                continue;
            }
            return true;
        }
        return false;
    }
}
