package com.lvmoney.cloud.zipkin.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin2.server.internal.EnableZipkinServer;

@EnableDiscoveryClient
@SpringBootApplication
@EnableZipkinServer
public class Sb2scZipkinApplication {
    public static void main(String[] args) {
        SpringApplication.run(Sb2scZipkinApplication.class, args);
    }
}
