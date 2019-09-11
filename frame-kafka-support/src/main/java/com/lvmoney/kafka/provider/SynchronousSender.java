package com.lvmoney.kafka.provider;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/1/22
 * Copyright xxxx科技有限公司
 *
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */


/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0
 * 2018年10月30日 下午3:29:38   
 */

import com.lvmoney.common.exceptions.BusinessException;
import com.lvmoney.common.exceptions.CommonException;
import com.lvmoney.common.utils.JsonUtil;
import com.lvmoney.kafka.constant.KafkaConstant;
import com.lvmoney.kafka.listener.ProviderListener;
import com.lvmoney.kafka.vo.MessageVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Component
public class SynchronousSender {
    private static final Logger logger = LoggerFactory.getLogger(SynchronousSender.class);
    @Autowired
    KafkaTemplate kafkaTemplate;
    @Autowired
    private ProviderListener producerListener;
    @Value("${kafka.send.get.milliseconds}")
    String millseconds;

    public void send(MessageVo messageVo) {
        try {
            kafkaTemplate.setProducerListener(producerListener);
            if (StringUtils.isBlank(millseconds)) {
                millseconds = "1000";
            }
            kafkaTemplate.send(KafkaConstant.SYN_QUEUE_NAME, JsonUtil.t2JsonString(messageVo)).get(Long.valueOf(millseconds), TimeUnit.MILLISECONDS);
            //发送消息的时候需要休眠一下，否则发送时间较长的时候会导致进程提前关闭导致无法调用回调时间。主要是因为KafkaTemplate发送消息是采取异步方式发送的
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            logger.error("kafka发送同步消息报错:{}", e.getMessage());
            throw new BusinessException(CommonException.Proxy.KAFKA_SEND_SYN_INTERRUPTED_ERROR);

        } catch (ExecutionException e) {
            logger.error("kafka发送同步消息执行错误:{}", e.getMessage());
            throw new BusinessException(CommonException.Proxy.KAFKA_SEND_SYN_EXE_ERROR);

        } catch (TimeoutException e) {
            logger.error("kafka发送同步消息超时:{}", e.getMessage());
            throw new BusinessException(CommonException.Proxy.KAFKA_SEND_SYN_TIME_ERROR);
        }
    }
}
