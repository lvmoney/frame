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
     * @describe: 通过oauth2方式登录，加载用户权限（shiro）到redis
     * @param: [oauth2Token]
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/8/13 11:55
     */
    void initAuthority(Oauth2Token oauth2Token);

    /**
     * @describe: 通过oauth2方式登录，加载用户信息（userRo）到redis
     * @param: [oauth2Token]
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/8/13 11:58
     */
    void initUser(Oauth2Token oauth2Token);

    /**
     * @describe: 获得当前用户的权限，资源，角色对应数据
     * @param: [oauth2Token]
     * @return: com.lvmoney.shiro.ro.ShiroUriRo
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/8/16 10:28
     */
    ShiroUriRo getShiroUri(Oauth2Token oauth2Token);


}
