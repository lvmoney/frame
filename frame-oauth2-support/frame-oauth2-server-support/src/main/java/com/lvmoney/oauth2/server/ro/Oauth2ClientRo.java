package com.lvmoney.oauth2.server.ro;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:${{
 * Copyright xxxx科技有限公司
 */


import com.lvmoney.oauth2.server.vo.Oauth2ClientVo;

import java.io.Serializable;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public class Oauth2ClientRo implements Serializable {
    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }

    public Map<String, Oauth2ClientVo> getData() {
        return data;
    }

    public void setData(Map<String, Oauth2ClientVo> data) {
        this.data = data;
    }

    /**
     * 失效时间
     */

    private Long expire;
    /**
     * 把oauth2的user信息统一放到redis中
     */
    private Map<String, Oauth2ClientVo> data;
}
