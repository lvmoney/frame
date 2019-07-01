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
    public final static String SIMPLE_QUEUE_NAME = "simple";
    public final static String MESSAGE_TOPIC = "topic.message";

    public final static String MESSAGE_TOPICS = "topic.messages";

    public final static String MESSAGE_FANOUT = "fanout.message";

    public final static String EXCHANGE_FANOUT = "fanout.exchange";

    public final static String EXCHANGE_TOPIC = "topic.exchange";
    public final static String ROUTING_KEY_TOPIC = "topic.#";

    public final static String REDIS_ACK_RECORD_NAME = "kafka_error_record";
}
