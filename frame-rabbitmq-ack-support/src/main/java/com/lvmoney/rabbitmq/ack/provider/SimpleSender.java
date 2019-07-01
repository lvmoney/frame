package com.lvmoney.rabbitmq.ack.provider;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/1/21
 * Copyright xxxx科技有限公司
 */


import com.lvmoney.common.utils.JsonUtil;
import com.lvmoney.rabbitmq.ack.constant.RabbitmqConstant;
import com.lvmoney.rabbitmq.ack.ro.AckErrorRecordRo;
import com.lvmoney.rabbitmq.ack.service.RabbitmqRedisService;
import com.lvmoney.rabbitmq.ack.vo.MessageVo;
import com.lvmoney.rabbitmq.ack.vo.MsgErrorVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SerializerMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Component
public class SimpleSender {
//    @Autowired
//    private RabbitTemplate rabbitTemplate;

    @Autowired
    RabbitmqRedisService rabbitmqRedisService;
    @Value("${rabbitmq.error.record.expire}")
    String expire;
    @Autowired
    ConnectionFactory connectionFactory;

    @Bean
    @Scope("prototype")
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMandatory(true);
        template.setMessageConverter(new SerializerMessageConverter());
        return template;
    }

    public void send(MessageVo msg) {
        RabbitTemplate rabbitTemplate = rabbitTemplate(connectionFactory);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {//exchange 错误被调用ack=false
            if (!ack) {
                AckErrorRecordRo ackErrorRecordRo = new AckErrorRecordRo();
                if (StringUtils.isBlank(expire)) {
                    expire = "1800";
                }
                ackErrorRecordRo.setExpire(Long.valueOf(expire));
                MsgErrorVo msgErrorVo = new MsgErrorVo();
                msgErrorVo.setMessageVo(msg);
                msgErrorVo.setMqName(RabbitmqConstant.SIMPLE_QUEUE_NAME);
                List<MsgErrorVo> data = new ArrayList<>();
                data.add(msgErrorVo);
                ackErrorRecordRo.setMsgErrorVoList(data);
                rabbitmqRedisService.ackRecord2Redis(ackErrorRecordRo);
            }
        });
        rabbitTemplate.setReturnCallback((Message message, int replyCode, String replyText,
                                          String exchange, String routingKey) -> { //exchange 正确,queue 错误 ,confirm被回调, ack=true; return被回调 replyText:NO_ROUTE
            AckErrorRecordRo ackErrorRecordRo = new AckErrorRecordRo();
            if (StringUtils.isBlank(expire)) {
                expire = "1800";
            }
            ackErrorRecordRo.setExpire(Long.valueOf(expire));
            MsgErrorVo msgErrorVo = new MsgErrorVo();
            msgErrorVo.setMessageVo(msg);
            msgErrorVo.setExName(exchange);
            msgErrorVo.setRoutingKey(routingKey);
            List<MsgErrorVo> data = new ArrayList<>();
            data.add(msgErrorVo);
            ackErrorRecordRo.setMsgErrorVoList(data);
            rabbitmqRedisService.ackRecord2Redis(ackErrorRecordRo);
        });
        rabbitTemplate.convertAndSend(RabbitmqConstant.SIMPLE_QUEUE_NAME, JsonUtil.t2JsonString(msg));
    }


}
