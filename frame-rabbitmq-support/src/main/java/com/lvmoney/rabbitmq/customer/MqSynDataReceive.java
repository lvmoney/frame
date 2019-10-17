/**
 * 描述:
 * 包名:com.lvmoney.pay.mq
 * 版本信息: 版本1.0
 * 日期:2018年10月11日  下午4:57:06
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.rabbitmq.customer;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.lvmoney.common.exception.BusinessException;
import com.lvmoney.common.exception.CommonException;
import com.lvmoney.rabbitmq.annotation.DynamicService;
import com.lvmoney.rabbitmq.constant.RabbitmqConstant;
import com.lvmoney.rabbitmq.factory.HandMqServiceContext;
import com.lvmoney.rabbitmq.service.HandMqDataService;
import com.lvmoney.rabbitmq.vo.MqDataVo;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月11日 下午4:57:06
 */
@Component
public class MqSynDataReceive {
    @Autowired
    HandMqServiceContext handMqServiceContext;

    /**
     * @param str 2018年10月12日上午10:35:00
     * @describe:监听器监听指定的Queue
     * 处理订单支付结果消息
     * 1、注意这里需要通过mqType 找到 HandMqDataService 实现接口对应的beanname，通过策略模式调用不同的接口处理消息
     * 2、通过@DynamicService 注解能够找到对于的beanname
     * 3、使用@DynamicService(name="SYN_DATA")注解name的值应该和mqType一样
     * @author: lvmoney /xxxx科技有限公司
     */
    @RabbitListener(queues = RabbitmqConstant.MQ_DATA_SYN_NAME)
    public void processOrder(String str) {
        MqDataVo mqDataVo = JSONObject.parseObject(str, MqDataVo.class);
        String mqType = mqDataVo.getMqType();
        String beanName = getBeanName(mqType);
        if (StringUtils.isBlank(beanName)) {
            throw new BusinessException(CommonException.Proxy.RABBITMQ_DYNAMIC_NOT_EXSIT);
        }
        handMqServiceContext.getData(beanName, mqDataVo);
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
