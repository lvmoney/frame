/**
 * 描述:
 * 包名:com.lvmoney.hotel.ro
 * 版本信息: 版本1.0
 * 日期:2018年11月5日  上午10:09:18
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.rabbitmq.vo;

import java.io.Serializable;

/**
 * @param <T>
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年11月5日 上午10:09:18
 */

public class MqDataVo<T> implements Serializable {
    /**
     *
     */


    private static final long serialVersionUID = -5977813880584317111L;
    /**
     * 队列类型。fanout,direct，topic
     */
    private String mqType;
    /**
     * 队列名称。
     */
    private String mqName;
    /**
     * 数据
     */
    private T data;

    public String getMqType() {
        return mqType;
    }

    public void setMqType(String mqType) {
        this.mqType = mqType;
    }

    public String getMqName() {
        return mqName;
    }

    public void setMqName(String mqName) {
        this.mqName = mqName;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
