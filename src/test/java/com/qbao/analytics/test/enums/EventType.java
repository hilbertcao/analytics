package com.abc.analytics.test.enums;

import java.util.Random;

/**
 * Created by caopengfei on 2016/8/9.
 */
public enum EventType {

    登录("/login.do") {
        public EventType next() {
            Random random = new Random();
            int index = random.nextInt(3);
            int i=0;
            for(EventType eventType:values()){
                if(eventType != 登录){

                    i++;

                }
                if(index == i){

                    return eventType;
                }
            }
            return null;
        }
    },登出("/logout.do") {
        public EventType next() {
            Random random = new Random();
            int index = random.nextInt(3);
            int i=0;
            for(EventType eventType:values()){
                if(index == i){

                    return eventType;
                }

                if(eventType != 登录){

                    i++;

                }
            }
            return null;
        }
    },签到("/qiandao.do") {
        public EventType next() {
            Random random = new Random();
            int index = random.nextInt(3);
            int i=0;
            for(EventType eventType:values()){

                if(index == i){

                    return eventType;
                }

                if(eventType != 登录){

                    i++;

                }
            }
            return null;
        }
    },创建订单("/order.do") {
        public EventType next() {
            Random random = new Random();
            int index = random.nextInt(3);
            int i=0;
            for(EventType eventType:values()){

                if(index == i){

                    return eventType;
                }

                if(eventType != 登录){

                    i++;

                }

            }
            return null;
        }
    };

    private EventType(String url){

        this.url = url;
    }

    private String url;

    public abstract EventType next();

    /**
     * 随机获取一个event
     * @return
     */
    public static EventType getRandom(){
        Random random = new Random();
        int index = random.nextInt(4);
        int i=0;
        for(EventType eventType:values()){
            if(index == i){

                return eventType;
            }
            i++;
        }
        return null;
    }

    public String getUrl() {
        return url;
    }
}
