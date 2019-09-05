package com.lvmoney.k8s.logback.common.service;/**
 * 描述:
 * 包名:com.lvmoney.k8s.logback.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/8/23
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.k8s.logback.common.vo.LogVo;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/23 8:51
 */
public interface LogbackService {

    void saveLog(LogVo logVo);
}
