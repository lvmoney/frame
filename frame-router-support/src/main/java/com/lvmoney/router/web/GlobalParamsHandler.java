package com.lvmoney.router.web;


import javax.servlet.http.HttpServletRequest;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.lvmoney.common.exception.FrameRuntimeException;
import com.lvmoney.router.vo.global.GlobalPhoneParams;
import com.lvmoney.router.vo.global.GlobalPhoneParamsAware;


/***
 * @describe：全局参数初始化处理器
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0
 * 2019年1月3日 上午11:13:25
 */
@Component
public class GlobalParamsHandler implements BuildRequestHandler {
    public static final String ATTR_NAME_GLOBALPARAMS = "__ATTR_NAME_GLOBALPARAMS__";

    @Override
    public void handle(HttpServletRequest request, Object requestVo) throws FrameRuntimeException {
        if (requestVo instanceof GlobalPhoneParamsAware) {
            System.out.println("globalParams");
            GlobalPhoneParams globalPhoneParams = null;
            Object attr = request.getAttribute(ATTR_NAME_GLOBALPARAMS);
            // 初始化全局参数
            if (attr != null) {
                globalPhoneParams = GlobalPhoneParams.fromUrlParams(attr.toString());
            } else {
                globalPhoneParams = GlobalPhoneParams.fromRequest(request);
            }
            ((GlobalPhoneParamsAware) requestVo).setGlobalParams(globalPhoneParams);
        }
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
