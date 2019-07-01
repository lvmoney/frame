/**
 * 描述:
 * 包名:com.lvmoney.pay.mq
 * 版本信息: 版本1.0
 * 日期:2018年10月11日  下午3:51:40
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.rabbitmq.provider;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lvmoney.common.utils.JsonUtil;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月11日 下午3:51:40
 */

@Component
public class MqSender {
    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 1、没有使用交换机
     * 2、没有使用ack确认机制
     *
     * @param queueName
     * @param t
     * @param <T>
     */
    public <T> void send(String queueName, T t) {
        rabbitTemplate.convertAndSend(queueName, JsonUtil.t2JsonString(t));
    }
}
