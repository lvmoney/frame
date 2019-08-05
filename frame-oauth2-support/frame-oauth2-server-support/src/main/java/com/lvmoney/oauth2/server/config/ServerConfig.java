package com.lvmoney.oauth2.server.config;

import com.lvmoney.common.utils.JsonUtil;
import com.lvmoney.oauth2.server.ro.Oauth2ClientRo;
import com.lvmoney.oauth2.server.service.Oauth2RedisService;
import com.lvmoney.oauth2.server.vo.Oauth2ClientVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月16日 下午1:30:49
 */
@Configuration
@EnableAuthorizationServer
public class ServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    AuthenticationManager authenticationManager;

    @Bean
    public TokenStore tokenStore() {
        return new FrameRedisTokenStore(redisConnectionFactory);
    }

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Qualifier("frameUserDetailsService")
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    Oauth2RedisService oauth2RedisService;

    @Bean
    public ClientDetailsService clientDetails() {
        return new RedisClientDetailsService();
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 配置token获取和验证时的策略
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
/*secret密码配置从 Spring Security 5.0开始必须以 {加密方式}+加密后的密码 这种格式填写
                 *   当前版本5新增支持加密方式：
                 *   bcrypt - BCryptPasswordEncoder (Also used for encoding)
                 *   ldap - LdapShaPasswordEncoder
                *   MD4 - Md4PasswordEncoder
                *   MD5 - new MessageDigestPasswordEncoder("MD5")
                *   noop - NoOpPasswordEncoder
                *   pbkdf2 - Pbkdf2PasswordEncoder
                *   scrypt - SCryptPasswordEncoder
                *   SHA-1 - new MessageDigestPasswordEncoder("SHA-1")
                *   SHA-256 - new MessageDigestPasswordEncoder("SHA-256")
                *   sha256 - StandardPasswordEncoder*/
//        clients.inMemory()
//                .withClient("client") .secret("{noop}secret")
//                .scopes("all")
//                .authorizedGrantTypes("authorization_code", "password", "refresh_token")
//                .autoApprove(true);
//测试用数据，把clientdetail数据存到redis
        Oauth2ClientRo oauth2ClientRo = new Oauth2ClientRo();
        oauth2ClientRo.setExpire(180000l);
        Oauth2ClientVo oauth2ClientVo = new Oauth2ClientVo();
        oauth2ClientVo.setClient("client");
        BaseClientDetails baseClientDetails = new BaseClientDetails();
        baseClientDetails.setClientId("client");
        baseClientDetails.setClientSecret("{noop}secret");
        Set authorizedGrantTypes = new TreeSet();
        authorizedGrantTypes.add("password");
        authorizedGrantTypes.add("refresh_token");
        authorizedGrantTypes.add("authorization_code");
        authorizedGrantTypes.add("client_credentials");
        baseClientDetails.setAuthorizedGrantTypes(authorizedGrantTypes);
        Set scope = new TreeSet();
        scope.add("all");
        baseClientDetails.setScope(scope);
        oauth2ClientVo.setClientDetails(baseClientDetails);
        Map<String, Object> data = new HashMap<>();
        data.put("client", JsonUtil.t2JsonString(oauth2ClientVo));
        Oauth2ClientVo oauth2ClientVo2 = new Oauth2ClientVo();
        oauth2ClientVo2.setClient("client1");
        BaseClientDetails baseClientDetails2 = new BaseClientDetails();
        baseClientDetails2.setClientId("client1");
        baseClientDetails2.setClientSecret("{noop}secret");
        Set authorizedGrantTypes2 = new TreeSet();
        authorizedGrantTypes2.add("password");
        authorizedGrantTypes2.add("refresh_token");
        authorizedGrantTypes2.add("authorization_code");
        baseClientDetails2.setAuthorizedGrantTypes(authorizedGrantTypes2);
        Set scope2 = new TreeSet();
        scope2.add("all");
        baseClientDetails2.setScope(scope);
        Map<String, Object> data2 = new HashMap<>();
        oauth2ClientVo2.setClientDetails(baseClientDetails2);
        data.put("client1", JsonUtil.t2JsonString(oauth2ClientVo2));
        oauth2ClientRo.setData(data);
        oauth2RedisService.FrameClientDetails2Redis(oauth2ClientRo);
        clients.withClientDetails(clientDetails());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 配置tokenStore，保存到redis缓存中
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
                // 不添加userDetailsService，刷新access_token时会报错
                .userDetailsService(userDetailsService);
    }


}
