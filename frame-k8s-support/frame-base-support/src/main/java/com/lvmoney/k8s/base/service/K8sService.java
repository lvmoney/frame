package com.lvmoney.k8s.base.service;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.service
 * 版本信息: 版本1.0
 * 日期:2019/8/17
 * Copyright XXXXXX科技有限公司
 */


import io.fabric8.kubernetes.api.model.NamespaceList;
import io.fabric8.kubernetes.api.model.NodeList;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/17 10:01
 */
public interface K8sService {

    NamespaceList listNamespace();

    NodeList listNode();
}
