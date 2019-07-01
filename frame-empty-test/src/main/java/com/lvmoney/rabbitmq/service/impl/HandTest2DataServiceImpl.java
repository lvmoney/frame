/**
 * 描述:
 * 包名:com.lvmoney.rabbitmq.service.impl
 * 版本信息: 版本1.0
 * 日期:2019年1月10日  下午5:52:24
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.rabbitmq.service.impl;

import org.springframework.stereotype.Service;

import com.lvmoney.rabbitmq.annotation.DynamicService;
import com.lvmoney.rabbitmq.service.HandMqDataService;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月10日 下午5:52:24
 */
@Service("testAAAA")
@DynamicService(name = "SYN_HOTEL")
public class HandTest2DataServiceImpl implements HandMqDataService {

    @Override
    public void handData() {
        System.out.println("test2");

    }

}
