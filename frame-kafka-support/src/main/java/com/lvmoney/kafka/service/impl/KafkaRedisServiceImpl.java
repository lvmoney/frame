package com.lvmoney.kafka.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/1/22
 * Copyright xxxx科技有限公司
 */


import com.lvmoney.kafka.constant.KafkaConstant;
import com.lvmoney.kafka.ro.ErrorRecordRo;
import com.lvmoney.kafka.service.KafkaRedisService;
import com.lvmoney.redis.service.BaseRedisService;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Service
public class KafkaRedisServiceImpl implements KafkaRedisService {
    @Autowired
    BaseRedisService baseRedisService;

    @Override
    public void errorRecord2Redis(ErrorRecordRo errorRecordRo) {
        baseRedisService.addList(KafkaConstant.REDIS_KAFKA_ERROR_RECORD_NAME, errorRecordRo.getProducerRecordList(), errorRecordRo.getExpire());
    }

    @Override
    public List<ProducerRecord> getAllProducerRecord() {
        return baseRedisService.getListAll(KafkaConstant.REDIS_KAFKA_ERROR_RECORD_NAME);
    }
}
