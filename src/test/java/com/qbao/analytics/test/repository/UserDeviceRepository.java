package com.abc.analytics.test.repository;

import com.abc.analytics.test.Device;
import com.abc.analytics.test.User;
import com.abc.analytics.test.enums.BrowerType;
import com.abc.analytics.test.enums.TeminalType;
import com.abc.analytics.test.generator.IPGenerator;

import java.util.*;

/**
 * Created by caopengfei on 2016/8/9.
 */
public class UserDeviceRepository {

    private UserRepository userRepository;
    private Map<String, List<Device>> userDeviceMap;

    public UserDeviceRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
        List<User> users = userRepository.getAllUsers();
        userDeviceMap = new HashMap<String, List<Device>>(users.size());
        userDeviceMap = new HashMap<String, List<Device>>(users.size());

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

    public List<Device> getUserDevices(String userId) {

        return userDeviceMap.get(userId);
    }

}
