package com.lvmoney.k8s.config.client.listener;/**
 * 描述:
 * 包名:com.lvmoney.k8s.config.client.listener
 * 版本信息: 版本1.0
 * 日期:2019/9/12
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.cloud.bus.event.RefreshRemoteApplicationEvent;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/9/12 18:20
 * //@Component
 */

public class RefreshListener implements ApplicationListener<RefreshRemoteApplicationEvent> {

    private ContextRefresher contextRefresher;

    @Override
    public void onApplicationEvent(RefreshRemoteApplicationEvent event) {
        System.out.println();
        System.out.println("TestRefreshListener。。。。。。。。。。。。");
        System.out.println();
    }
}
