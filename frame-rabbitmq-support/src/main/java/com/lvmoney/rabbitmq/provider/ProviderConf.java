/**
 * 描述:
 * 包名:com.lvmoney.pay.mq
 * 版本信息: 版本1.0
 * 日期:2018年10月11日  下午3:40:10
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.rabbitmq.provider;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lvmoney.rabbitmq.constant.RabbitmqConstant;


/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月11日 下午3:40:10
 */

@Configuration
public class ProviderConf {
    @Bean
    public Queue queuePaymentResult() {
        return new Queue(RabbitmqConstant.MQ_DATA_SYN_NAME);
    }
}
