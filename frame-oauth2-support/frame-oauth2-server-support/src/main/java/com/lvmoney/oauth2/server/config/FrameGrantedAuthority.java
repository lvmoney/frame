package com.lvmoney.oauth2.server.config;

import org.springframework.security.core.GrantedAuthority;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月16日 下午1:30:27
 */
public class FrameGrantedAuthority implements GrantedAuthority {
    /**
     *
     */


    private static final long serialVersionUID = 8737418056256554173L;
    private String roleId;
    private String menuId;

    @Override
    public String getAuthority() {
        return roleId + "&" + menuId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public FrameGrantedAuthority(String roleId, String menuId) {
        this.roleId = roleId;
        this.menuId = menuId;
    }
}
