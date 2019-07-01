package com.lvmoney.cloud.zuul.config;

import com.lvmoney.cloud.zuul.filter.FrameZuulFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Zuulconfig {
    @Bean
    public FrameZuulFilter tokenFilter() {
        return new FrameZuulFilter();
    }
}
