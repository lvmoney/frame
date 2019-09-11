package com.lvmoney.rabbitmq.ack.constant;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/1/21
 * Copyright xxxx科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public class RabbitmqConstant {
    public static final String SIMPLE_QUEUE_NAME = "simple";
    public static final String MESSAGE_TOPIC = "topic.message";

    public static final String MESSAGE_TOPICS = "topic.messages";

    public static final String MESSAGE_FANOUT = "fanout.message";

    public static final String EXCHANGE_FANOUT = "fanout.exchange";

    public static final String EXCHANGE_TOPIC = "topic.exchange";
    public static final String ROUTING_KEY_TOPIC = "topic.#";

    public static final String REDIS_ACK_RECORD_NAME = "rabbitmq_error_record";
}
