package com.lvmoney.k8s.logback.redis.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.k8s.logback.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/8/23
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.k8s.logback.common.service.LogbackService;
import com.lvmoney.k8s.logback.common.vo.LogVo;
import com.lvmoney.k8s.logback.redis.ro.LogRo;
import com.lvmoney.redis.service.BaseRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/23 8:53
 */
@Service
public class RedisLogbackServiceImpl implements LogbackService {

    @Autowired
    BaseRedisService baseRedisService;

    @Override
    public void saveLog(LogVo logVo) {
        LogRo logRo = new LogRo();
        logRo.setData(new ArrayList() {{
            add(logVo);
        }});
        logRo.setKey(logVo.getLogger());
        baseRedisService.addList(logRo.getKey(), logRo.getData(), logRo.getExpired());
    }

}
