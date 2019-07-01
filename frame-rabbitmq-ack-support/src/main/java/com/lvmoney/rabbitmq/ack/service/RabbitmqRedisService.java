package com.lvmoney.rabbitmq.ack.service;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/1/21
 * Copyright xxxx科技有限公司
 */


import com.lvmoney.rabbitmq.ack.ro.AckErrorRecordRo;
import com.lvmoney.rabbitmq.ack.vo.MsgErrorVo;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public interface RabbitmqRedisService {
    /**
     * @describe: 往redis中写入ackrecord的数据
     * @param: [ackErrorRecordRo]
     * @return: void
     * @author： lvmoney /xxxx科技有限公司
     * 2019/1/21
     */
    void ackRecord2Redis(AckErrorRecordRo ackErrorRecordRo);

    /**
     * @describe: 获得所有的的ack记录
     * @param: [key]
     * @return: java.util.List<com.lvmoney.rabbitmq.ack.ro.MsgErrorVo>
     * @author： lvmoney /xxxx科技有限公司
     * 2019/1/21
     */
    List<MsgErrorVo> getAllAckRecord();
}
