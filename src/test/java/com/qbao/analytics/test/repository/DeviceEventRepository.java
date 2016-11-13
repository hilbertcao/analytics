package com.abc.analytics.test.repository;

import com.abc.analytics.test.Event;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by caopengfei on 2016/8/10.
 */
public class DeviceEventRepository {

    private Map<String,List<Event>> deviceEventMap;

    public DeviceEventRepository() {
        this.deviceEventMap = new HashMap<String, List<Event>>();
    }

    public List<Event> getEventList(String deviceId){

        return deviceEventMap.get(deviceId);
    }

    public void putDeviceEvent(String deviceId, Event event){

        if(deviceId == null || deviceId.trim().equals("")){

            throw new IllegalArgumentException("deviceId不能为空");
        }

        if(event == null){

            throw new IllegalArgumentException("event不能为空");
        }

        List<Event> eventQueue = getEventList(deviceId);
        if(eventQueue == null){

            eventQueue = new ArrayList<Event>(5);
        }
        if(eventQueue.size() >= 5){

            eventQueue.remove(0);
        }
        eventQueue.add(event);
        deviceEventMap.put(deviceId,eventQueue);

    }
}
