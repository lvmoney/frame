package com.lvmoney.oauth2.center.server.config;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lvmoney.common.utils.vo.ResultData;
import com.lvmoney.oauth2.center.server.exception.CustomOauthException;
import com.lvmoney.oauth2.center.server.exception.Oauth2Exception;
import com.lvmoney.oauth2.center.server.service.LoginHistoryService;
import com.lvmoney.oauth2.center.server.service.UserAccountService;
import com.lvmoney.oauth2.center.server.vo.LoginHistoryVo;
import com.lvmoney.oauth2.center.server.utils.IPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Component
public class ClientAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private Logger LOGGER = LoggerFactory.getLogger(ClientAuthenticationFailureHandler.class);
    private String failureUrl = "/signIn";

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    LoginHistoryService loginHistoryService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException exception)
            throws IOException {
        String username = request.getParameter("username");
        LoginHistoryVo loginHistoryVo = new LoginHistoryVo();
        loginHistoryVo.setUsername(username);
        loginHistoryVo.setIp(IPUtils.getIpAddress(request));
        loginHistoryVo.setDevice(request.getHeader("User-Agent"));
        loginHistoryVo.setRecordStatus(0);
        loginHistoryVo.setRemarks(exception.getMessage());
        loginHistoryService.asyncCreate(loginHistoryVo);

        userAccountService.loginFailure(username);

        boolean isAjax = "XMLHttpRequest".equals(request
                .getHeader("X-Requested-With")) || "apiLogin".equals(request
                .getHeader("api-login"));
        if (isAjax) {
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            try {
                ResultData resultData = new ResultData();
                resultData.setCode(Oauth2Exception.Proxy.AUTHENTICATION_EXCEPTION.getCode());
                resultData.setMsg(Oauth2Exception.Proxy.AUTHENTICATION_EXCEPTION.getDescription());
                resultData.setDate(System.currentTimeMillis());
                resultData.setSuccess(false);
                ObjectMapper objectMapper = new ObjectMapper();
                JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(response.getOutputStream(),
                        JsonEncoding.UTF8);
                objectMapper.writeValue(jsonGenerator, resultData);
            } catch (Exception ex) {
                LOGGER.error("不能够写入json信息:{}", ex.getMessage());
                throw new CustomOauthException(Oauth2Exception.Proxy.DENIED_JSON_NOT_WRITE.getDescription());
            }
        } else {
            String encodedMessage = "";
            try {
                encodedMessage = URLEncoder.encode(exception.getMessage(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("不支持的的编码错误:{}", e);
                throw new CustomOauthException(Oauth2Exception.Proxy.UNSUPPORTED_ENCODING_EXCEPTION.getDescription());
            }
            response.sendRedirect(failureUrl + "?authentication_error=true&error=" + encodedMessage);
            /*super.onAuthenticationFailure(request, response, exception);*/
        }
    }
}