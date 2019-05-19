package com.hp.project.bo;

import java.io.Serializable;

/**
 * Created by 金梦杰 on 2018/4/8/008.
 */
public class UClassProjectbo implements Serializable {
    private Long cpid;
    private String squalified;

    public Long getCpid() {
        return cpid;
    }

    public void setCpid(Long cpid) {
        this.cpid = cpid;
    }

    public String getSqualified() {
        return squalified;
    }

    public void setSqualified(String squalified) {
        this.squalified = squalified;
    }
}
