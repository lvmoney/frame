package com.lvmoney.k8s.base.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/8/19
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.common.utils.JsonUtil;
import com.lvmoney.k8s.base.constant.BaseConstant;
import com.lvmoney.k8s.base.enums.*;
import com.lvmoney.k8s.base.properties.RpcServerConfigProp;
import com.lvmoney.k8s.base.service.YamlService;
import com.lvmoney.k8s.base.utils.YamlUtil;
import com.lvmoney.k8s.base.vo.jyaml.*;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/19 10:53
 */
@org.springframework.stereotype.Service
public class YamlServiceImpl implements YamlService {
    @Value("${istio.yaml.cover:false}")
    boolean yamlCover;
    @Value("${spring.application.name:lvmoney}")
    String applicationName;
    @Value("${server.port:8080}")
    int serverPort;
    @Value("${istio.yaml.replicas:1}")
    int replicas;
    @Value("${istio.yaml.version:v1}")
    String version;
    @Value("${istio.docker.image.tag}")
    String dockerImage;

    @Value("${istio.docker.image.pull:IfNotPresent}")
    String pullPolicy;

    @Value("${istio.yaml.destination.support:false}")
    boolean destinationSupport;

    @Value("${istio.yaml.destination.v1:50}")
    Integer ratioV1;

    @Value("${istio.yaml.destination.v2:50}")
    Integer ratioV2;

    private String allPrefix = "all";
    /**
     * istio master节点ip，所用的服务都以这里为入口
     */
    @Value("${istio.master.ip}")
    private String masterIp;


    @Value("${istio.yaml.policy.maxConnections:2048}")
    private Integer maxConnections;
    @Value("${istio.yaml.policy.connectTimeout:3s}")
    private String connectTimeout;
    @Value("${istio.yaml.policy.http1MaxPendingRequests:1024}")
    private Integer http1MaxPendingRequests;
    @Value("${istio.yaml.policy.maxRequestsPerConnection:200}")
    private Integer maxRequestsPerConnection;
    @Value("${istio.yaml.policy.consecutiveErrors:3}")
    private Integer consecutiveErrors;
    @Value("${istio.yaml.policy.interval:3s}")
    private String interval;
    @Value("${istio.yaml.policy.baseEjectionTime:3m}")
    private String baseEjectionTime;
    @Value("${istio.yaml.policy.maxEjectionPercent:100}")
    private Integer maxEjectionPercent;
    @Value("${istio.yaml.policy.maxRetries:3}")
    private Integer maxRetries;
    @Value("${istio.yaml.policy.http2MaxRequests:2048}")
    private Integer http2MaxRequests;


    @Autowired
    RpcServerConfigProp rpcServerConfigProp;

    @Override
    public Service buildService() {
        Service service = new Service();
        service.setApiVersion(ApiVersion.v1.getValue());
        service.setKind(YamlKind.Service.getValue());
        Metadata sMetadata = new Metadata();
        sMetadata.setName(applicationName);
        Labels sLabels = new Labels();
        sLabels.setApp(applicationName);
        sLabels.setService(applicationName);
        sMetadata.setLabels(sLabels);
        ServiceSpec serviceSpec = new ServiceSpec();
        Ports sPorts = new Ports();
        sPorts.setPort(serverPort);
        sPorts.setName(applicationName);
        serviceSpec.setPorts(new ArrayList() {{
            add(sPorts);
        }});
        Selector sSelector = new Selector();
        sSelector.setApp(applicationName);
        serviceSpec.setSelector(sSelector);
        service.setMetadata(sMetadata);
        service.setSpec(serviceSpec);
        return service;
    }

