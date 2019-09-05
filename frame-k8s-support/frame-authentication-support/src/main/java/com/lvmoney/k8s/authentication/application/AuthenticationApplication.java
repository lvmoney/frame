package com.lvmoney.k8s.authentication.application;/**
 * 描述:
 * 包名:com.lvmoney.customer.application
 * 版本信息: 版本1.0
 * 日期:2019/8/9
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/9 9:48
 */
@SpringBootApplication(scanBasePackages = {"com.lvmoney.**"})
@EnableFeignClients(basePackages = {"com.lvmoney.**"})
public class AuthenticationApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthenticationApplication.class, args);
    }
}
