package com.lvmoney.k8s.seata.server.application;

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

/**
 * Created by Administrator on 2019/6/26.
 */

@SpringBootApplication(scanBasePackages = {"com.lvmoney.**"}, exclude = DataSourceAutoConfiguration.class)
@Configuration
@MapperScan("com.lvmoney.k8s.seata.server.dao*")
public class SeataServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeataServerApplication.class, args);
    }

}
