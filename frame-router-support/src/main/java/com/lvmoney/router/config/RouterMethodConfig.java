package com.lvmoney.router.config;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;

/**
 * @describe：方法注解配置
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年12月29日 上午11:27:23
 */
public class RouterMethodConfig {

    private RouterServiceConfig routerServiceConfig;

    private Method method;

    private String name;

    private Class<?> parameterType;

    public RouterMethodConfig(Method method, String name, Class<?> parameterType) {
        super();
        this.method = method;
        if (StringUtils.isNotBlank(name)) {
            this.name = name;
        } else {
            this.name = method.getName();
        }
        this.parameterType = parameterType;
    }

    void setRouterServiceConfig(RouterServiceConfig routerServiceConfig) {
        this.routerServiceConfig = routerServiceConfig;
    }

    public RouterServiceConfig getRouterServiceConfig() {
        return routerServiceConfig;
    }

    public Method getMethod() {
        return method;
    }

    public String getName() {
        return name;
    }

    public boolean hasParameter() {
        return parameterType != null;
    }

    public Class<?> getParameterType() {
        return parameterType;
    }

}
