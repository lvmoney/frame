package com.lvmoney.rabbitmq.ack.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/1/21
 * Copyright xxxx科技有限公司
 */


import com.lvmoney.rabbitmq.ack.constant.RabbitmqConstant;
import com.lvmoney.rabbitmq.ack.ro.AckErrorRecordRo;
import com.lvmoney.rabbitmq.ack.service.RabbitmqRedisService;
import com.lvmoney.rabbitmq.ack.vo.MsgErrorVo;
import com.lvmoney.redis.service.BaseRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Service
public class RabbitmqRedisServiceImpl implements RabbitmqRedisService {
    @Autowired
    BaseRedisService baseRedisService;

    @Override
    public void ackRecord2Redis(AckErrorRecordRo ackErrorRecordRo) {
        baseRedisService.addList(RabbitmqConstant.REDIS_ACK_RECORD_NAME, ackErrorRecordRo.getMsgErrorVoList(), ackErrorRecordRo.getExpire());
    }

    @Override
    public List<MsgErrorVo> getAllAckRecord() {
        return baseRedisService.getListAll(RabbitmqConstant.REDIS_ACK_RECORD_NAME);
    }
}
