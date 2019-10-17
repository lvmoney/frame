package com.lvmoney.k8s.authentication.controller;/**
 * 描述:
 * 包名:com.lvmoney.k8s.login.controller
 * 版本信息: 版本1.0
 * 日期:2019/8/9
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.common.constant.CommonConstant;
import com.lvmoney.common.exception.BusinessException;
import com.lvmoney.common.exception.CommonException;
import com.lvmoney.common.ro.UserRo;
import com.lvmoney.common.util.ResultUtil;
import com.lvmoney.common.util.vo.ResultData;
import com.lvmoney.jwt.service.JwtRedisService;
import com.lvmoney.k8s.authentication.constant.AuthenticationConstant;
import com.lvmoney.k8s.authentication.properties.OauthClientProp;
import com.lvmoney.k8s.authentication.server.Oauth2Server;
import com.lvmoney.k8s.authentication.service.Oauth2LoginService;
import com.lvmoney.k8s.authentication.vo.Oauth2AuthorizationCode;
import com.lvmoney.k8s.authentication.vo.Oauth2Token;
import com.lvmoney.k8s.authentication.vo.Oauth2TokenCheck;
import com.lvmoney.k8s.authentication.vo.resp.Oauth2AuthorizationRespVo;
import com.lvmoney.k8s.authentication.vo.resp.ShiroCheckRespVo;
import com.lvmoney.shiro.properties.ShiroConfigProp;
import com.lvmoney.shiro.ro.ShiroDataRo;
import com.lvmoney.shiro.ro.ShiroUriRo;
import com.lvmoney.shiro.service.ShiroRedisService;
import com.lvmoney.shiro.vo.ShiroUriVo;
import com.lvmoney.shiro.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/9 15:11
 */
@RestController
@RequestMapping("/user")
public class AuthenticationController {
    @Autowired
    Oauth2Server oauth2Server;

    @Autowired
    OauthClientProp oauthClientProp;
    @Value("${rpc.server.authentication}")
    String redirectUri;


    @Autowired
    ShiroRedisService shiroRedisService;
    @Autowired
    ShiroConfigProp shiroConfigProp;

    @Autowired
    JwtRedisService jwtRedisService;
    @Autowired
    Oauth2LoginService oauth2LoginService;

    @GetMapping("/index")
    public ResultData<Oauth2AuthorizationRespVo> index(Oauth2AuthorizationCode oauth2AuthorizationCode) {
        Oauth2Token oauth2Token = oauth2Server.oauth2TokenAuthoriztionCode(oauthClientProp.getClientId(),
                oauthClientProp.getClientSecret(),
                AuthenticationConstant.GRANT_TYPE_AUTHORIZATION_CODE,
                redirectUri + AuthenticationConstant.OAUTH2_REDIRECT_URI_SERVLET_NAME,
                oauth2AuthorizationCode.getCode());
        oauth2LoginService.initAuthority(oauth2Token);
        oauth2LoginService.initUser(oauth2Token);
        Oauth2AuthorizationRespVo respVo = new Oauth2AuthorizationRespVo();
        respVo.setAccessToken(oauth2Token.getAccess_token());
        respVo.setRefreshToken(oauth2Token.getRefresh_token());
        respVo.setUsername(oauth2Token.getUsername());
        return ResultUtil.success(respVo);
    }

