package com.lvmoney.k8s.gateway.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by Administrator on 2019/5/14.
 */
@SpringBootApplication(scanBasePackages = {"com.lvmoney.**"})
@EnableFeignClients(basePackages = {"com.lvmoney.**"})
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
