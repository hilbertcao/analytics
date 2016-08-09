package com.qbao.analytics.test.enums;

/**
 * Created by caopengfei on 2016/8/9.
 */
public enum EventType {

    登录("/login.do"),登出("/logout.do"),签到("/qiandao.do")
            ,创建订单("/order.do");

    private EventType(String url){

        this.url = url;
    }

    private String url;

}
