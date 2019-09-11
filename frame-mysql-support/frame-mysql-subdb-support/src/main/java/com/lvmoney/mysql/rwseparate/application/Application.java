package com.lvmoney.mysql.rwseparate.application;/**
 * 描述:
 * 包名:com.lvmoney.mysql.separate.application
 * 版本信息: 版本1.0
 * 日期:2019/9/6
 * Copyright XXXXXX科技有限公司
 */


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/9/6 14:44
 */
@SpringBootApplication(exclude = JtaAutoConfiguration.class, scanBasePackages = {"com.lvmoney.**"})
@MapperScan(basePackages = {"com.lvmoney.mysql.subdb.dao*"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}