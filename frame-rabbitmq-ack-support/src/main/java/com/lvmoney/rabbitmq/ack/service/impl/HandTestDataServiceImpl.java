/**
 * 描述:
 * 包名:com.lvmoney.hotel.service.impl
 * 版本信息: 版本1.0
 * 日期:2018年11月8日  下午2:59:44
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.rabbitmq.ack.service.impl;

import com.lvmoney.rabbitmq.ack.annotations.DynamicService;
import com.lvmoney.rabbitmq.ack.service.HandMqDataService;
import com.lvmoney.rabbitmq.ack.vo.MessageVo;
import org.springframework.stereotype.Service;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年11月8日 下午2:59:44
 */
@Service("handTestDataService")
@DynamicService(name = "simple")
public class HandTestDataServiceImpl implements HandMqDataService {
    @Override
    public void handData(MessageVo messageVo) {
        System.out.print("this test simple");
    }
}
