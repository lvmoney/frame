/**
 * 描述:
 * 包名:com.lvmoney.pay.factory
 * 版本信息: 版本1.0
 * 日期:2018年10月18日  上午11:07:01
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.kafka.factory;

import com.lvmoney.kafka.service.HandMqDataService;
import com.lvmoney.kafka.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @describe：调用不同数据处理策略模式环境角色类
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月18日 上午11:07:01
 */
@Component
public class HandMqServiceContext {
    @Autowired
    private Map<String, HandMqDataService> strategyMap = new ConcurrentHashMap<>();

    /**
     * @describe:策略注入
     * @author: lvmoney /xxxx科技有限公司
     * 2018年11月8日下午3:08:36
     */
    @Autowired
    public <T> HandMqServiceContext(Map<String, HandMqDataService> strategyMap) {
        this.strategyMap.clear();
        strategyMap.forEach((k, v) -> this.strategyMap.put(k, v));

    }

    /**
     * @param implName
     * @param messageVo 2018年11月8日下午3:08:26
     * @describe:策略方法
     * @author: lvmoney /xxxx科技有限公司
     */
    @SuppressWarnings("rawtypes")
    public void getData(String implName, MessageVo messageVo) {
        strategyMap.get(implName).handData(messageVo);
    }

    public Map<String, HandMqDataService> getStrategyMap() {
        return strategyMap;
    }

}
