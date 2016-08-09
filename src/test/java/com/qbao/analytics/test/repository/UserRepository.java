package com.qbao.analytics.test.repository;

import com.qbao.analytics.test.User;
import com.qbao.analytics.test.generator.UserGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by caopengfei on 2016/8/9.
 */
public class UserRepository {


    private Map<Integer,User> userMap;
    private List<User> users;
    private int userTotalNum;

    public UserRepository(int userTotalNum) {
        this.userTotalNum = userTotalNum;
        this.userMap = new HashMap<Integer, User>(userTotalNum);
        this.users = UserGenerator.generateUsers(userTotalNum);
        for(User user:users){

            userMap.put(user.getUserId(),user);
        }
    }

    public User getUser(Integer userId){

        return userMap.get(userId);
    }

    public List<User> getAllUsers(){

        return users;
    }

}