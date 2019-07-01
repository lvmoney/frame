/**
 * 描述:
 * 包名:com.lvmoney.router.config
 * 版本信息: 版本1.0
 * 日期:2019年1月9日  下午3:59:25
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.rabbitmq.config;

import org.springframework.context.ApplicationContext;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月9日 下午3:59:25
 */

public class DynamicServiceConfig {
    private Class<?> clazz;

    private Object beanInstance;

    private transient ApplicationContext applicationContext;

    private transient String beanName;

    public DynamicServiceConfig(Class<?> clazz, Object beanInstance, ApplicationContext applicationContext,
                                String beanName) {
        super();
        this.clazz = clazz;
        this.beanInstance = beanInstance;
        this.applicationContext = applicationContext;
        this.beanName = beanName;
    }

    public Object getBeanInstance() {
        return beanInstance;
    }

    public Class<?> getClazz() {
        return clazz;
    }


    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public String getBeanName() {
        return beanName;
    }
}
