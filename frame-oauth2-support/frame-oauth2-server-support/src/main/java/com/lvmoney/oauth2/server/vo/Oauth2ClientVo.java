package com.lvmoney.oauth2.server.vo;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:${{
 * Copyright xxxx科技有限公司
 */


import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public class Oauth2ClientVo implements Serializable {
    private String client;
    private BaseClientDetails clientDetails;

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public BaseClientDetails getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(BaseClientDetails clientDetails) {
        this.clientDetails = clientDetails;
    }
}
