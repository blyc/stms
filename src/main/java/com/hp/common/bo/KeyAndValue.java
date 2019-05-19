package com.hp.common.bo;

public class KeyAndValue {
    private Object key;
    private Object value;
    private Object obj;


    public KeyAndValue(Object key, Object value) {
        this.key = key;
        this.value = value;
    }

    public KeyAndValue(Object key, Object value, Object obj) {
        this.key = key;
        this.value = value;
        this.obj = obj;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
