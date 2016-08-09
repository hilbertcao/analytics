package com.qbao.analytics.test.enums;

/**
 * Created by caopengfei on 2016/8/9.
 */
public enum TeminalType {

    CHROME("pc")
    ,IE11("android")
    ,SAFARI5("ios");
    private TeminalType(String type){

        this.type = type;
    }
     private String type;
}
