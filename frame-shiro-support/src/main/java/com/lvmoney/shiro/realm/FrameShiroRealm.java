package com.lvmoney.shiro.realm;

import com.lvmoney.shiro.ro.ShiroDataRo;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.lvmoney.common.exceptions.BusinessException;
import com.lvmoney.common.exceptions.CommonException;
import com.lvmoney.shiro.vo.ShiroDataVo;
import com.lvmoney.shiro.vo.UserVo;
import com.lvmoney.shiro.service.ShiroRedisService;

import java.util.*;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public class FrameShiroRealm extends AuthorizingRealm {
    @Autowired
    ShiroRedisService shiroRedisService;

    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String name = token.getUsername();
        String password = String.valueOf(token.getPassword());
        if (StringUtils.isBlank(name) || StringUtils.isBlank(password)) {
            throw new BusinessException(CommonException.Proxy.SHIRO_UNAUTHORIZED_EXCEPTIONT);
        }
        UserVo user = new UserVo();
        user.setUserName(name);
        user.setPassWord(password);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                // 用户
                user,
                // 密码
                user.getPassWord(),
                // realm name
                getName()
        );
        return authenticationInfo;
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Object principal = principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if (principal instanceof UserVo) {
            UserVo userLogin = (UserVo) principal;
            String userId = userLogin.getUserName();
            ShiroDataVo shiroDataVo = shiroRedisService.getShiroData(userId);
            if (shiroDataVo == null) {
                throw new BusinessException(CommonException.Proxy.SHIRO_REDIS_NOT_EXSIT);
            }
            Set<String> roles = shiroDataVo.getRoles();
            if (ObjectUtils.allNotNull(roles)) {
                authorizationInfo.addRoles(roles);
            }
            Set<String> permissions = shiroDataVo.getPermissions();
            if (ObjectUtils.allNotNull(permissions)) {
                authorizationInfo.addStringPermissions(permissions);
            }
            ShiroDataRo shiroDataRo = new ShiroDataRo();
            shiroDataRo.setUsername(userId);
            shiroDataRo.setRoles(roles);
            shiroDataRo.setPermissions(permissions);
            shiroDataRo.setExpire(shiroDataVo.getExpire());
            shiroRedisService.saveShiroData(shiroDataRo);
        }
        return authorizationInfo;
    }

}
