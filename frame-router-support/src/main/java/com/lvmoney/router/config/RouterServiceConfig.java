package com.lvmoney.router.config;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;

/**
 * @describe：接口注解配置
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年12月29日 上午11:27:34
 */
public class RouterServiceConfig {

    private Class<?> clazz;

    private Object beanInstance;

    private String path;

    private Map<String, RouterMethodConfig> methods = new java.util.concurrent.ConcurrentHashMap<>();

    private transient ApplicationContext applicationContext;

    private transient String beanName;

    public RouterServiceConfig(Class<?> clazz, Object beanInstance, String path, ApplicationContext applicationContext,
                               String beanName) {
        super();
        this.clazz = clazz;
        this.beanInstance = beanInstance;
        this.path = path;
        this.applicationContext = applicationContext;
        this.beanName = beanName;
        if (StringUtils.isBlank(path)) {
            this.path = beanName;
        }
    }

    public Object getBeanInstance() {
        return beanInstance;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public String getPath() {
        return path;
    }

    public Map<String, RouterMethodConfig> getMethods() {
        return methods;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public String getBeanName() {
        return beanName;
    }

    public void addMethod(RouterMethodConfig routerMethodConfig) {
        routerMethodConfig.setRouterServiceConfig(this);
        methods.put(routerMethodConfig.getName(), routerMethodConfig);
    }

    public RouterMethodConfig getMethod(String name) {
        return methods.get(name);
    }

}