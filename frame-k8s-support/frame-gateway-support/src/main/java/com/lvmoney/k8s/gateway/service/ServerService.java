package com.lvmoney.k8s.gateway.service;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.service
 * 版本信息: 版本1.0
 * 日期:2019/8/20
 * Copyright XXXXXX科技有限公司
 */


import java.net.URI;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/20 14:33
 */
public interface ServerService {
    String getRealRequstUri(URI uri);
}
