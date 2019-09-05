package com.lvmoney.k8s.base.service;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.service
 * 版本信息: 版本1.0
 * 日期:2019/8/19
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.k8s.base.vo.jyaml.*;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/19 10:53
 */
public interface YamlService {

    Service buildService();

    Deployment buildDeployment();

    Gateway buildGateway();

    VirtualService buildVirtualService();

    DestinationRule buildDestinationRule();

    /**
     * @describe: 多个web服务接入共享ingressgateway
     * @param: []
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/8/19 10:58
     */
    void createIDeploy();

    /**
     * @describe: 多个web服务接入共享ingressgateway
     * @param: []
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/8/19 10:59
     */
    void createIGateway();

}
