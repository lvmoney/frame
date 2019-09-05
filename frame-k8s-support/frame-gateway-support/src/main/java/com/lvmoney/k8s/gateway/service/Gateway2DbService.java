package com.lvmoney.k8s.gateway.service;/**
 * 描述:
 * 包名:com.lvmoney.k8s.gateway.service
 * 版本信息: 版本1.0
 * 日期:2019/8/15
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/15 17:21
 */
public interface Gateway2DbService {
    default public List<RouteDefinition> initRouteDefinition() {
        System.out.println("test");
        return null;
    }
}
