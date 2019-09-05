package com.lvmoney.k8s.logback.redis.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.k8s.logback.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/8/22
 * Copyright XXXXXX科技有限公司
 */


import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.lvmoney.common.utils.ApplicationBeanUtil;
import com.lvmoney.k8s.logback.common.service.LogbackService;
import com.lvmoney.k8s.logback.common.vo.LogVo;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/22 17:40
 */
public class LogbackAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {
    @Override
    protected void append(ILoggingEvent eventObject) {
        RedisLogbackServiceImpl logbackService = ApplicationBeanUtil.getBean(RedisLogbackServiceImpl.class);
        if (logbackService != null) {
            LogVo logVo = new LogVo();
            logVo.setLevel(eventObject.getLevel().toString());
            logVo.setLogger(eventObject.getLoggerName());
            logVo.setMessage(eventObject.getFormattedMessage());
            logVo.setThread(eventObject.getThreadName());
            logVo.setTimeStamp(eventObject.getTimeStamp());
            logbackService.saveLog(logVo);
        }
    }

}