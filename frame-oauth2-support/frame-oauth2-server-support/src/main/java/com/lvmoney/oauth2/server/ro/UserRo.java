package com.lvmoney.oauth2.server.ro;

import com.lvmoney.oauth2.server.config.FrameGrantedAuthority;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lvmoney on 2019/1/18.
 */
public class UserRo implements Serializable {
    private String username;
    private String password;
    private List<FrameGrantedAuthority> frameGrantedAuthorities;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<FrameGrantedAuthority> getFrameGrantedAuthorities() {
        return frameGrantedAuthorities;
    }

    public void setFrameGrantedAuthorities(List<FrameGrantedAuthority> frameGrantedAuthorities) {
        this.frameGrantedAuthorities = frameGrantedAuthorities;
    }
}
