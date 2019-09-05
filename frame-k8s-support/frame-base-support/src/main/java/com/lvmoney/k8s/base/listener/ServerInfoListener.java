package com.lvmoney.k8s.base.listener;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.listener
 * 版本信息: 版本1.0
 * 日期:2019/8/16
 * Copyright XXXXXX科技有限公司
 */

import com.lvmoney.common.ro.ServerBaseInfoRo;
import com.lvmoney.common.vo.ServerInfo;
import com.lvmoney.k8s.base.constant.BaseConstant;
import com.lvmoney.k8s.base.enums.OperatingEnvironmentType;
import com.lvmoney.k8s.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

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
        ServerBaseInfoRo serverBaseInfoRo = new ServerBaseInfoRo();
        Map<String, ServerInfo> stringServerInfoMap = new HashMap() {{
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

}
