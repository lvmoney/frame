package com.lvmoney.oauth2.server.config;/**
 * 描述:
 * 包名:com.lvmoney.oauth2.server.config
 * 版本信息: 版本1.0
 * 日期:2019/7/24
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/7/24 21:51
 */

@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        getRedirectStrategy().sendRedirect(request, response, "http://127.0.0.1:9528/#/login?state=" + request.getParameter("state"));
    }
}
