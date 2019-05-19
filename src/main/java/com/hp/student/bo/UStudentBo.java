package com.hp.student.bo;

import com.hp.common.model.UInterViewInfo;
import com.hp.common.model.UStudent;

import java.io.Serializable;

/**
 * Created by lenovo on 2018/4/20.
 */
public class UStudentBo extends UStudent implements Serializable{

    private UInterViewInfo uInterViewInfo;

    public UInterViewInfo getuInterViewInfo() {
        return uInterViewInfo;
    }

    public void setuInterViewInfo(UInterViewInfo uInterViewInfo) {
        this.uInterViewInfo = uInterViewInfo;
    }
}
