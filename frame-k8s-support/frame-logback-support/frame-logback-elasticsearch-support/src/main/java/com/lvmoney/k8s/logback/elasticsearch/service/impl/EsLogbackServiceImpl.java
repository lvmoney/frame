package com.lvmoney.k8s.logback.elasticsearch.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.k8s.logback.elasticsearch.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/8/23
 * Copyright XXXXXX科技有限公司
 */


import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.lvmoney.common.utils.SnowflakeIdFactoryUtil;
import com.lvmoney.elasticsearch.service.ElasticsearchService;
import com.lvmoney.elasticsearch.vo.ElasticsearchSaveVo;
import com.lvmoney.k8s.logback.common.service.LogbackService;
import com.lvmoney.k8s.logback.common.vo.LogVo;
import com.lvmoney.k8s.logback.elasticsearch.eo.LogEo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/23 17:51
 */
@Service
public class EsLogbackServiceImpl implements LogbackService {
    @Autowired
    private ElasticsearchService searchService;
    private static Mapper MAPPER = DozerBeanMapperBuilder.buildDefault();

    @Override
    public void saveLog(LogVo logVo) {
        ElasticsearchSaveVo elasticsearchSaveVo = new ElasticsearchSaveVo();
        LogEo logEo = (LogEo) MAPPER.map(logVo, LogEo.class);
        SnowflakeIdFactoryUtil idWorker = new SnowflakeIdFactoryUtil(1, 2);
        Long num = idWorker.nextId();
        logEo.setId(num);
        searchService.save(elasticsearchSaveVo);
    }
}
