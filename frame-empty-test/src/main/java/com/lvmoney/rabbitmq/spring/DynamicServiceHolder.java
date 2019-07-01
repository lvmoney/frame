package com.lvmoney.rabbitmq.spring;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lvmoney.rabbitmq.config.DynamicServiceConfig;

/**
 * @describe：根据定义的uri获得对应的接口实现方法
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年12月29日 上午11:32:20
 */
public class DynamicServiceHolder {
    private static final Logger logger = LoggerFactory.getLogger(DynamicServiceHolder.class);
    public Map<String, DynamicServiceConfig> serviceMap = new java.util.concurrent.ConcurrentHashMap<>();

    private static class InstanceHolder {
        private static final DynamicServiceHolder INSTANCE = new DynamicServiceHolder();
    }

    private DynamicServiceHolder() {
    }

    public static final DynamicServiceHolder getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void put(DynamicServiceConfig service) {
        serviceMap.put(service.getBeanName(), service);
        if (logger.isDebugEnabled()) {
            logger.info("service {} mapping {} ", service.getClazz().getName(), service.getBeanName());
        }
    }

}
