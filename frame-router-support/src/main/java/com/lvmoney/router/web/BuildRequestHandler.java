package com.lvmoney.router.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.Ordered;

import com.lvmoney.common.exceptions.FrameRuntimeException;

/**
 * @describe： 构建请求处理链 所有实现必须被mvc容器管理 返回较小的order值的实现排在处理链更前方
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年12月29日 上午11:10:49
 */
public interface BuildRequestHandler extends Ordered {
    /**
     * 处理请求设置全局参数
     *
     * @param request:   http request
     * @param requestVo: 请求实体
     * @throws FrameRuntimeException 运行时错误
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:11
     */
    void handle(HttpServletRequest request, Object requestVo) throws FrameRuntimeException;

}
