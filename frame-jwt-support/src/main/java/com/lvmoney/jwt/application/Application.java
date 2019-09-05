package com.lvmoney.jwt.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(scanBasePackages = {"com.lvmoney.**"})
public class Application {
    //TODO 1、目前由于单模块的测试未最终确认，pom的配置还是基于能够单独启动的配置
    //2、如上基于1中的情况，系统中有controller和启动主方法，能够单独跑起来测试
    //3、考虑是否需要把接口实现公布出去，三方引入该模块的时候自实现。目前考虑就放到该模块，三方只需要引入调用即可
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
