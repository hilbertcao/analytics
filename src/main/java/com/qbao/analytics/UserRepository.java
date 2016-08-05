package com.qbao.analytics;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class UserRepository {

    public final static Map<String,Integer> map = new HashMap<String,Integer>();

    static {

        map.put("a",1);
        map.put("b",2);
        map.put("c",3);
        map.put("d",4);
        map.put("e",5);
        map.put("f",6);
        map.put("g",7);
        map.put("user1",8);
        map.put("user2",9);
        map.put("user3",10);

    }

    public static int getUserId(String userName){

        Integer uid = map.get(userName);
        if(uid!=null){

            return uid.intValue();
        }
        return 0;
    }

}
