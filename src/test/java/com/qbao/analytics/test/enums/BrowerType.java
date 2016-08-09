package com.qbao.analytics.test.enums;

/**
 * Created by caopengfei on 2016/8/9.
 */
public enum BrowerType {

    CHROME("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36")
    ,IE11("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.10240")
    ,SAFARI5("User-Agent:Mozilla/5.0 (Windows; U; Windows NT 6.1; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50");
    private BrowerType(String userAgent){

        this.userAgent = userAgent;
    }
     private String userAgent;
}
