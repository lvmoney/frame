package com.lvmoney.k8s.base.listener;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.listener
 * 版本信息: 版本1.0
 * 日期:2019/8/16
 * Copyright XXXXXX科技有限公司
 */

import com.lvmoney.common.constant.CommonConstant;
import com.lvmoney.common.ro.ServerBaseInfoRo;
import com.lvmoney.common.vo.ServerInfo;
import com.lvmoney.k8s.base.annotations.ReleaseServer;
import com.lvmoney.k8s.base.constant.BaseConstant;
import com.lvmoney.k8s.base.enums.OperatingEnvironmentType;
import com.lvmoney.k8s.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/16 15:38
 */
@Component
public class ServerInfoListener implements ApplicationListener<WebServerInitializedEvent> {

    private int serverPort;
    @Value("${spring.application.name}")
    private String serverName;
    @Value("${custom.tomcat.https.schema:http}")
    private String schema;
    @Autowired
    BaseService baseService;
    @Value("${operating.environment:local}")
    String operatingEnvironment;
    @Value("${operating.internal:internal}")
    String operatingInternal;

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    /**
     * 为当前服务构造基础数据，主要用在gateway中白名单，是否公布出去被访问等等
     *
     * @throws
     * @return: com.lvmoney.common.vo.ServerInfo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/17 16:59
     */
    public ServerInfo getServerInfo() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        ServerInfo serverInfo = new ServerInfo();
        serverInfo.setIp(address.getHostAddress());
        serverInfo.setPort(this.serverPort);
        if (operatingEnvironment.equals(OperatingEnvironmentType.local.getValue())) {
            serverInfo.setUri(schema + "://" + address.getHostAddress() + ":" + this.serverPort + "/");
        } else if (operatingEnvironment.equals(OperatingEnvironmentType.istio.getValue())) {
            serverInfo.setUri(schema + "://" + BaseConstant.WEBSITE_PREFIX + this.serverName + BaseConstant.WEBSITE_SUFFIX + "/");
        }
        serverInfo.setServerName(BaseConstant.WEBSITE_PREFIX + this.serverName + BaseConstant.WEBSITE_SUFFIX);
        serverInfo.setHttpScheme(this.schema);
        serverInfo.setReleaseServer(this.getReleaseServer());
        serverInfo.setInternalService(operatingInternal);
        ServerBaseInfoRo serverBaseInfoRo = new ServerBaseInfoRo();
        Map<String, ServerInfo> stringServerInfoMap = new HashMap(CommonConstant.MAP_DEFAULT_SIZE) {{
            put(serverInfo.getServerName(), serverInfo);
        }};
        serverBaseInfoRo.setData(stringServerInfoMap);
        baseService.saveServerInfo(serverBaseInfoRo);
        return serverInfo;
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.serverPort = event.getWebServer().getPort();
        this.getServerInfo();
    }

    /**
     * 通过注解获得该服务被允许其他服务调用的接口
     *
     * @throws
     * @return: java.util.Set<java.lang.String>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/17 16:58
     */
    private Set<String> getReleaseServer() {
        Set<String> result = new HashSet();
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            RequestMappingInfo info = m.getKey();
            HandlerMethod handlerMethod = m.getValue();
            PatternsRequestCondition p = info.getPatternsCondition();
            Method method = handlerMethod.getMethod();
            if (method.isAnnotationPresent(ReleaseServer.class)) {
                ReleaseServer shiroResouce = method.getAnnotation(ReleaseServer.class);
                if (shiroResouce.release() == true) {
                    for (String url : p.getPatterns()) {
                        result.add(url);
                    }
                }
            }
        }
        return result;
    }

}
