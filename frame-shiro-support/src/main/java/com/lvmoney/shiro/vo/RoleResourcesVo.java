package com.lvmoney.shiro.vo;

import java.io.Serializable;

public class RoleResourcesVo implements Serializable {
    /**
     *
     */


    private static final long serialVersionUID = -2366519090522067849L;

    private Integer id;

    private Integer roleId;

    private Integer resourcesId;

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
     * @return role_id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * @return resources_id
     */
    public Integer getResourcesId() {
        return resourcesId;
    }

    /**
     * @param resourcesId
     */
    public void setResourcesId(Integer resourcesId) {
        this.resourcesId = resourcesId;
    }
}