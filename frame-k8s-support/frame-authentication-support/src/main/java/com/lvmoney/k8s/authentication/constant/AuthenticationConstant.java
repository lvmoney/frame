package com.lvmoney.k8s.authentication.constant;/**
 * 描述:
 * 包名:com.lvmoney.k8s.login.constant
 * 版本信息: 版本1.0
 * 日期:2019/8/11
 * Copyright XXXXXX科技有限公司
 */


import java.io.File;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/11 11:37
 */
public class AuthenticationConstant {
    public static final String GRANT_TYPE_AUTHORIZATION_CODE = "authorization_code";
    public static final String OAUTH2_REDIRECT_URI_SERVLET_NAME = "/user/index";


    public static final String OAUTH2_RESPONSE_TYPE_CODE = "code";

    public static final String OAUTH2_TOKEN_CHECK_SERVLET_NAME = "/oauth/check_token";

    /**
     * oauth2 默认的角色
     */
    public static final String DEFAULT_ROLE = "OAUTH2";

    public static final String PATH_START = "/";
}
