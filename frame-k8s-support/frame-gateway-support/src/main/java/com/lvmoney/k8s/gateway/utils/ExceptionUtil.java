package com.lvmoney.k8s.gateway.utils;/**
 * 描述:
 * 包名:com.lvmoney.k8s.gateway.util
 * 版本信息: 版本1.0
 * 日期:2019/8/14
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.common.exception.BusinessException;
import com.lvmoney.common.util.JsonUtil;
import com.lvmoney.common.util.ResultUtil;
import com.lvmoney.common.util.vo.ResultData;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;

import java.nio.charset.StandardCharsets;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/14 14:15
 */
public class ExceptionUtil {
    public static DataBuffer filterExceptionHandle(ServerHttpResponse serverHttpResponse, BusinessException ex) {
        ResultData resultData = ResultUtil.error(ex.getCode(), ex.getDescription());
        byte[] bytes = JsonUtil.t2JsonString(resultData).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = serverHttpResponse.bufferFactory().wrap(bytes);
        return buffer;
    }
}
