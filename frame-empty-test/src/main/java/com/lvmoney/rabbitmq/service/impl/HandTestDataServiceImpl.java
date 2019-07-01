/**
 * 描述:
 * 包名:com.lvmoney.hotel.service.impl
 * 版本信息: 版本1.0
 * 日期:2018年11月8日  下午2:59:44
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.rabbitmq.service.impl;

import org.springframework.stereotype.Service;

import com.lvmoney.rabbitmq.annotation.DynamicService;
import com.lvmoney.rabbitmq.service.HandMqDataService;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年11月8日 下午2:59:44
 */
@Service("handTestDataService")
@DynamicService(name = "SYN_FROOM")
public class HandTestDataServiceImpl implements HandMqDataService {
    @SuppressWarnings("rawtypes")
    @Override
    public void handData() {
        System.out.println("test");
    }

}
