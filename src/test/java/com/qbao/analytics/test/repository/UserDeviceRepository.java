package com.qbao.analytics.test.repository;

import com.qbao.analytics.test.Device;
import com.qbao.analytics.test.User;

import java.util.List;
import java.util.Map;

/**
 * Created by caopengfei on 2016/8/9.
 */
public class UserDeviceRepository {

    private UserRepository userRepository;
    private Map<Integer,List<Device>> userDevices;

    public UserDeviceRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
        List<User> users = userRepository.getAllUsers();
    }

}
