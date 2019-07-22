package com.lvmoney.cloud.gateway.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * Created by Administrator on 2019/5/14.
 */
@SpringBootApplication(scanBasePackages = {"com.lvmoney.**"})
public class Application {
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(r -> r.path("/jianshu")
//                        .uri("http://www.baidu.com")
//                ).build();
//    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
