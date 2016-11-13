package com.abc.analytics.test;

import com.abc.analytics.test.enums.EventType;
import com.abc.analytics.test.repository.Repositorys;

import java.util.List;
import java.util.Random;


/**
 * 事件产生环境
 */
public class UserSession {

    private User user;
    private Device device;
    private EventType currenEventType;

    public UserSession(User user, Device device) {
        this.user = user;
        this.device = device;
        currenEventType  = EventType.登录;
    }

    /**
     * 事件
     * @return
     */
    public Event generateEvent(){

        //增加一点概率切换设备
        Random random = new Random();
        int i = random.nextInt(100);
        if(i>=98){
            List<Device> deviceList = Repositorys.getUserDeviceRepository().getUserDevices(user.getUserId());
            int deviceIndex = random.nextInt(deviceList.size()-1);
            device = deviceList.get(deviceIndex+1);
            currenEventType = EventType.登录;
        }

        String domain = "www.abc.com";
        Event event = new Event(device,user,currenEventType,domain,System.currentTimeMillis());
        currenEventType = currenEventType.next();
        Repositorys.getDeviceEventRepository().putDeviceEvent(device.getClientId(),event);
        return event;
    }

}
