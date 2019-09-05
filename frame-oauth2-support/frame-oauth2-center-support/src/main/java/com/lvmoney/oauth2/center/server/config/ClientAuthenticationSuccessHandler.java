package com.lvmoney.oauth2.center.server.config;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lvmoney.common.utils.vo.ResultData;
import com.lvmoney.oauth2.center.server.exception.CustomOauthException;
import com.lvmoney.oauth2.center.server.exception.Oauth2Exception;
import com.lvmoney.oauth2.center.server.service.UserAccountService;
import com.lvmoney.oauth2.center.server.vo.LoginHistoryVo;
import com.lvmoney.oauth2.center.server.service.LoginHistoryService;
import com.lvmoney.oauth2.center.server.utils.IPUtils;
import com.lvmoney.oauth2.center.server.vo.RoleEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ClientAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private Logger LOGGER = LoggerFactory.getLogger(ClientAuthenticationSuccessHandler.class);

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    LoginHistoryService loginHistoryService;

    RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        String redirectUrl = "";
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null && StringUtils.isNotEmpty(savedRequest.getRedirectUrl())) {
            redirectUrl = savedRequest.getRedirectUrl();
        }

        LoginHistoryVo loginHistoryVo = new LoginHistoryVo();
        loginHistoryVo.setUsername(authentication.getName());
        loginHistoryVo.setIp(IPUtils.getIpAddress(request));
        loginHistoryVo.setDevice(request.getHeader("User-Agent"));
        loginHistoryVo.setRecordStatus(1);
        loginHistoryVo.setRemarks(Oauth2Exception.Proxy.OAUTH2_LOGIN_SUCCESS.getDescription());
        loginHistoryService.asyncCreate(loginHistoryVo);
        userAccountService.loginSuccess(authentication.getName());
        boolean isAjax = "XMLHttpRequest".equals(request
                .getHeader("X-Requested-With")) || "apiLogin".equals(request
                .getHeader("api-login"));

        if (isAjax) {
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            try {
                ResultData resultData = new ResultData();
                resultData.setCode(Oauth2Exception.Proxy.SUCCESS.getCode());
                resultData.setMsg(Oauth2Exception.Proxy.SUCCESS.getDescription());
                resultData.setDate(System.currentTimeMillis());
                resultData.setSuccess(true);
                ObjectMapper objectMapper = new ObjectMapper();
                JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(response.getOutputStream(),
                        JsonEncoding.UTF8);
                objectMapper.writeValue(jsonGenerator, resultData);
            } catch (Exception ex) {
                LOGGER.error("不能够写入json信息:{}", ex.getMessage());
                throw new CustomOauthException(Oauth2Exception.Proxy.DENIED_JSON_NOT_WRITE.getDescription());
            }
        } else {
            //Call the parent method to manage the successful authentication
            //setDefaultTargetUrl("/");
            if (StringUtils.isNotEmpty(redirectUrl)) {
                super.onAuthenticationSuccess(request, response, authentication);
            } else {
                if (authentication.getAuthorities().contains(new SimpleGrantedAuthority(RoleEnum.ROLE_USER.toString()))) {
                    response.sendRedirect("/");
                } else {
                    response.sendRedirect("/management/user");
                }
            }
        }

    }

}
