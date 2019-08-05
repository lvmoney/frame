package com.lvmoney.oauth2.center.server.vo;

import lombok.Data;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.util.Date;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;

@Data
public class FrameOauth2AccessToken implements OAuth2AccessToken, Serializable {
    private Map<String, Object> additionalInformation;
    private Set<String> scope;
    private FrameOAuth2RefreshToken refreshToken;
    private String tokenType;
    private boolean expired;
    private String value;
    private int expiresIn;
    private Date expiration;
}
