package com.lvmoney.shiro.vo;

import java.io.Serializable;

public class RoleVo implements Serializable {
    /**
     *
     */


    private static final long serialVersionUID = 8367203772346713262L;

    private Integer id;

    private String roleDesc;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return role_desc
     */
    public String getRoleDesc() {
        return roleDesc;
    }

    /**
     * @param roleDesc
     */
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }
}