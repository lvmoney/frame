package com.lvmoney.rabbitmq.ack.ro;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/1/21
 * Copyright xxxx科技有限公司
 */


import com.lvmoney.rabbitmq.ack.vo.MsgErrorVo;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public class AckErrorRecordRo implements Serializable {
    private List<MsgErrorVo> msgErrorVoList;
    private Long expire;

    public List<MsgErrorVo> getMsgErrorVoList() {
        return msgErrorVoList;
    }

    public void setMsgErrorVoList(List<MsgErrorVo> msgErrorVoList) {
        this.msgErrorVoList = msgErrorVoList;
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }
}
