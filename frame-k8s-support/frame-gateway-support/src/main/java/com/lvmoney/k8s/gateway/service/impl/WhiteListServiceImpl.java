package com.lvmoney.k8s.gateway.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.k8s.gateway.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/8/20
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lvmoney.k8s.gateway.constant.GatewayConstant;
import com.lvmoney.k8s.gateway.ro.WhiteListRo;
import com.lvmoney.k8s.gateway.service.WhiteListService;
import com.lvmoney.k8s.gateway.vo.WhiteListVo;
import com.lvmoney.redis.service.BaseRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/20 9:52
 */
@Service
public class WhiteListServiceImpl implements WhiteListService {
    @Autowired
    BaseRedisService baseRedisService;

    @Override
    public void saveWhiteList2Redis(WhiteListRo whiteListRo) {
        baseRedisService.addMap(GatewayConstant.SERVER_WHILTE_LIST, whiteListRo.getData(), whiteListRo.getExpired());
    }

    @Override
    public WhiteListVo getWhiteList(String serverName) {
        Object obj = baseRedisService.getValueByMapKey(GatewayConstant.SERVER_WHILTE_LIST, serverName);
        WhiteListVo whiteListVo = JSON.parseObject(obj.toString(), new TypeReference<WhiteListVo>() {
        });
        return whiteListVo;
    }

    @Override
    public boolean isExist(String serverName) {
        return baseRedisService.isExistMapKey(GatewayConstant.SERVER_WHILTE_LIST, serverName);
    }
}
