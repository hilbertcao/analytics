package com.qbao.analytics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by Administrator on 2016/3/29.
 */
@SpringBootApplication
@Configuration("config.xml") public class Main {

    public static void main(String[] args) throws UnsupportedEncodingException {

        SpringApplication.run(Main.class, args);
    }
}