package com.lvmoney.oauth2.server.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.builders.JdbcClientDetailsServiceBuilder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by lvmoney on 2019/1/17.
 */
public class RedisClientDetailsServiceBuilder extends
        ClientDetailsServiceBuilder<InMemoryClientDetailsServiceBuilder> {

    private Map<String, ClientDetails> clientDetails = new HashMap<String, ClientDetails>();

    @Override
    protected void addClient(String clientId, ClientDetails value) {
        clientDetails.put(clientId, value);
    }

    @Override
    protected ClientDetailsService performBuild() {
        RedisClientDetailsService clientDetailsService = new RedisClientDetailsService();
        return clientDetailsService;
    }

}
