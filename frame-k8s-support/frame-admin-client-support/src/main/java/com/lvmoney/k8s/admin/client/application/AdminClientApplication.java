package com.lvmoney.k8s.admin.client.application;/**
 * 描述:
 * 包名:com.lvmoney.k8s.admin.client.application
 * 版本信息: 版本1.0
 * 日期:2019/9/20
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/9/20 10:23
 */
@SpringBootApplication(scanBasePackages = {"com.lvmoney.**"})
public class AdminClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminClientApplication.class, args);
    }
}
