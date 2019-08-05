package com.lvmoney.oauth2.center.server.vo;

import lombok.Data;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;

import java.io.Serializable;

@Data
public class FrameOAuth2RefreshToken implements OAuth2RefreshToken, Serializable {
    private static final long serialVersionUID = -8111339357304925068L;
    private String value;
    private Long expiration;

}
