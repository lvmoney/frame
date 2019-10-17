package com.lvmoney.kafka.utils;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/1/21
 * Copyright xxxx科技有限公司
 */


import com.lvmoney.kafka.annotation.DynamicService;
import com.lvmoney.kafka.factory.HandMqServiceContext;
import com.lvmoney.kafka.service.HandMqDataService;

import java.util.Map;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public class ContextBeanUtil {
    public static String getBeanName(HandMqServiceContext handMqServiceContext, String mqType) {
        Map<String, HandMqDataService> map = handMqServiceContext.getStrategyMap();
        for (Map.Entry<String, HandMqDataService> entry : map.entrySet()) {
            Class<? extends HandMqDataService> clazz = entry.getValue().getClass();
            if (clazz.isAnnotationPresent(DynamicService.class)) {
                DynamicService dynamicService = clazz.getAnnotation(DynamicService.class);
                String name = dynamicService.name();
                if (name.equals(mqType)) {
                    return entry.getKey();
                }
            }
        }
        return "";
    }
}
