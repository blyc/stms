package com.hp.core.converter;

import com.hp.common.utils.LoggerUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateStringConverter implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        try {
            if(source==null && source.equals("")){
                return null;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
            LoggerUtils.fmtError(getClass(), e, "日期转换错误，ids[%s]", source);
            return null;
        }
    }
}
