package com.lvmoney.shiro.vo;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public class ResourcesVo implements Serializable {
    /**
     *
     */


    private static final long serialVersionUID = -8621955278670702634L;

    private Integer id;

    /**
     * 资源名称
     */
    private String userName;

    /**
     * 资源url
     */
    private String resUrl;

    /**
     * 资源类型   1:菜单    2：按钮
     */
    private Integer userType;

    /**
     * 父资源
     */
    private Integer parentId;

    /**
     * 排序
     */
    private Integer userSort;

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
     * 获取资源名称
     *
     * @return user_name - 资源名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置资源名称
     *
     * @param userName 资源名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取资源url
     *
     * @return res_url - 资源url
     */
    public String getResUrl() {
        return resUrl;
    }

    /**
     * 设置资源url
     *
     * @param resUrl 资源url
     */
    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    /**
     * 获取资源类型   1:菜单    2：按钮
     *
     * @return user_type - 资源类型   1:菜单    2：按钮
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     * 设置资源类型   1:菜单    2：按钮
     *
     * @param userType 资源类型   1:菜单    2：按钮
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * 获取父资源
     *
     * @return parent_id - 父资源
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父资源
     *
     * @param parentId 父资源
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取排序
     *
     * @return user_sort - 排序
     */
    public Integer getUserSort() {
        return userSort;
    }

    /**
     * 设置排序
     *
     * @param userSort 排序
     */
    public void setUserSort(Integer userSort) {
        this.userSort = userSort;
    }
}