package com.lvmoney.k8s.gateway.exception;/**
 * 描述:
 * 包名:com.lvmoney.oauth2.center.server
 * 版本信息: 版本1.0
 * 日期:2019/7/28
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.common.exceptions.ExceptionType;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/7/28 16:50
 */
public interface GatewayException {

    int PROXYERR = 900;

    enum Proxy implements ExceptionType {
        /**
         * 成功
         */
        SUCCESS(2000, "success"),
        /**
         * 路由支持
         */
        GATEWAY_SUPPORT_ERROR(PROXYERR + 1, "frame.gateway.support value is 'true' or 'false'"),
        /**
         * 验证码校验
         */
        GATEWAY_TOKEN_CHECK_ERROR(PROXYERR + 2, "token校验没有通过"),
        /**
         * 请求地址授权
         */
        GATEWAY_SHIRO_CHECK_ERROR(PROXYERR + 3, "请求的地址未授权"),
        /**
         * 白名单
         */
        GATEWAY_WHITE_CHECK_ERROR(PROXYERR + 4, "服务白名单校验未通过"),


        /**
         * 白名单支持
         */
        WHITE_SUPPORT_ERROR(PROXYERR + 5, "frame.white.support value is 'true' or 'false'"),

        /**
         * 内部服务没有被允许调用
         */
        GATEWAY_INTERNAL_CHECK_ERROR(PROXYERR + 6, "该服务不允许被其他服务调用"),

        /**
         * 其他
         */
        OTHER(PROXYERR + 99, "未知错误");
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
