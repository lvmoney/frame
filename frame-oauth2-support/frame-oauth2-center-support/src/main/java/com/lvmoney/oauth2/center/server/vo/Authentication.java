package com.lvmoney.oauth2.center.server.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Authentication implements Serializable {
    private boolean authenticated;
    private String name;
    private Details details;
    private Principal principal;
}
