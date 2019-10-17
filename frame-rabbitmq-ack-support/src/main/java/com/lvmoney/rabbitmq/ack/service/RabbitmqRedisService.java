package com.lvmoney.rabbitmq.ack.service;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
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
     * 往redis中写入ackrecord的数据
     *
     * @param ackErrorRecordRo: ack实体
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 21:01
     */
    void ackRecord2Redis(AckErrorRecordRo ackErrorRecordRo);

    /**
     * 获得所有的的ack记录
     *
     * @throws
     * @return: java.util.List<com.lvmoney.rabbitmq.ack.vo.MsgErrorVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 21:01
     */
    List<MsgErrorVo> getAllAckRecord();
}
