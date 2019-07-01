/**
 * 描述:
 * 包名:com.lvmoney.shiro.ro
 * 版本信息: 版本1.0
 * 日期:2019年1月8日  下午5:38:49
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.shiro.ro;

import java.io.Serializable;
import java.util.Set;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月8日 下午5:38:49
 */

public class ShiroDataRo implements Serializable {
    /**
     *
     */


    private static final long serialVersionUID = -2658664610839100361L;
    Set<String> permissions;
    private long expire;
    private String username;
    Set<String> roles;

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
