package com.lvmoney.oauth2.center.server.service.impl;

import com.lvmoney.oauth2.center.server.service.Oauth2RedisService;
import com.lvmoney.oauth2.center.server.ro.Oauth2ClientDetailRo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by lvmoney on 2019/1/17.
 */
@Service
public class RedisClientDetailsServiceImpl implements ClientDetailsService {
    @Autowired
    Oauth2RedisService oauth2RedisService;

    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        BaseClientDetails baseClientDetails = new BaseClientDetails();
        Oauth2ClientDetailRo oauth2ClientDetailRo = new Oauth2ClientDetailRo();
        baseClientDetails.setClientId("SampleClientId");
        baseClientDetails.setClientSecret("$2a$10$gcrWom7ubcRaVD1.6ZIrIeJP0mtPLH5J9V/.8Qth59lZ4B/5HMq96");
        Set<String> scope = new HashSet<>();
        scope.add("user_info");
        baseClientDetails.setScope(scope);
        Set<String> authCrantType = new HashSet<>();
        authCrantType.add("authorization_code");
        authCrantType.add("refresh_token");
        authCrantType.add("password");
        authCrantType.add("client_credentials");
        authCrantType.add("frame_code");
        baseClientDetails.setAuthorizedGrantTypes(authCrantType);
        Set<String> redirectUri = new HashSet<>();
        redirectUri.add("http://localhost:8030/login");
        baseClientDetails.setRegisteredRedirectUri(redirectUri);
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_TRUSTED_CLIENT"));
        baseClientDetails.setAuthorities(grantedAuthorityList);
        baseClientDetails.setAccessTokenValiditySeconds(3600);
        baseClientDetails.setRefreshTokenValiditySeconds(2592000);
        Map<String, BaseClientDetails> data = new HashMap<>();
        data.put("SampleClientId", baseClientDetails);
        oauth2ClientDetailRo.setData(data);
        oauth2ClientDetailRo.setExpire(18000l);
        oauth2RedisService.clientDetails2Redis(oauth2ClientDetailRo);
        baseClientDetails = oauth2RedisService.getClientDetailsByClientId(clientId);
        return baseClientDetails;
    }


}
