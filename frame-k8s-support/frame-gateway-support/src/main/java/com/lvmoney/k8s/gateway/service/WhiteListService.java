package com.lvmoney.k8s.gateway.service;/**
 * 描述:
 * 包名:com.lvmoney.k8s.gateway.service
 * 版本信息: 版本1.0
 * 日期:2019/8/20
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.k8s.gateway.ro.WhiteListRo;
import com.lvmoney.k8s.gateway.vo.WhiteListVo;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/20 9:42
 */
public interface WhiteListService {
    /**
     * @describe: 白名单数据存入redis
     * @param: [whiteListRo]
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/8/20 9:50
     */
    void saveWhiteList2Redis(WhiteListRo whiteListRo);

    /**
     * @describe: 通过服务名获得对应的白名单
     * @param: [serverName]
     * @return: com.lvmoney.k8s.gateway.vo.WhiteListVo
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/8/20 9:52
     */
    WhiteListVo getWhiteList(String serverName);

    /**
     * @describe: 判断serverName 在 redis是否存在
     * @param: [serverName]
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/8/20 11:27
     */
    boolean isExist(String serverName);

}
