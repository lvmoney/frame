package com.lvmoney.oauth2.center.server.service.impl;

import com.lvmoney.common.exceptions.BusinessException;
import com.lvmoney.common.exceptions.CommonException;
import com.lvmoney.oauth2.center.server.exception.CustomOauthException;
import com.lvmoney.oauth2.center.server.exception.Oauth2Exception;
import com.lvmoney.oauth2.center.server.service.Db2RedisService;
import com.lvmoney.oauth2.center.server.service.Oauth2RedisService;
import com.lvmoney.oauth2.center.server.ro.Oauth2ClientDetailRo;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Service
public class RedisClientDetailsServiceImpl implements ClientDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(RedisClientDetailsServiceImpl.class);

    @Autowired
    Oauth2RedisService oauth2RedisService;
    @Autowired
    Db2RedisService db2RedisService;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        BaseClientDetails baseClientDetails = oauth2RedisService.getClientDetailsByClientId(clientId);
        if (ObjectUtils.anyNotNull(baseClientDetails)) {
            return baseClientDetails;
        } else {
//            Oauth2ClientDetailRo oauth2ClientDetailRo = new Oauth2ClientDetailRo();
////            baseClientDetails.setClientId("SampleClientId");
////            baseClientDetails.setClientSecret("$2a$10$gcrWom7ubcRaVD1.6ZIrIeJP0mtPLH5J9V/.8Qth59lZ4B/5HMq96");
////            Set<String> scope = new HashSet<>();
////            scope.add("user_info");
////            baseClientDetails.setScope(scope);
////            Set<String> authCrantType = new HashSet<>();
////            authCrantType.add("authorization_code");
////            authCrantType.add("refresh_token");
////            authCrantType.add("password");
////            authCrantType.add("client_credentials");
////            authCrantType.add("frame_code");
////            baseClientDetails.setAuthorizedGrantTypes(authCrantType);
////            Set<String> redirectUri = new HashSet<>();
////            redirectUri.add("http://localhost:8030/login");
////            baseClientDetails.setRegisteredRedirectUri(redirectUri);
////            List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
////            grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_TRUSTED_CLIENT"));
////            baseClientDetails.setAuthorities(grantedAuthorityList);
////            baseClientDetails.setAccessTokenValiditySeconds(3600);
////            baseClientDetails.setRefreshTokenValiditySeconds(2592000);
////            Map<String, BaseClientDetails> data = new HashMap<>();
////            data.put("SampleClientId", baseClientDetails);
////            oauth2ClientDetailRo.setData(data);
////            oauth2ClientDetailRo.setExpire(18000L);
////            oauth2RedisService.clientDetails2Redis(oauth2ClientDetailRo);
            db2RedisService.loadClientDetailsByClientId(clientId);
            baseClientDetails = oauth2RedisService.getClientDetailsByClientId(clientId);
            if (baseClientDetails == null) {
                throw new CustomOauthException(Oauth2Exception.Proxy.OAUTH2_CLIENT_DETAIL_NO_EXIST.getDescription());
            }
            return baseClientDetails;
        }
    }


}
