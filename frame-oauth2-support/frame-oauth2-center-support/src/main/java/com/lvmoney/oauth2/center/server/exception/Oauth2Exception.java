package com.lvmoney.oauth2.center.server.exception;/**
 * 描述:
 * 包名:com.lvmoney.oauth2.center.server
 * 版本信息: 版本1.0
 * 日期:2019/7/28
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.common.exception.ExceptionType;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/7/28 16:50
 */
public interface Oauth2Exception {

    int PROXYERR = 700;

    enum Proxy implements ExceptionType {
        /**
         * 验证码报错
         */
        VERIFICATION_ERROR(PROXYERR + 1, "验证码错误"),
        /**
         * 密码应包含字母、数字、符号
         */
        PASSWORD_NOT_STANDARD(PROXYERR + 2, "密码应包含字母、数字、符号"),
        /**
         * json写数据错误
         */
        DENIED_JSON_NOT_WRITE(PROXYERR + 3, "json写数据错误"),
        /**
         * 没有找到对应的客户端详细信息
         */
        CLIENT_DETAIL_NOT_EXSIT(PROXYERR + 4, "没有找到对应的客户端详细信息"),
        /**
         * 用户已经存在
         */
        USER_EXSIT(PROXYERR + 5, "用户已经存在"),
        /**
         * 不支持的编码错误
         */
        UNSUPPORTED_ENCODING_EXCEPTION(PROXYERR + 6, "不支持的编码错误"),
        AUTHENTICATION_EXCEPTION(PROXYERR + 7, "权限错误"),
        OAUTH2_CLIENT_DENIED_EXCEPTION(PROXYERR + 8, "客户端没有权限"),
        OAUTH2_OAUTH_CLIENT_IS_NULL(PROXYERR + 9, "token 失效"),
        CLIENT_NOT_EXSIT(PROXYERR + 4, "没有找到对应的客户端信息"),
        OAUTH2_ACCESS_TOKEM_REQUIRED(PROXYERR + 10, "没有检测到access_token"),
        OAUTH2_TOKEM_EXPIRED(PROXYERR + 11, "token 失效"),
        OAUTH2_SCOPE_DEFINITION_ERROR(PROXYERR + 12, "通过scope获得数据报错"),
        OAUTH2_STORE_ACCESS_TOKEN_ERROR(PROXYERR + 12, "保存accessToken报错"),
        OAUTH2_TOKEN_AUTHENTICATION_ERROR(PROXYERR + 13, "通过token获得权限报错"),
        OAUTH2_RM_ACCESS_TOKEN_ERROR(PROXYERR + 14, "删除accessToken报错"),
        OAUTH2_SERVER_ERROR(PROXYERR + 15, "调用oauth2服务出错"),
        OAUTH2_ACEESS_TOKEM_ERROR(PROXYERR + 16, "access_token已失效"),
        OAUTH2_USER_NOT_EXSIT(PROXYERR + 17, "用户不存在"),
        OAUTH2_PASSWORD_ERROR(PROXYERR + 18, "密码不匹配"),
        OAUTH2_CUSTOM_OAUTH_ERROR(PROXYERR + 19, "客户端权限校验失败"),
        OAUTH2_ACCESS_DENIED_ERROR(PROXYERR + 20, "客户端授权失败报错"),
        OAUTH2_CLIENT_DETAIL_NO_EXIST(PROXYERR + 22, "获取客户端详情失败"),
        OAUTH2_LOGIN_SUCCESS(2000, "登录成功");
        private int code;
        private String description;

        private Proxy(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public int getCode() {
            return this.code;
        }

        @Override
        public String getDescription() {
            return this.description;
        }
    }

}
