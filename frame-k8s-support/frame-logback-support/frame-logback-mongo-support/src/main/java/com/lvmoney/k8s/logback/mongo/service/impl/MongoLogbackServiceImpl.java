package com.lvmoney.k8s.logback.mongo.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.k8s.logback.mongo.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/8/23
 * Copyright XXXXXX科技有限公司
 */


import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.lvmoney.k8s.logback.common.service.LogbackService;
import com.lvmoney.k8s.logback.common.vo.LogVo;
import com.lvmoney.k8s.logback.mongo.mo.LogMo;
import com.lvmoney.mongo.service.BaseMongoService;
import com.lvmoney.mongo.vo.BaseMongoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/23 16:10
 */
@Service
public class MongoLogbackServiceImpl implements LogbackService {
    @Autowired
    BaseMongoService baseMongoService;
    private static Mapper MAPPER = DozerBeanMapperBuilder.buildDefault();

    @Override
    public void saveLog(LogVo logVo) {
        BaseMongoVo baseMongoVo = new BaseMongoVo();
        LogMo logMo = (LogMo) MAPPER.map(logVo, LogMo.class);
        baseMongoVo.setData(logMo);
        baseMongoService.save(baseMongoVo);
    }
}
