package com.lvmoney.cloud.gateway.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
