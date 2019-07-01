/**
 * 描述:
 * 包名:com.lvmoney.hotel.service
 * 版本信息: 版本1.0
 * 日期:2018年11月8日  下午2:59:09
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.rabbitmq.ack.service;

import com.lvmoney.rabbitmq.ack.vo.MessageVo;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年11月8日 下午2:59:09
 */

public interface HandMqDataService {
    @SuppressWarnings("rawtypes")
    void handData(MessageVo messageVo);
}
