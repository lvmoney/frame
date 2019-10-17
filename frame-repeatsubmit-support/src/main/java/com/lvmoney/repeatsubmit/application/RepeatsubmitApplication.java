package com.lvmoney.repeatsubmit.application;/**
 * 描述:
 * 包名:com.lvmoney.provider.application
 * 版本信息: 版本1.0
 * 日期:2019/8/16
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/16 11:43
 */
@SpringBootApplication(scanBasePackages = {"com.lvmoney.**"})
public class RepeatsubmitApplication {
    public static void main(String[] args) {
        SpringApplication.run(RepeatsubmitApplication.class, args);
    }


}
