package com.lvmoney.k8s.feign.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//
//@SpringBootApplication(scanBasePackages = {"com.lvmoney.**"})
//@EnableFeignClients(basePackages = {"com.lvmoney.**"})
public class Application {
    //shiro,jwt,redis整合成功。1、事务注解是否生效校验。2、自定义shiro标签中的url的过滤
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
