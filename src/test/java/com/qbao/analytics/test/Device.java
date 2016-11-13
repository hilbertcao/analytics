package com.abc.analytics.test;

import com.abc.analytics.test.enums.BrowerType;
import com.abc.analytics.test.enums.TeminalType;

/**
 * 设备(1个浏览器也相当于一个device)
 */
public class Device {

    private String ip;
    private BrowerType browerType;
    private TeminalType teminalType;
    private String clientId;

    public Device(String ip, BrowerType browerType, TeminalType teminalType, String clientId) {
        this.ip = ip;
        this.browerType = browerType;
        this.teminalType = teminalType;
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

    public TeminalType getTeminalType() {
        return teminalType;
    }
}
