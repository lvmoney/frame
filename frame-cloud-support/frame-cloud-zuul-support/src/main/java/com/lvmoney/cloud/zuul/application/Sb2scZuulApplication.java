package com.lvmoney.cloud.zuul.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.lvmoney.**"})
@EnableZuulProxy
@RefreshScope
public class Sb2scZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(Sb2scZuulApplication.class, args);
    }
}
