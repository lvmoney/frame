package com.lvmoney.kafka.ro;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/1/21
 * Copyright xxxx科技有限公司
 */


import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public class ErrorRecordRo implements Serializable {
    private List<ProducerRecord> producerRecordList;
    private Long expire;

    public List<ProducerRecord> getProducerRecordList() {
        return producerRecordList;
    }

    public void setProducerRecordList(List<ProducerRecord> producerRecordList) {
        this.producerRecordList = producerRecordList;
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }
}
