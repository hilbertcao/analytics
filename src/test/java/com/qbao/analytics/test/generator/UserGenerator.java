package com.qbao.analytics.test.generator;

import com.qbao.analytics.test.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caopengfei on 2016/8/9.
 */
public class UserGenerator {

    public static List<User> generateUsers(int n){

        List<User> users = new ArrayList<User>();
        for(int i=0;i<n;i++){

            User user = new User(i,"user"+i);
            users.add(user);
        }
        return users;
    }
}
