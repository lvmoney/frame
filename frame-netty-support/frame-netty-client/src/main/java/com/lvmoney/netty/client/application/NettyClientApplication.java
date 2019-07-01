package com.lvmoney.netty.client.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lvmoney
 * @date 2018/8/15 - 12:29
 */
@SpringBootApplication(scanBasePackages = {"com.lvmoney.**"})
public class NettyClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(NettyClientApplication.class, args);
    }
}