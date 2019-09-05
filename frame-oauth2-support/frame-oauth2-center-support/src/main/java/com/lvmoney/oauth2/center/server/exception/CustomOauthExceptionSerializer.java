package com.lvmoney.oauth2.center.server.exception;/**
 * 描述:
 * 包名:com.lvmoney.oauth2.center.server.exception
 * 版本信息: 版本1.0
 * 日期:2019/8/8
 * Copyright XXXXXX科技有限公司
 */


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @describe：客户端权限校验错误统一处理和frame整体框架一致
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/8 9:36
 */
public class CustomOauthExceptionSerializer extends StdSerializer<OAuth2Exception> {
    public CustomOauthExceptionSerializer() {
        super(OAuth2Exception.class);
    }

    @Override
    public void serialize(OAuth2Exception value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("code", Oauth2Exception.Proxy.OAUTH2_CUSTOM_OAUTH_ERROR.getCode());
        gen.writeStringField("msg", buildErrorMsg(value.getMessage()));
        gen.writeBooleanField("success", false);
        gen.writeObjectField("date", System.currentTimeMillis());
        gen.writeEndObject();
    }

    /**
     * @describe: 替换返回值里面的错误信息，用户体验更好
     * @param: [msg]
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/8/8 10:20
     */
    private String buildErrorMsg(String msg) {
        if (msg.startsWith("Invalid refresh token")) {
            return "refresh_token 校验没通过";
        } else if (msg.startsWith("Invalid scope")) {
            return "不支持的scope";
        } else if (msg.startsWith("Unauthorized grant type")) {
            return "不支持的grant_type";
        } else {
            return msg;
        }
    }
}