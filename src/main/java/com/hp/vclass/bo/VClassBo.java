package com.hp.vclass.bo;

import com.hp.common.model.VClass;

/**
 * @Author: boy
 * @Date: 2018/04/08
 * @Description:
 */
public class VClassBo extends VClass {
    private Long totalsum;
    private Long totalclass;

    public Long getTotalclass() {
        return totalclass;
    }

    public void setTotalclass(Long totalclass) {
        this.totalclass = totalclass;
    }

    public Long getTotalsum() {
        return totalsum;
    }

    public void setTotalsum(Long totalsum) {
        this.totalsum = totalsum;
    }
}
