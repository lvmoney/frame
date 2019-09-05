package com.lvmoney.oauth2.center.server.config;

import com.lvmoney.oauth2.center.server.service.Oauth2RedisService;
import com.lvmoney.oauth2.center.server.vo.Principal;
import com.lvmoney.oauth2.center.server.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomTokenEnhancer implements TokenEnhancer {
    @Autowired
    Oauth2RedisService oauth2RedisService;

    /**
     * 自定义一些token属性
     *
     * @param accessToken
     * @param authentication
     * @return
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        final Map<String, Object> additionalInformation = new HashMap<>();
        // Important !,client_credentials mode ,no user!
        if (authentication.getUserAuthentication() != null) {
            String username = (String) authentication.getUserAuthentication().getName();// 与登录时候放进去的UserDetail实现类一致
            UserInfo user = oauth2RedisService.getOauth2UserVo(username);
            additionalInformation.put("grantType", authentication.getOAuth2Request().getGrantType());
//            additionalInformation.put("userId", user.getUserId());
            additionalInformation.put("username", user.getUsername());
            additionalInformation.put("nikename", user.getNickname());
            additionalInformation.put("gender", user.getGender());
//            additionalInformation.put("status", 1);
        }
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
        return accessToken;
    }

}
