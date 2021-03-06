/**
 * 描述:
 * 包名:com.lvmoney.router.config
 * 版本信息: 版本1.0
 * 日期:2019年1月2日  上午10:16:43
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.router.config;

import java.lang.reflect.Method;

/**
 * @describe：方法入参校验注解
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月2日 上午10:16:43
 */

public class ParamValidatedConfig {
    private RouterServiceConfig routerServiceConfig;

    private Method method;

    private boolean isValidate;

    public ParamValidatedConfig(Method method, boolean isValidate) {
        super();
        this.method = method;
        this.isValidate = isValidate;
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

    public boolean isValidate() {
        return isValidate;
    }
}
