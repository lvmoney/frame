package com.lvmoney.oauth2.center.server.service;/**
 * 描述:
 * 包名:com.lvmoney.oauth2.center.server.service
 * 版本信息: 版本1.0
 * 日期:2019/8/6
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/6 14:14
 */
public interface Db2RedisService {

    /**
     * @describe: 从数据库获得用户的userdetails信息并加载到redis
     * @param: [username]
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/8/6 14:20
     */
    void loadUserByUsername(String username);

    /**
     * @describe: 从数据库中获得clientdetails信息并加载到redis
     * @param: [clientId]
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/8/6 17:33
     */
    void loadClientDetailsByClientId(String clientId);
}
