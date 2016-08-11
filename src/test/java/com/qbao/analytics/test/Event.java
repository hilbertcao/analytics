package com.qbao.analytics.test;

import com.google.gson.Gson;
import com.qbao.analytics.test.enums.EventType;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by caopengfei on 2016/8/10.
 */
public class Event {

    private Device device;
    private User user;
    private EventType eventType;
    private String domain;

    private long eventTime;

    public Event(Device device, User user, EventType eventType, String domain,long eventTime) {
        this.device = device;
        this.user = user;
        this.eventType = eventType;
        this.domain = domain;
        this.eventTime = eventTime;
    }

    public Device getDevice() {
        return device;
    }

    public User getUser() {
        return user;
    }

    public EventType getEventType() {
        return eventType;
    }

    public String getDomain() {
        return domain;
    }

    @Override
    public String toString() {
        String ip =device.getIp();
        String receiveTimeStamp = new StringBuilder(eventTime+"").insert(10,".").toString();
        Map map = new HashMap();
        map.put("domain",domain);
        if(eventType == EventType.登录){
            map.put("userName",user.getUserName());
        }else{
            map.put("userId",user.getUserId());
        }
        map.put("url",eventType.getUrl());
        map.put("eventTime",System.currentTimeMillis());
        map.put("teminalType",device.getTeminalType().getType());

        Gson gson = new Gson();
        String eventStr=null;
        try {
            eventStr = URLEncoder.encode(gson.toJson(map),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "{\"serverType\":\"nginx\",\"meta\":{\"ip\":\""+ip+"\",\"time\":\""+receiveTimeStamp+"\",\"referer\":\"http://"+
                domain+"\",\"userAgent\":\""+device.getBrowerType().getUserAgent()+"\",\"clientId\":\""+
                device.getClientId()+"\",\"event\":\""+eventStr+"\"}}";
    }

    public long getEventTime() {
        return eventTime;
    }
}
