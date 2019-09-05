package com.lvmoney.k8s.gateway.filter;/**
 * 描述:
 * 包名:com.lvmoney.k8s.gateway.filter
 * 版本信息: 版本1.0
 * 日期:2019/8/8
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.common.constant.CommonConstant;
import com.lvmoney.common.exceptions.BusinessException;
import com.lvmoney.common.utils.IpUtil;
import com.lvmoney.common.utils.vo.ResultData;
import com.lvmoney.k8s.gateway.constant.GatewayConstant;
import com.lvmoney.k8s.gateway.exception.GatewayException;
import com.lvmoney.k8s.gateway.properties.GatewayConfigProp;
import com.lvmoney.k8s.gateway.ro.WhiteListRo;
import com.lvmoney.k8s.gateway.server.AuthenticationServerConfig;
import com.lvmoney.k8s.gateway.service.Gateway2RedisService;
import com.lvmoney.k8s.gateway.service.WhiteListService;
import com.lvmoney.k8s.gateway.utils.ExceptionUtil;
import com.lvmoney.k8s.gateway.utils.FilterMapUtil;
import com.lvmoney.k8s.gateway.vo.WhiteListVo;
import com.lvmoney.k8s.gateway.vo.resp.Oauth2TokenCheck;
import com.lvmoney.k8s.gateway.vo.resp.ShiroAuthorityCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/8 17:14
 */
@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {
    @Value("${frame.gateway.support:false}")
    String gatewaySupport;
    @Autowired
    private AuthenticationServerConfig authenticationServerConfig;
    @Autowired
    GatewayConfigProp gatewayConfigProp;
    @Autowired
    Gateway2RedisService gateway2RedisService;
    @Autowired
    WhiteListService whiteListService;
    @Value("${frame.white.support:false}")
    String whiteSupport;

    /**
     * @describe:1、白名单校验=====>2、是否需要gateway支持======>3、token校验=======>4、权限校验=======>5、服务访问
     * @param: [exchange, chain]
     * @return: reactor.core.publisher.Mono<java.lang.Void>
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/8/20 11:40
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        if (!whiteSupport.equals(GatewayConstant.FRAME_GATEWAY_SUPPORT_FALSE) && !whiteSupport.equals(GatewayConstant.FRAME_GATEWAY_SUPPORT_TRUE)) {
            return serverHttpResponse.writeWith(Flux.just(ExceptionUtil.filterExceptionHandle(serverHttpResponse, new BusinessException(GatewayException.Proxy.GATEWAY_SUPPORT_ERROR))));
        } else if (!isWhite(exchange)) {//白名单校验
            return serverHttpResponse.writeWith(Flux.just(ExceptionUtil.filterExceptionHandle(serverHttpResponse, new BusinessException(GatewayException.Proxy.GATEWAY_WHITE_CHECK_ERROR))));
        }
        String requestPath = exchange.getRequest().getPath().toString();
        String realPath = realPath(exchange);
        URI uri = exchange.getRequest().getURI();

        if (gatewaySupport.equals(GatewayConstant.FRAME_GATEWAY_SUPPORT_FALSE)) {//
            return chain.filter(exchange);
        } else if (!GatewayConstant.FRAME_GATEWAY_SUPPORT_FALSE.equals(gatewaySupport) && !GatewayConstant.FRAME_GATEWAY_SUPPORT_TRUE.equals(gatewaySupport)) {
            return serverHttpResponse.writeWith(Flux.just(ExceptionUtil.filterExceptionHandle(serverHttpResponse, new BusinessException(GatewayException.Proxy.GATEWAY_SUPPORT_ERROR))));
        }

        Map<String, String> filterChainDefinition = gatewayConfigProp.getfilterChainDefinitionMap();
        if (filterChainDefinition != null && FilterMapUtil.wildcardMatchMapKey(filterChainDefinition, requestPath, GatewayConstant.GATEWAY_REQUEST_IGNORE)) {// 在这里做判断
            return chain.filter(exchange);
        }

        String token = exchange.getRequest().getHeaders().getFirst("token");
        ResultData<Oauth2TokenCheck> oauth2TokenCheck = authenticationServerConfig.authenticationServer(token).oauth2CheckToken();
        if (!oauth2TokenCheck.isSuccess() || !oauth2TokenCheck.getData().isAdopt()) {
            return serverHttpResponse.writeWith(Flux.just(ExceptionUtil.filterExceptionHandle(serverHttpResponse, new BusinessException(GatewayException.Proxy.GATEWAY_TOKEN_CHECK_ERROR))));
        }
        ResultData<ShiroAuthorityCheck> shiroAuthorityCheck = authenticationServerConfig.authenticationServer(token).shiroCheckAuthority(realPath);
        if (!shiroAuthorityCheck.isSuccess() || !shiroAuthorityCheck.getData().isPass()) {
            return serverHttpResponse.writeWith(Flux.just(ExceptionUtil.filterExceptionHandle(serverHttpResponse, new BusinessException(GatewayException.Proxy.GATEWAY_SHIRO_CHECK_ERROR))));
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -9999;
    }

    /**
     * @describe:通过路由规则获得真正的请求的地址
     * @param: [exchange]
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/8/15 11:49
     */
    private String realPath(ServerWebExchange exchange) {

        Route route = exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        String routeId = route.getId();
        RouteDefinition routeDefinition = gateway2RedisService.getRouteDefinition(routeId);
        String path = exchange.getRequest().getPath().toString();
        String newPath = "";
        final String[] skip = {""};
        routeDefinition.getFilters().stream()
                .filter(
                        e -> e.getName().equals(GatewayConstant.ROUTE_DEFINITION_FILTER)).limit(1).forEach(ex -> ex.getArgs().entrySet().forEach(map -> skip[0] = map.getValue()));
        Long step = Long.parseLong(skip[0]);
        newPath = "/" + (String) Arrays.stream(StringUtils.tokenizeToStringArray(path, "/")).skip(step).collect(Collectors.joining("/"));
        newPath = newPath + (newPath.length() > 1 && path.endsWith("/") ? "/" : "");
        return newPath;
    }

    /**
     * @describe: 判断请求ip是否在白名单，改校验用于内网环境服务可信任调度
     * @param: [exchange]
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/8/20 10:20
     */
    private boolean isWhite(ServerWebExchange exchange) {
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        Route route = exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        String serverName = route.getUri().getHost();
        if (whiteSupport.equals(GatewayConstant.FRAME_GATEWAY_SUPPORT_FALSE)) {//不需要白名单支持
            return true;
        } else if (GatewayConstant.FRAME_GATEWAY_SUPPORT_TRUE.equals(gatewaySupport)
                && !whiteListService.isExist(serverName)) {//需要白名单支持，但是服务名称不在redis中，说明该服务不需要白名单校验
            return true;
        }
        ServerHttpRequest request = exchange.getRequest();
        String ip = request.getURI().getHost();
        if (ip.equals("localhost") || ip.equals(CommonConstant.LOCALHOST_IP)) {
            ip = IpUtil.getLocalhostIp();
        }

        //        String ip = request.getRemoteAddress().getAddress().getHostAddress();

        WhiteListVo whiteListVo = whiteListService.getWhiteList(serverName);
        boolean result = false;
        Set<String> white = whiteListVo.getNetworkSegment();
        for (
                String e : white) {
            if (IpUtil.isInRange(ip, e)) {
                result = true;
                break;
            }

        }
        return result;
    }


}
