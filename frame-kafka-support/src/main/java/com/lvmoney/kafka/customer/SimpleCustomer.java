package com.lvmoney.kafka.customer;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/1/21
 * Copyright xxxx科技有限公司
 */


import com.alibaba.fastjson.JSONObject;
import com.lvmoney.common.exceptions.BusinessException;
import com.lvmoney.common.exceptions.CommonException;
import com.lvmoney.kafka.constant.KafkaConstant;
import com.lvmoney.kafka.factory.HandMqServiceContext;
import com.lvmoney.kafka.utils.ContextBeanUtil;
import com.lvmoney.kafka.vo.MessageVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Component
public class SimpleCustomer {
    @Autowired
    HandMqServiceContext handMqServiceContext;

    @KafkaListener(topics = {KafkaConstant.SIMPLE_QUEUE_NAME}, groupId = KafkaConstant.SIMPLE_QUEUE_GROUP_ID)
    public void receive(ConsumerRecord consumerRecord) throws InterruptedException {
        System.out.println(consumerRecord.offset());
        System.out.println(consumerRecord.value().toString());
        MessageVo mqDataVo = JSONObject.parseObject(consumerRecord.value().toString(), MessageVo.class);
        String mqType = mqDataVo.getMsgType();
        String beanName = ContextBeanUtil.getBeanName(handMqServiceContext, mqType);
        if (StringUtils.isBlank(beanName)) {
            throw new BusinessException(CommonException.Proxy.KAFKA_DYNAMIC_NOT_EXSIT);
        }
        handMqServiceContext.getData(beanName, mqDataVo);
    }
}
