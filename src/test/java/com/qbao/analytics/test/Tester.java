package com.qbao.analytics.test;

import com.google.gson.Gson;
import com.qbao.analytics.test.enums.EventType;
import com.qbao.analytics.test.repository.Repositorys;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by caopengfei on 2016/8/10.
 */
public class Tester {

    private static File analyticsLogFile = new File("D://data/analytics.log");
    private static File appLogfile = new File("D://data/app.log");

    public static void main(String[] args) {

        Collection<User> users = Repositorys.getUserRepository().getAllUsers();
        for(User user:users){

            //默认选第一个常用设备
            Device device = Repositorys.getUserDeviceRepository().getUserDevices(user.getUserId()).get(0);
            UserSession userSession = new UserSession(user,device);

            for(int i=0;i<10;i++){
                List<Event> events = Repositorys.getDeviceEventRepository().getEventList(device.getClientId());
                Event event = userSession.generateEvent();
                logApp(events,event);
                logEvent(event);
            }
        }

    }

    /**
     * 记录在
     * @param events
     * @param event
     */
    public static void logApp(List<Event> events,Event event){

        try {
            FileUtils.write(appLogfile,toAppString(events,event)+"\n",true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String toAppString(List<Event> events,Event event){

        String ip =event.getDevice().getIp();
        String receiveTimeStamp = new StringBuilder(System.currentTimeMillis()+"").insert(10,".").toString();

        List<Map> recordList = new ArrayList<Map>();
        if(events != null){
            for(Event item:events){
                recordList.add(convertEventToMap(item));
            }
        }

        Gson gson = new Gson();
        String recordListStr = null;

        try {
            recordListStr = URLEncoder.encode(gson.toJson(recordList),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "{\"serverType\":\"nginx\",\"meta\":{\"ip\":\""+ip+"\",\"time\":\""+receiveTimeStamp+"\",\"referer\":\"http://"+event.getDomain()+"\",\"userAgent\":\""+event.getDevice().getBrowerType().getUserAgent()+"\",\"clientId\":\""+event.getDevice().getClientId()+"\",\"event\":\"-\",\"recordList\":\""+recordListStr+"\"}}";
    }

    public static Map convertEventToMap(Event event){

        Map map = new HashMap();
        map.put("domain",event.getDomain());
        if(event.getEventType() == EventType.登录){
            map.put("userName",event.getUser().getUserName());
        }else{
            map.put("userId",event.getUser().getUserId());
        }
        map.put("url",event.getEventType().getUrl());
        map.put("eventTime",System.currentTimeMillis());
        return map;
    }

    /**
     *
     * @param event
     */
    public static void logEvent(Event event){
        try {
            FileUtils.write(analyticsLogFile,event.toString()+"\n",true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
