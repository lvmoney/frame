package com.lvmoney.k8s.base.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/8/16
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.common.constant.CommonConstant;
import com.lvmoney.common.ro.ServerBaseInfoRo;
import com.lvmoney.k8s.base.constant.BaseConstant;
import com.lvmoney.k8s.base.service.BaseService;
import com.lvmoney.redis.service.BaseRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/16 15:52
 */
@Service
public class BaseServiceImpl implements BaseService {
    @Autowired
    BaseRedisService baseRedisService;

    @Override
    public void saveServerInfo(ServerBaseInfoRo serverBaseInfoRo) {
        baseRedisService.addMap(CommonConstant.REDIS_SERVER_CROUP_KEY, serverBaseInfoRo.getData(), serverBaseInfoRo.getExpired());
    }
}
