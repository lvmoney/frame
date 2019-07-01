package com.lvmoney.k8s.docker.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication(scanBasePackages = {"com.lvmoney.**"})
@Configuration
public class DockerApplication {
    //shiro,jwt,redis整合成功。1、事务注解是否生效校验。2、自定义shiro标签中的url的过滤
    public static void main(String[] args) {
        SpringApplication.run(DockerApplication.class, args);
    }

}