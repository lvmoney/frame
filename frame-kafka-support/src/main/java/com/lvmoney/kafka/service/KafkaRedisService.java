package com.lvmoney.kafka.service;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/1/22
 * Copyright xxxx科技有限公司
 */


import com.lvmoney.kafka.ro.ErrorRecordRo;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public interface KafkaRedisService {
    /**
     * 往redis中写入ackrecord的数据
     *
     * @param ackErrorRecordRo: 确认信息
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:50
     */
    void errorRecord2Redis(ErrorRecordRo ackErrorRecordRo);

    /**
     * 获得所有消息生成者
     *
     * @throws
     * @return: java.util.List<org.apache.kafka.clients.producer.ProducerRecord>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:50
     */
    List<ProducerRecord> getAllProducerRecord();
}
