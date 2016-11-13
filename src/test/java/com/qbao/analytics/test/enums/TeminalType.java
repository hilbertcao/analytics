package com.abc.analytics.test.enums;

/**
 * Created by caopengfei on 2016/8/9.
 */
public enum TeminalType {

    PC("pc")
    ,ANDROID("android")
    ,IOS("ios");
    private TeminalType(String type){

        this.type = type;
    }
     private String type;

    public String getType() {
        return type;
    }
}
