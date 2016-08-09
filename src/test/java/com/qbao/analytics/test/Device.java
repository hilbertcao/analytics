package com.qbao.analytics.test;

import com.qbao.analytics.test.enums.BrowerType;

/**
 * 设备(1个浏览器也相当于一个device)
 */
public class Device {

    private String ip;
    private BrowerType browerType;
    private String clientId;

    public Device(String ip, BrowerType browerType, String clientId) {
        this.ip = ip;
        this.browerType = browerType;
        this.clientId = clientId;
    }

    public String getIp() {
        return ip;
    }

    public BrowerType getBrowerType() {
        return browerType;
    }

    public String getClientId() {
        return clientId;
    }
}
