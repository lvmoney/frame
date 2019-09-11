package com.lvmoney.oauth2.center.server.config;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;

import com.github.benmanes.caffeine.cache.Caffeine;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 * @Configuration
 * @EnableCaching
 */

public class CaffeineCacheConfiguration {
    /**
     * @describe: 一级缓存管理
     * @param: []
     * @return: org.springframework.cache.CacheManager
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 10:01
     */
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        ArrayList<CaffeineCache> caches = new ArrayList<CaffeineCache>();
//        for (CachesEnum c : CachesEnum.values()) {
//            caches.add(new CaffeineCache(c.name(), Caffeine.newBuilder().expireAfterWrite(c.getTtl(), TimeUnit.SECONDS)
//                    .maximumSize(c.getMaxSize()).build()));
//        }
        cacheManager.setCaches(caches);
        return cacheManager;
    }

}
