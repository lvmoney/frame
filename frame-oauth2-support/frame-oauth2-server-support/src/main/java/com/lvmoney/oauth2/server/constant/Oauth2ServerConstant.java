/**
 * 描述:
 * 包名:com.lvmoney.oauth2.server.constant
 * 版本信息: 版本1.0
 * 日期:2019年1月18日  下午1:24:03
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.oauth2.server.constant;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月18日 下午1:24:03
 */

public class Oauth2ServerConstant {
    /**
     * oauth2客户端授权验证基础信息redis存储的key值。
     */
    public final static String REDIS_FRAME_USER_DETAILS_NAME = "REDIS_FRAME_USER_DETAILS_NAME";
    /**
     * oauth2 UserDetailsService中 User secret 的默认前缀
     */
    public final static String OAUTH2_USER_SECRET_PREFIX = "{noop}";

    /**
     * oauth2客户端授权详细基础信息redis存储的key值。
     */
    public final static String REDIS_FRAME_CLIENT_DETAILS_NAME = "REDIS_FRAME_CLIENT_DETAILS_NAME";
}
