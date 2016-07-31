package com.qbao.analytics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2016/3/29.
 */
@SpringBootApplication @Configuration("config.xml") public class Main {

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
    }
}
