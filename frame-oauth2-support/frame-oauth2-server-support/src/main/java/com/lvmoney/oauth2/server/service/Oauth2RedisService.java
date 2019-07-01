package com.lvmoney.oauth2.server.service;

import com.lvmoney.oauth2.server.ro.Oauth2ClientRo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.ClientDetails;

import com.lvmoney.oauth2.server.ro.Oauth2UserRo;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月18日 下午2:17:09
 */
public interface Oauth2RedisService {
    /**
     * @param oauth2UserRo 2019年1月18日下午2:17:12
     * @describe:oauth2 userdetail 初始化到redis中
     * @author: lvmoney /xxxx科技有限公司
     */
    void FrameUserDetails2Redis(Oauth2UserRo oauth2UserRo);

    /**
     * @param username
     * @return 2019年1月18日下午2:17:38
     * @describe:oauth2获取特定用户的 user信息
     * @author: lvmoney /xxxx科技有限公司
     */
    User getOauth2UserVo(String username);

    ClientDetails getClientDetailsByClientId(String clientId);

    void FrameClientDetails2Redis(Oauth2ClientRo oauth2ClientRo);
}
