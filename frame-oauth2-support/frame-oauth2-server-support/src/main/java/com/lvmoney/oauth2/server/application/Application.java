package com.lvmoney.oauth2.server.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @describe：oauth2 服务端启动入口主方法
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月16日 上午11:44:32
 */
@SpringBootApplication(scanBasePackages = {"com.lvmoney.**"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * @return 2019年1月16日上午11:45:00
     * @describe:解决前后端分离跨域问题
     * @author: lvmoney /xxxx科技有限公司
     */
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
