package com.lvmoney.k8s.gateway.init;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/2/22
 * Copyright xxxx科技有限公司
 */


import com.lvmoney.k8s.gateway.ro.RouteDefinitionRo;
import com.lvmoney.k8s.gateway.service.Gateway2DbService;
import com.lvmoney.k8s.gateway.service.Gateway2RedisService;
import com.lvmoney.k8s.gateway.service.ServerService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Service
public class AfterStartupInit implements InitializingBean {
    @Autowired
    Gateway2DbService gateway2DbService;

    @Autowired
    Gateway2RedisService gateway2RedisService;
    @Autowired
    GatewayProperties gatewayProperties;
    @Autowired
    ServerService serverService;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<RouteDefinition> routeDefinitions = gateway2DbService.initRouteDefinition();
        routeDefinitions = routeDefinitions != null ? routeDefinitions : gatewayProperties.getRoutes();
        RouteDefinitionRo routeDefinitionRo = new RouteDefinitionRo();
        Map<String, RouteDefinition> routeDefinitionMap = new HashMap<>();
        routeDefinitions.stream().forEach(e -> {
            try {
                URI realUri = new URI(serverService.getRealRequstUri(e.getUri()));
                e.setUri(realUri);
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
            routeDefinitionMap.put(e.getId(), e);
        });
        routeDefinitionRo.setRouteDefinitions(routeDefinitionMap);
        routeDefinitionRo.setExpire(null);
        gateway2RedisService.saveRouteDefinitions(routeDefinitionRo);
    }


}
