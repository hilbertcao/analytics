package com.qbao.analytics.test;

/**
 * 事件产生环境
 */
public class EventEnvironment {

    private User user;
    private Device device;

    public EventEnvironment(User user, Device device) {
        this.user = user;
        this.device = device;
    }

}
