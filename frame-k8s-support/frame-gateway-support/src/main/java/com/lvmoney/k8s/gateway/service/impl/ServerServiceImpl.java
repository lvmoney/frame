package com.lvmoney.k8s.gateway.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/8/20
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lvmoney.common.constant.CommonConstant;
import com.lvmoney.common.vo.ServerInfo;
import com.lvmoney.k8s.gateway.service.ServerService;
import com.lvmoney.redis.service.BaseRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/20 14:34
 */
@Service
public class ServerServiceImpl implements ServerService {
    @Autowired
    BaseRedisService baseRedisService;

    @Override
    public String getRealRequstUri(URI uri) {
        Object obj = baseRedisService.getValueByMapKey(CommonConstant.REDIS_SERVER_CROUP_KEY, uri.getHost().toString());
        try {
            ServerInfo serverInfo = JSON.parseObject(obj.toString(), new TypeReference<ServerInfo>() {
            });
            return serverInfo.getUri();
        } catch (Exception e) {
            return uri.toString();
        }


    }
}
