package com.lvmoney.kafka.provider;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/1/21
 * Copyright xxxx科技有限公司
 */


import com.lvmoney.common.util.JsonUtil;
import com.lvmoney.common.util.ReflectUtil;
import com.lvmoney.kafka.constant.KafkaConstant;
import com.lvmoney.kafka.listener.ProviderListener;
import com.lvmoney.kafka.vo.MessageVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Component
public class SimpleSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectUtil.class);

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Autowired
    private ProviderListener producerListener;

    public void send(MessageVo messageVo) {
        kafkaTemplate.setProducerListener(producerListener);
        ListenableFuture send = kafkaTemplate.send(KafkaConstant.SIMPLE_QUEUE_NAME, JsonUtil.t2JsonString(messageVo));
        try {
            //发送消息的时候需要休眠一下，否则发送时间较长的时候会导致进程提前关闭导致无法调用回调时间。主要是因为KafkaTemplate发送消息是采取异步方式发送的
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            LOGGER.error("kafka发送消息报错:{}", e.getMessage());
        }
    }

}