    @Override
    public Deployment buildDeployment() {
        Deployment deployment = new Deployment();
        deployment.setApiVersion(ApiVersion.v1beta1.getValue());
        deployment.setKind(YamlKind.Deployment.getValue());
        Metadata dMetadata = new Metadata();
        dMetadata.setName(applicationName + "-" + version);
        Labels meLabels = new Labels();
        meLabels.setVersion(version);
        meLabels.setApp(applicationName);
        dMetadata.setLabels(meLabels);
        DeploymentSpec deploymentSpec = new DeploymentSpec();
        deploymentSpec.setReplicas(replicas);

        Template template = new Template();
        Metadata tMetadata = new Metadata();
        TemplateSpec templateSpec = new TemplateSpec();
        Labels tLabels = new Labels();
        tLabels.setApp(applicationName);

        tLabels.setVersion(version);
        tMetadata.setLabels(tLabels);

        Containers containers = new Containers();

        containers.setName(applicationName);
        containers.setImage(dockerImage);
        if (pullPolicy.equals(DockerPull.IfNotPresent.getValue())) {
            containers.setImagePullPolicy(DockerPull.IfNotPresent.getValue());
        } else if (pullPolicy.equals(DockerPull.Always.getValue())) {
            containers.setImagePullPolicy(DockerPull.Always.getValue());
        } else if (pullPolicy.equals(DockerPull.Never.getValue())) {
            containers.setImagePullPolicy(DockerPull.Never.getValue());
        } else {
            containers.setImagePullPolicy(DockerPull.IfNotPresent.getValue());
        }

        Ports tsPorts = new Ports();
        tsPorts.setContainerPort(serverPort);
        containers.setPorts(new ArrayList() {{
            add(tsPorts);
        }});


        List<HostAliases> hostAliasesList = new ArrayList();
        if (rpcServerConfigProp != null && rpcServerConfigProp.getServer() != null) {
            rpcServerConfigProp.getServer().forEach((k, v) -> {
                HostAliases hostAliases = new HostAliases();
                try {///etc/hosts 里面不能用http什么的，所以这里需要获得host
                    URI uri = new URI(v.toString());
                    hostAliases.setHostnames(new ArrayList() {
                        {
                            add(uri.getHost());
                        }

                    });
                } catch (URISyntaxException e) {
                    hostAliases.setHostnames(new ArrayList() {
                        {
                            add(v);
                        }

                    });
                }

                hostAliases.setIp(masterIp);
                hostAliasesList.add(hostAliases);
            });

        }

        templateSpec.setHostAliases(hostAliasesList);

        templateSpec.setContainers(new ArrayList() {{
            add(containers);
        }});
        template.setMetadata(tMetadata);
        template.setSpec(templateSpec);
        deploymentSpec.setTemplate(template);

        deployment.setSpec(deploymentSpec);
        deployment.setMetadata(dMetadata);
        return deployment;
    }

    @Override
    public Gateway buildGateway() {
        Gateway gateway = new Gateway();
        gateway.setApiVersion(ApiVersion.v1alpha3.getValue());
        gateway.setKind(YamlKind.Gateway.getValue());
        Metadata gMetadata = new Metadata();
        gMetadata.setName(applicationName);

        GatewatSpec gatewatSpec = new GatewatSpec();
        Selector selector = new Selector();
        selector.setIstio(Istio.ingressgateway.getValue());
        gatewatSpec.setSelector(selector);


        Ports servicesPort = new Ports();
        servicesPort.setNumber(BaseConstant.INGRESS_DEFAULT_PORT);
        servicesPort.setName(HttpProtocol.HTTP.getValue().toLowerCase());
        servicesPort.setProtocol(HttpProtocol.HTTP.getValue());
        Servers servers = new Servers();
        servers.setPort(servicesPort);
        gatewatSpec.setServers(new ArrayList() {{
            add(servers);
        }});
        servers.setHosts(new ArrayList() {{
            add("*." + applicationName + BaseConstant.WEBSITE_SUFFIX);
        }});
        gateway.setMetadata(gMetadata);
        gateway.setSpec(gatewatSpec);
        return gateway;
    }

