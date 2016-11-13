package com.abc.analytics.test.generator;

import com.abc.analytics.test.User;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by caopengfei on 2016/8/9.
 */
public class UserGenerator {


    public static List<User> generateUsers(int n){

        String[] formats = new String[]{
                "a%d124@163.com","a201%d201601@qq.com","eye%d","%d1001@qq.com"
        };
        List<User> users = new ArrayList<User>();
        Random random = new Random();
        for(int i=0;i<n;i++){

            String name = null;

            int left = random.nextInt(1000)%formats.length;
            if(left != 0 ){
                if(left == 1){
                    name = String.format("5%s31",StringUtils.leftPad(""+i,6,"1"));
                }else{
                    String format = formats[random.nextInt(1000)%formats.length];
                    name = String.format(format,i);
                }

            }else{

                name = String.format("134%s31",StringUtils.leftPad(""+i,6,"1"));
            }

            String userId = String.format("u_10%s9",StringUtils.rightPad(i+"",6,"1"));

            User user = new User(userId,name);
            users.add(user);
        }
        return users;
    }
}
