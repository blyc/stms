package com.hp.common.bo;

import java.io.Serializable;

/**
 * @Author: boy
 * @Date: 2018/12/21
 * @Description:
 */
public class QueryBuffer implements Serializable {
    public String queryDate;
    public String startQueryDate;
    public String endQueryDate;

    public String getStartQueryDate() {
        return startQueryDate;
    }

    public void setStartQueryDate(String startQueryDate) {
        this.startQueryDate = startQueryDate;
    }

    public String getEndQueryDate() {
        return endQueryDate;
    }

    public void setEndQueryDate(String endQueryDate) {
        this.endQueryDate = endQueryDate;
    }

    public String getQueryDate() {
        return queryDate;
    }

    public void setQueryDate(String queryDate) {
        this.queryDate = queryDate;
    }
}
