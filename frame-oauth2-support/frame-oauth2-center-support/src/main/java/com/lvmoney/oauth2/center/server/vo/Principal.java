package com.lvmoney.oauth2.center.server.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Principal implements Serializable {
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private int userId;
    private String username;
}
