package com.lvmoney.oauth2.center.server.ro;/**
 * 描述:
 * 包名:com.revengemission.sso.oauth2.server.ro
 * 版本信息: 版本1.0
 * 日期:2019/7/26
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.oauth2.center.server.vo.OauthClient;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/7/26 16:51
 */
@Data
@NoArgsConstructor
public class Oauth2ClientRo implements Serializable {
    private static final long serialVersionUID = 8417996989294558127L;
    /**
     * 失效时间
     */
    private Long expire;
    /**
     * 把oauth2的user信息统一放到redis中
     */
    private Map<String, OauthClient> data;
}
