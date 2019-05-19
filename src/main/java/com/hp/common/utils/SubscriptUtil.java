package com.hp.common.utils;

import com.hp.common.model.USchedule;


public  class SubscriptUtil {
    public static USchedule Generatingsubscript(USchedule uSchedule){
        String weeks = uSchedule.getWeeks();
        String section = uSchedule.getSection();
        Integer subscript = 0;
        switch (weeks){
            case "周一":
                subscript = 0;
                break;
            case "周二":
                subscript = 10;
                break;
            case "周三":
                subscript = 20;
                break;
            case "周四":
                subscript = 30;
                break;
            case "周五":
                subscript = 40;
                break;
            case "周六":
                subscript = 50;
                break;
            case "周日":
                subscript = 60;
                break;
        }
        subscript = SubscriptUtil.Subscriptdetermination(subscript,section);
        uSchedule.setSubscript(subscript);
        return uSchedule;
    }
    public static int Subscriptdetermination(Integer subscript,String section){
        switch (section){
            case "第一节":
                subscript +=1;
                break;
            case "第二节":
                subscript +=2;
                break;
            case "第三节":
                subscript +=3;
                break;
            case "第四节":
                subscript +=4;
                break;
            case "第五节":
                subscript +=5;
                break;
            case "第六节":
                subscript +=6;
                break;
            case "第七节":
                subscript +=7;
                break;
            case "第八节":
                subscript +=8;
                break;
            case "第九节":
                subscript +=9;
                break;
            case "第十节":
                subscript +=10;
                break;
        }

        return subscript;
    }
}
