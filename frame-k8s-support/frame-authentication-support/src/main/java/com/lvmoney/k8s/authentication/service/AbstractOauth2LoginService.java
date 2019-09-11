package com.lvmoney.k8s.authentication.service;/**
 * 描述:
 * 包名:com.lvmoney.k8s.login.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/8/13
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.common.constant.CommonConstant;
import com.lvmoney.common.ro.UserRo;
import com.lvmoney.common.utils.SnowflakeIdFactoryUtil;
import com.lvmoney.jwt.service.JwtRedisService;
import com.lvmoney.k8s.authentication.constant.AuthenticationConstant;
import com.lvmoney.k8s.authentication.vo.Oauth2Token;
import com.lvmoney.shiro.ro.ShiroDataRo;
import com.lvmoney.shiro.ro.ShiroUriRo;
import com.lvmoney.shiro.service.ShiroRedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/13 14:01
 */
public abstract class AbstractOauth2LoginService implements Oauth2LoginService {
    @Autowired
    JwtRedisService jwtRedisService;

    @Autowired
    ShiroRedisService shiroRedisService;

    @Override
    public void initUser(Oauth2Token oauth2Token) {
        UserRo userRo = new UserRo();
        if (StringUtils.isNotEmpty(oauth2Token.getAccess_token())) {
            userRo.setToken(CommonConstant.TOKEM_OAUTH2_PREFIX + oauth2Token.getAccess_token());
            userRo.setUsername(oauth2Token.getUsername());
            userRo.setUserId(oauth2Token.getUsername());
            SnowflakeIdFactoryUtil idWorker = new SnowflakeIdFactoryUtil(1, 2);
            userRo.setPassword(String.valueOf(idWorker.nextId()));
            jwtRedisService.saveToken2Redis(userRo);
        }
    }

    @Override
    public void initAuthority(Oauth2Token oauth2Token) {
        if (StringUtils.isNotEmpty(oauth2Token.getAccess_token())) {
            ShiroDataRo shiroDataRo = new ShiroDataRo();
            shiroDataRo.setUsername(oauth2Token.getUsername());
            Set<String> roles = new HashSet() {{
                add(AuthenticationConstant.DEFAULT_ROLE);
            }};
            shiroDataRo.setRoles(roles);
            shiroRedisService.saveShiroData(shiroDataRo);
            ShiroUriRo shiroUriRo = getShiroUri(oauth2Token);
            shiroRedisService.saveShiroUriData(shiroUriRo);
        }

    }

    @Override
    public ShiroUriRo getShiroUri(Oauth2Token oauth2Token) {
        return null;
    }
}
