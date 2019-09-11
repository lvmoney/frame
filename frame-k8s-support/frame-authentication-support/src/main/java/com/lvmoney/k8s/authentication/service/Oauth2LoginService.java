package com.lvmoney.k8s.authentication.service;/**
 * 描述:
 * 包名:com.lvmoney.k8s.login.service
 * 版本信息: 版本1.0
 * 日期:2019/8/13
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.k8s.authentication.vo.Oauth2Token;
import com.lvmoney.shiro.ro.ShiroUriRo;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/13 11:53
 */
public interface Oauth2LoginService {
    /**
     * 通过oauth2方式登录，加载用户权限（shiro）到redis
     *
     * @param oauth2Token: 用户token数据
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:53
     */
    void initAuthority(Oauth2Token oauth2Token);

    /**
     * 通过oauth2方式登录，加载用户信息（userRo）到redis
     *
     * @param oauth2Token: 用户token数据
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:54
     */
    void initUser(Oauth2Token oauth2Token);

    /**
     * 获得当前用户的权限，资源，角色对应数据
     *
     * @param oauth2Token: 用户token数据
     * @throws
     * @return: com.lvmoney.shiro.ro.ShiroUriRo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:54
     */
    ShiroUriRo getShiroUri(Oauth2Token oauth2Token);


}
