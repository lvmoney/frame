package com.lvmoney.oauth2.center.server.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Details implements Serializable {
    private static final long serialVersionUID = -305412935653682903L;
    private String graphId;
    private String inputVerificationCode;
    private String remoteAddress;
    private String sessionId;
}
