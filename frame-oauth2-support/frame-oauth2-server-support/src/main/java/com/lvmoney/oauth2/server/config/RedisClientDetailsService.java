package com.lvmoney.oauth2.server.config;

import com.lvmoney.oauth2.server.service.Oauth2RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.*;
import org.springframework.stereotype.Service;

/**
 * Created by lvmoney on 2019/1/17.
 */
@Service("redisClientDetailsService")
public class RedisClientDetailsService implements ClientDetailsService {
    @Autowired
    Oauth2RedisService oauth2RedisService;

    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        ClientDetails details = oauth2RedisService.getClientDetailsByClientId(clientId);
        return details;
    }


}
