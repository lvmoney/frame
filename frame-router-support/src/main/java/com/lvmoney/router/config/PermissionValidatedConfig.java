/**
 * 描述:
 * 包名:com.lvmoney.router.config
 * 版本信息: 版本1.0
 * 日期:2019年1月9日  下午3:59:42
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.router.config;

import java.lang.reflect.Method;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月9日 下午3:59:42
 */

public class PermissionValidatedConfig {
    private RouterServiceConfig routerServiceConfig;

    private Method method;

    private String role;

    public PermissionValidatedConfig(Method method, String role) {
        super();
        this.method = method;
        this.role = role;
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

    public String getRole() {
        return role;
    }
}
