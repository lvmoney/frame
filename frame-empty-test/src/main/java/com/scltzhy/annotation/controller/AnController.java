/**
 * 描述:
 * 包名:com.lvmoney.test.controller
 * 版本信息: 版本1.0
 * 日期:2019年1月4日  下午2:57:04
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.annotation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lvmoney.rabbitmq.annotation.DynamicService;
import com.lvmoney.rabbitmq.factory.HandMqServiceContext;
import com.lvmoney.rabbitmq.service.HandMqDataService;


/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月4日 下午2:57:04
 */
@RestController
@RequestMapping("annotation")
public class AnController {
    @Autowired
    HandMqServiceContext handMqServiceContext;

    @GetMapping("/test")
    public String getMessage() {
        System.out.println(getBeanName("SYN_FROOM"));
        return "你已通过验证";
    }

    private String getBeanName(String mqType) {
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
