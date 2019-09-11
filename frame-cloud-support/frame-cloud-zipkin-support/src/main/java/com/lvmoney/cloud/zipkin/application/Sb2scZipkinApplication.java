package com.lvmoney.cloud.zipkin.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin2.server.internal.EnableZipkinServer;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableZipkinServer
public class Sb2scZipkinApplication {
    public static void main(String[] args) {
        SpringApplication.run(Sb2scZipkinApplication.class, args);
    }
}
