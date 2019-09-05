package com.lvmoney.k8s.gateway.service;/**
 * 描述:
 * 包名:com.lvmoney.k8s.gateway.service
 * 版本信息: 版本1.0
 * 日期:2019/8/15
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.k8s.gateway.ro.RouteDefinitionRo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/15 9:47
 */
public interface Gateway2RedisService {

    /**
     * @describe: 从redis中获得路由规则
     * @param: []
     * @return: java.util.List<org.springframework.cloud.gateway.route.RouteDefinition>
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/8/15 9:48
     */
    List<RouteDefinition> getRouteDefinition();

    void saveRouteDefinitions(RouteDefinitionRo routeDefinitionRo);

    boolean routeIdExist(String routeId);

    void deleteRouteId(String routeId);

    RouteDefinition getRouteDefinition(String routeId);
}
