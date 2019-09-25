package com.lvmoney.k8s.admin.application;/**
 * 描述:
 * 包名:com.lvmoney.k8s.admin.application
 * 版本信息: 版本1.0
 * 日期:2019/9/20
 * Copyright XXXXXX科技有限公司
 */


import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/9/20 9:48
 */
@SpringBootApplication(scanBasePackages = {"com.lvmoney.**"})
@EnableAdminServer
public class AdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminServerApplication.class, args);
    }

}
