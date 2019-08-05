package com.lvmoney.oauth2.center.server.config;

import com.lvmoney.captcha.service.CaptchaService;
import com.lvmoney.common.exceptions.BusinessException;
import com.lvmoney.oauth2.center.server.exception.Oauth2Exception;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.LinkedHashMap;
import java.util.Map;

public class FrameCodeTokenGranter extends AbstractTokenGranter {

    private static final String GRANT_TYPE = "frame_code";

    UserDetailsService userDetailsService;

    CaptchaService captchaService;

    public FrameCodeTokenGranter(UserDetailsService userDetailsService,
                                 AuthorizationServerTokenServices authorizationServerTokenServices,
                                 ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory,
                                 CaptchaService captchaService) {
        super(authorizationServerTokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.userDetailsService = userDetailsService;
        this.captchaService = captchaService;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {

        Map<String, String> parameters = new LinkedHashMap<String, String>(tokenRequest.getRequestParameters());
        String username = parameters.get("username"); // 客户端提交的用户名
        String captcha = parameters.get("captcha"); // 客户端提交的验证码
        String captchaNum = parameters.get("captcha_num"); // 客户端提交的验证码编号
        String password = parameters.get("password");
        // 验证用户状态(是否禁用等),代码略
        // 验证验证码
        String codeCached = captchaService.getValidate(captchaNum).getValue();
        if (!StringUtils.equalsIgnoreCase(codeCached, captcha)) {
            throw new BusinessException(Oauth2Exception.Proxy.VERIFICATION_ERROR);
        }
        // 从库里查用户
        UserDetails user = userDetailsService.loadUserByUsername(username);
        if (user == null) {
            throw new BusinessException(Oauth2Exception.Proxy.OAUTH2_USER_NOT_EXSIT);
        }
        String rPass = user.getPassword();
        if (!new BCryptPasswordEncoder().matches(password, rPass)) {
            throw new BusinessException(Oauth2Exception.Proxy.OAUTH2_PASSWORD_ERROR);
        }

        Authentication userAuth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        // 关于user.getAuthorities(): 我们的自定义用户实体是实现了
        // org.springframework.security.core.userdetails.UserDetails 接口的, 所以有
        // user.getAuthorities()
        // 当然该参数传null也行
        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);

        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(storedOAuth2Request, userAuth);
    }

}
