package com.lvmoney.cloud.zuul.config;

import com.lvmoney.cloud.zuul.filter.FrameZuulFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Configuration
public class Zuulconfig {
    @Bean
    public FrameZuulFilter tokenFilter() {
        return new FrameZuulFilter();
    }
}