    @Override
    public VirtualService buildVirtualService() {
        VirtualService virtualService = new VirtualService();
        virtualService.setApiVersion(ApiVersion.v1alpha3.getValue());
        virtualService.setKind(YamlKind.VirtualService.getValue());
        Metadata vsMetadata = new Metadata();
        vsMetadata.setName(applicationName);
        virtualService.setMetadata(vsMetadata);
        VirtualServiceSpec virtualServiceSpec = new VirtualServiceSpec();
        virtualServiceSpec.setHosts(new ArrayList() {{
            add(BaseConstant.WEBSITE_PREFIX + applicationName + BaseConstant.WEBSITE_SUFFIX);
        }});
        virtualServiceSpec.setGateways(new ArrayList() {{
            add(applicationName);
        }});
        Http http = new Http();
//        Match match = new Match();
//        Uri uri = new Uri();
//        uri.setExact(BaseConstant.VIRTUAL_SERVICE_EXAC);
//        match.setUri(uri);
//        http.setPrefix(new ArrayList() {{
//            add(match);
//        }});
        http.setTimeout(BaseConstant.ISTIO_SERVICE_TIMEOUT);

        Retries retries = new Retries();
        retries.setAttempts(maxRetries);
        retries.setPerTryTimeout(BaseConstant.ISTIO_SERVICE_PERTRYTIMEOUT);
        http.setRetries(retries);
        if (destinationSupport) {//金丝雀测试，灰度发布
            Route routeV1 = new Route();
            Destination destinationV1 = new Destination();
            destinationV1.setHost(applicationName);
            Ports desPortsV1 = new Ports();
            desPortsV1.setNumber(serverPort);
            destinationV1.setPort(desPortsV1);
            destinationV1.setSubset(BaseConstant.VERSION_V1);
            routeV1.setDestination(destinationV1);
            routeV1.setWeight(ratioV1);

            Route routeV2 = new Route();
            Destination destinationV2 = new Destination();
            destinationV2.setHost(applicationName);
            Ports desPortsV2 = new Ports();
            desPortsV2.setNumber(serverPort);
            destinationV2.setPort(desPortsV2);
            destinationV2.setSubset(BaseConstant.VERSION_V2);
            routeV2.setDestination(destinationV2);
            routeV2.setWeight(ratioV2);

            http.setRoute(new ArrayList() {{
                add(routeV1);
                add(routeV2);
            }});

        } else {
            Route route = new Route();
            Destination destination = new Destination();
            destination.setHost(applicationName);
            Ports desPorts = new Ports();
            desPorts.setNumber(serverPort);
            destination.setPort(desPorts);
            destination.setSubset(BaseConstant.VERSION_V1);
            route.setDestination(destination);
            http.setRoute(new ArrayList() {{
                add(route);
            }});
        }
        virtualServiceSpec.setHttp(new ArrayList() {{
            add(http);
        }});
        virtualService.setSpec(virtualServiceSpec);
        return virtualService;
    }

    @Override
    public DestinationRule buildDestinationRule() {
        DestinationRule destinationRule = new DestinationRule();
        destinationRule.setApiVersion(ApiVersion.v1alpha3.getValue());
        destinationRule.setKind(YamlKind.DestinationRule.getValue());
        Metadata metadata = new Metadata();
        metadata.setName(applicationName);
        destinationRule.setMetadata(metadata);
        DestinationRuleSpec destinationRuleSpec = new DestinationRuleSpec();
        destinationRuleSpec.setHost(applicationName);
        Tcp tcp = new Tcp();
        tcp.setMaxConnections(maxConnections);
        tcp.setConnectTimeout(connectTimeout);
        Http http = new Http();
        http.setHttp1MaxPendingRequests(http1MaxPendingRequests);
        http.setMaxRequestsPerConnection(maxRequestsPerConnection);
        http.setHttp2MaxRequests(http2MaxRequests);
        http.setMaxRetries(maxRetries);
        ConnectionPool connectionPool = new ConnectionPool();
        connectionPool.setHttp(http);
        connectionPool.setTcp(tcp);
        OutlierDetection outlierDetection = new OutlierDetection();
        outlierDetection.setBaseEjectionTime(baseEjectionTime);
        outlierDetection.setConsecutiveErrors(consecutiveErrors);
        outlierDetection.setInterval(interval);
        outlierDetection.setMaxEjectionPercent(maxEjectionPercent);
        TrafficPolicy trafficPolicy = new TrafficPolicy();
        trafficPolicy.setConnectionPool(connectionPool);
        trafficPolicy.setOutlierDetection(outlierDetection);
        destinationRuleSpec.setTrafficPolicy(trafficPolicy);
        if (destinationSupport) {//生成两个版本的subset，目前只需要支持两种就行
            List subsetslist = new ArrayList();
            Subsets subsetsV1 = new Subsets();
            subsetsV1.setName(BaseConstant.VERSION_V1);
            Labels labelsV1 = new Labels();
            labelsV1.setVersion(BaseConstant.VERSION_V1);
            subsetsV1.setLabels(labelsV1);
            subsetslist.add(subsetsV1);
            Subsets subsetsV2 = new Subsets();
            subsetsV2.setName(BaseConstant.VERSION_V2);
            Labels labelsV2 = new Labels();
            labelsV2.setVersion(BaseConstant.VERSION_V2);
            subsetsV2.setLabels(labelsV2);
            subsetslist.add(subsetsV2);
            destinationRuleSpec.setSubsets(subsetslist);
        } else {
            List subsetslist = new ArrayList();
            Subsets subsetsV1 = new Subsets();
            subsetsV1.setName(BaseConstant.VERSION_V1);
            Labels labelsV1 = new Labels();
            labelsV1.setVersion(BaseConstant.VERSION_V1);
            subsetsV1.setLabels(labelsV1);
            subsetslist.add(subsetsV1);
            destinationRuleSpec.setSubsets(subsetslist);
        }
        destinationRule.setSpec(destinationRuleSpec);
        return destinationRule;
    }

