package com.lvmoney.oauth2.center.server.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class RequestParameters implements Serializable {
    private static final long serialVersionUID = -2697205728038157188L;
    private String response_type;
    private String redirect_uri;
    private String client_id;
}