    @GetMapping("/login")
    public void login(HttpServletResponse response) {
        try {
            String redirect = oauthClientProp.getUserAuthorizationUri() + "?client_id=" + oauthClientProp.getClientId()
                    + "&response_type=" + AuthenticationConstant.OAUTH2_RESPONSE_TYPE_CODE + "&redirect_uri=" + redirectUri + AuthenticationConstant.OAUTH2_REDIRECT_URI_SERVLET_NAME;
            response.sendRedirect(redirect);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @describe:1、调用oauth2 token 校验接口
     * 2、这个接口结合jwt的token拦截器，整体来完成jwt或者oauth2 token的校验
     * 3、这个接口用在gateway的全局过滤器
     * @param: [request]
     * @return: com.lvmoney.k8s.login.vo.Oauth2TokenCheck
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/8/13 16:46
     */
    @GetMapping("/token/check")
    public ResultData<Oauth2TokenCheck> checkLogin(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (token.startsWith(CommonConstant.TOKEM_OAUTH2_PREFIX)) {
            token = token.replaceFirst(CommonConstant.TOKEM_OAUTH2_PREFIX, "");
            Oauth2TokenCheck oauth2TokenCheck = oauth2Server.oauth2CheckToken(token);
            if (StringUtils.isNotEmpty(oauth2TokenCheck.getClient_id())) {
                oauth2TokenCheck.setAdopt(true);
            } else {
                oauth2TokenCheck.setAdopt(false);
            }
            return ResultUtil.success(oauth2TokenCheck);
        } else if (token.startsWith(CommonConstant.TOKEM_JWT_PREFIX)) {
            Oauth2TokenCheck oauth2TokenCheck = new Oauth2TokenCheck();
            oauth2TokenCheck.setAdopt(true);
            return ResultUtil.success(oauth2TokenCheck);
        } else {
            Oauth2TokenCheck oauth2TokenCheck = new Oauth2TokenCheck();
            oauth2TokenCheck.setAdopt(false);
            return ResultUtil.success(oauth2TokenCheck);
        }

    }


    @GetMapping("/authority/check")
    public ResultData<ShiroCheckRespVo> checkAuthority(HttpServletRequest request, String servletPath) {
        String token = request.getHeader("token");
        ShiroCheckRespVo shiroCheckRespVo = new ShiroCheckRespVo();
        if (token == null) {
            throw new BusinessException(CommonException.Proxy.TOKEN_IS_REQUIRED);
        }
        UserVo userVo = shiroRedisService.getUser(token);
        if (userVo == null) {
            throw new BusinessException(CommonException.Proxy.TOKEN_USER_NOT_EXSIT);
        }
        String username = userVo.getUserName();
        String password = userVo.getPassWord();
        try {
            UsernamePasswordToken shiroToken = new UsernamePasswordToken(username, password);
            Subject subject = SecurityUtils.getSubject();
            subject.login(shiroToken);
            if (servletPath.endsWith(AuthenticationConstant.PATH_START)) {
                servletPath = servletPath.substring(0, servletPath.lastIndexOf(AuthenticationConstant.PATH_START));
            }
            ShiroUriVo shiroUriVo = shiroRedisService.getShiroUriData(servletPath);
            if (shiroUriVo != null) {
                List<String> roleList = new ArrayList<>(shiroUriVo.getRole());
                boolean[] roles = subject.hasRoles(roleList);
                boolean isRole = isPass(roles);
                if (isRole) {
                    shiroCheckRespVo.setPass(true);
                    return ResultUtil.success(shiroCheckRespVo);
                } else {
                    Set<String> permissionSet = shiroUriVo.getPermission();
                    boolean isPermitted = false;
                    for (String permission : permissionSet) {
                        if (subject.isPermitted(permission)) {
                            isPermitted = true;
                            break;
                        }
                    }
                    if (isPermitted) {
                        shiroCheckRespVo.setPass(true);
                        return ResultUtil.success(shiroCheckRespVo);
                    }
                    throw new BusinessException(CommonException.Proxy.SHIRO_AUTHORIZATION_EXCEPTIONT);
                }
            } else {
                throw new BusinessException(CommonException.Proxy.SHIRO_URI_EXCEPTIONT);
            }
        } catch (Exception e) {
            throw new BusinessException(CommonException.Proxy.SHIRO_AUTHORIZATION_EXCEPTIONT);
        }

    }


    @GetMapping("/test")
    public String test() {
        UserRo userRo = new UserRo();
        userRo.setPassword("123456");
        userRo.setToken("1234");
        userRo.setUserId("lvmoney");
        userRo.setUsername("xml");
        userRo.setExpire(88888L);

        jwtRedisService.saveToken2Redis(userRo);


        ShiroUriRo shiroUriRo = new ShiroUriRo();
        shiroUriRo.setUri("/user/test");

        ShiroUriVo shiroUriVo = new ShiroUriVo();
        Set<String> role = new HashSet() {{
            add("admin");
        }};
        shiroUriVo.setRole(role);
        shiroUriRo.setShiroUriVo(shiroUriVo);
        shiroUriRo.setExpire(88888L);
        shiroUriRo.setUsername("xml");

        shiroRedisService.saveShiroUriData(shiroUriRo);

        ShiroDataRo shiroDataRo = new ShiroDataRo();
        shiroDataRo.setUsername("xml");
        shiroDataRo.setRoles(role);
        shiroDataRo.setExpire(88888L);
        shiroRedisService.saveShiroData(shiroDataRo);
        return "test";
    }

    private boolean isPass(boolean[] booleans) {
        boolean result = false;
        for (boolean bool : booleans) {
            if (bool) {
                result = true;
                break;
            }
        }
        return result;
    }
}
