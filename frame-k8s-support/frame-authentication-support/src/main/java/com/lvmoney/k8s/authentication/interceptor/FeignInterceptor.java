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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/13 16:23
 */
@Component
public class FeignInterceptor implements RequestInterceptor {
    @Autowired
    OauthClientProp oauthClientProp;

    public void apply(RequestTemplate requestTemplate) {
        String requstPath = requestTemplate.path();
        if (AuthenticationConstant.OAUTH2_TOKEN_CHECK_SERVLET_NAME.equals(requstPath)) {
            String creds = String.format("%s:%s", oauthClientProp.getClientId(), oauthClientProp.getClientSecret());
            try {
                String secret = "Basic " + new String(Base64.encode(creds.getBytes("UTF-8")));
                requestTemplate.header("Authorization", secret);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }
}
