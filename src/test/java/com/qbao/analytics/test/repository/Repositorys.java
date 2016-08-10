package com.qbao.analytics.test.repository;

/**
 * Created by caopengfei on 2016/8/10.
 */
public class Repositorys {
    private static DeviceEventRepository deviceEventRepository;
    private static UserRepository userRepository;
    private static UserDeviceRepository userDeviceRepository;
    static{

        userRepository = new UserRepository(100);
        userDeviceRepository = new UserDeviceRepository(userRepository);
        deviceEventRepository = new DeviceEventRepository();
    }

    public static DeviceEventRepository getDeviceEventRepository() {
        return deviceEventRepository;
    }

    public static UserRepository getUserRepository() {
        return userRepository;
    }

    public static UserDeviceRepository getUserDeviceRepository() {
        return userDeviceRepository;
    }
}
