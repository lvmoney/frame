package com.lvmoney.k8s.base.service;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.service
 * 版本信息: 版本1.0
 * 日期:2019/8/16
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.common.ro.ServerBaseInfoRo;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/16 15:52
 */
public interface BaseService {
    /**
     * @describe: 存储服务基本信息到redis中
     * @param: [serverInfo]
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/8/16 15:54
     */
    void saveServerInfo(ServerBaseInfoRo serverBaseInfoRo);
}
