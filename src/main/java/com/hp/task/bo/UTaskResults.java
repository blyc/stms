package com.hp.task.bo;

import java.io.Serializable;

/**
 * Created by Dragon on 2018/4/20 0020.
 */
public class UTaskResults implements Serializable {
    private String cname;
    private String squalified;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getSqualified() {
        return squalified;
    }

    public void setSqualified(String squalified) {
        this.squalified = squalified;
    }
}
