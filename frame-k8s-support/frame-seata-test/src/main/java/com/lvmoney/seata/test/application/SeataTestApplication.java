package com.lvmoney.seata.test.application;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@SpringBootApplication(scanBasePackages = {"com.lvmoney.**"}, exclude = DataSourceAutoConfiguration.class)
@Configuration
@MapperScan("com.lvmoney.seata.test.dao*")
public class SeataTestApplication {
    //shiro,jwt,redis整合成功。1、事务注解是否生效校验。2、自定义shiro标签中的url的过滤
    public static void main(String[] args) {
        SpringApplication.run(SeataTestApplication.class, args);
    }


}