    @Override
    public void createIDeploy() {
        if (!version.equals(BaseConstant.VERSION_V1) && !version.equals(BaseConstant.VERSION_V2)) {
            version = BaseConstant.VERSION_V1;
        }
        List<Object> iDeploy = new ArrayList<>();
        if (version.equals(BaseConstant.VERSION_V2)) {
            iDeploy.add(this.buildDeployment());
        } else {
            iDeploy.add(this.buildService());
            iDeploy.add(this.buildDeployment());
        }
        YamlBuild yamlBuild = new YamlBuild();
        yamlBuild.setCover(yamlCover);
        yamlBuild.setData(iDeploy);
        yamlBuild.setPath(BaseConstant.YAML_FILE_PATH);
        yamlBuild.setName(version + "-" + YamlType.IDeploy.getValue() + "-" + applicationName + BaseConstant.YAML_SUFFIX);
        YamlUtil.buildYaml(yamlBuild);
    }

    @Override
    public void createIGateway() {
        if (!version.equals(BaseConstant.VERSION_V1) && !version.equals(BaseConstant.VERSION_V2)) {
            version = BaseConstant.VERSION_V1;
        }
        List<Object> iDeploy = new ArrayList<>();
        iDeploy.add(this.buildGateway());
        iDeploy.add(this.buildVirtualService());
        iDeploy.add(this.buildDestinationRule());
        YamlBuild yamlBuild = new YamlBuild();
        yamlBuild.setCover(yamlCover);
        yamlBuild.setData(iDeploy);
        yamlBuild.setPath(BaseConstant.YAML_FILE_PATH);
        yamlBuild.setName(allPrefix + "-" + YamlType.IGateway.getValue() + "-" + applicationName + BaseConstant.YAML_SUFFIX);
        YamlUtil.buildYaml(yamlBuild);
    }


    /**
     * @describe: 灰度发布
     * @param: [http]
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/8/29 11:34
     */
    private void destinationRule(Http http) {//灰度发布
        if (destinationSupport) {//金丝雀测试，灰度发布
            Route routeV1 = new Route();
            Destination destinationV1 = new Destination();
            destinationV1.setHost(applicationName);
            Ports desPortsV1 = new Ports();
            desPortsV1.setNumber(serverPort);
            destinationV1.setPort(desPortsV1);
            destinationV1.setSubset(BaseConstant.VERSION_V1);
            routeV1.setDestination(destinationV1);
            routeV1.setWeight(ratioV1);

            Route routeV2 = new Route();
            Destination destinationV2 = new Destination();
            destinationV2.setHost(applicationName);
            Ports desPortsV2 = new Ports();
            desPortsV2.setNumber(serverPort);
            destinationV2.setPort(desPortsV2);
            destinationV2.setSubset(BaseConstant.VERSION_V2);
            routeV2.setDestination(destinationV2);
            routeV2.setWeight(ratioV2);

            http.setRoute(new ArrayList() {{
                add(routeV1);
                add(routeV2);
            }});

        } else {
            Route route = new Route();
            Destination destination = new Destination();
            destination.setHost(applicationName);
            Ports desPorts = new Ports();
            desPorts.setNumber(serverPort);
            destination.setPort(desPorts);
            route.setDestination(destination);
            http.setRoute(new ArrayList() {{
                add(route);
            }});
        }
    }

    /**
     * @describe: 流量复制
     * @param: []
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/8/29 11:33
     */
    private void mirror(Http http) {

    }

    private void fault(Http http) {

    }
}
