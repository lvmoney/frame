package com.lvmoney.k8s.authentication.interceptor;/**
 * 描述:
 * 包名:com.lvmoney.k8s.login.interceptor
 * 版本信息: 版本1.0
 * 日期:2019/8/13
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.k8s.authentication.constant.AuthenticationConstant;
import com.lvmoney.k8s.authentication.properties.OauthClientProp;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/13 16:23
 */
@Component
public class FeignInterceptor implements RequestInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(FeignInterceptor.class);

    @Autowired
    OauthClientProp oauthClientProp;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String requstPath = requestTemplate.path();
        if (AuthenticationConstant.OAUTH2_TOKEN_CHECK_SERVLET_NAME.equals(requstPath)) {
            String creds = String.format("%s:%s", oauthClientProp.getClientId(), oauthClientProp.getClientSecret());
            try {
                String secret = "Basic " + Base64.encodeBase64String(creds.getBytes("UTF-8"));
                requestTemplate.header("Authorization", secret);
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("请求apply报错:{}", e.getMessage());
            }
        }
    }
}
