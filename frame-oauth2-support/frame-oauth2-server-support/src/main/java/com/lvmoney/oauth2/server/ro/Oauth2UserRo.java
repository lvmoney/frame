package com.lvmoney.oauth2.server.ro;

import com.lvmoney.oauth2.server.vo.Oauth2UserVo;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by lvmoney on 2019/1/18.
 */
public class Oauth2UserRo implements Serializable {
    /**
     *
     */


    private static final long serialVersionUID = 7414371486912637607L;
    /**
     * 失效时间
     */
    private Long expire;
    /**
     * 把oauth2的user信息统一放到redis中
     */
    private Map<String, Oauth2UserVo> data;


    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }

    public Map<String, Oauth2UserVo> getData() {
        return data;
    }

    public void setData(Map<String, Oauth2UserVo> data) {
        this.data = data;
    }
}
