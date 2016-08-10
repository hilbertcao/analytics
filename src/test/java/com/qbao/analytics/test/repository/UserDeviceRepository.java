package com.qbao.analytics.test.repository;

import com.qbao.analytics.test.Device;
import com.qbao.analytics.test.User;
import com.qbao.analytics.test.enums.BrowerType;
import com.qbao.analytics.test.enums.TeminalType;
import com.qbao.analytics.test.generator.IPGenerator;

import java.util.*;

/**
 * Created by caopengfei on 2016/8/9.
 */
public class UserDeviceRepository {

    private UserRepository userRepository;
    private Map<Integer, List<Device>> userDeviceMap;

    public UserDeviceRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
        List<User> users = userRepository.getAllUsers();
        userDeviceMap = new HashMap<Integer, List<Device>>(users.size());

        //认为一个用户可能有几个ip,以及有几个浏览器
        for (User user : users) {

            List<Device> devices = new ArrayList<Device>();
            Collection<String> ipList = IPGenerator.generateRandomIpLisList(2);
            for (String ip : ipList) {
                for (BrowerType browerType : BrowerType.values()) {
                    String clientId = UUID.randomUUID().toString();
                    Device device = new Device(ip, browerType, TeminalType.PC, clientId);
                    devices.add(device);
                }
            }

            userDeviceMap.put(user.getUserId(), devices);
        }
    }

    public List<Device> getUserDevices(int userId) {

        return userDeviceMap.get(userId);
    }

}
