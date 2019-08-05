package com.lvmoney.oauth2.center.server.config;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lvmoney.common.exceptions.BusinessException;
import com.lvmoney.common.utils.vo.ResultData;
import com.lvmoney.oauth2.center.server.constant.Oauth2ServerConstant;
import com.lvmoney.oauth2.center.server.exception.Oauth2Exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ClientAccessDeniedHandler implements AccessDeniedHandler {
    private Logger LOGGER = LoggerFactory.getLogger(ClientAccessDeniedHandler.class);

    private String accessDeniedUrl = "/403";

    public String getAccessDeniedUrl() {
        return accessDeniedUrl;
    }

    public void setAccessDeniedUrl(String accessDeniedUrl) {
        this.accessDeniedUrl = accessDeniedUrl;
    }

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response, AccessDeniedException e)
            throws IOException, ServletException {
        String toUrl = "http://" + request.getServerName() //服务器地址
                + ":"
                + request.getServerPort()           //端口号
                + request.getContextPath()      //项目名称
                + request.getServletPath()      //请求页面或其他地址
                + "?" + (request.getQueryString()); //参数
        boolean isAjax = "XMLHttpRequest".equals(request
                .getHeader("X-Requested-With")) || "apiLogin".equals(request
                .getHeader("api-login"));
        if (isAjax) {
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            try {
                ResultData resultData = new ResultData();
                resultData.setCode(Oauth2Exception.Proxy.OAUTH2_CLIENT_DENIED_EXCEPTION.getCode());
                resultData.setMsg(toUrl);
                ObjectMapper objectMapper = new ObjectMapper();
                JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(response.getOutputStream(),
                        JsonEncoding.UTF8);
                objectMapper.writeValue(jsonGenerator, resultData);
            } catch (Exception ex) {
                LOGGER.error("不能够写入json信息:{}", ex.getMessage());
                throw new BusinessException(Oauth2Exception.Proxy.DENIED_JSON_NOT_WRITE);
            }
        } else {
            //response.sendRedirect(accessDeniedUrl + "?toUrl=" + toUrl);
            response.sendRedirect(accessDeniedUrl);
        }
    }
}
