package com.lvmoney.k8s.logback.redis.application;/**
 * 描述:
 * 包名:com.lvmoney.k8s.logback.application
 * 版本信息: 版本1.0
 * 日期:2019/8/22
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/22 17:59
 */
@SpringBootApplication(scanBasePackages = {"com.lvmoney.**"})
public class LogbackApplication {
    public static void main(String[] args) {
        SpringApplication.run(LogbackApplication.class, args);
    }
}
