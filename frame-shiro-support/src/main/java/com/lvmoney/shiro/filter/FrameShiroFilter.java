package com.lvmoney.shiro.filter;

import com.lvmoney.common.exceptions.BusinessException;
import com.lvmoney.common.exceptions.CommonException;
import com.lvmoney.shiro.constant.ShiroConstant;
import com.lvmoney.shiro.properties.ShiroConfigProp;
import com.lvmoney.shiro.service.ShiroRedisService;
import com.lvmoney.shiro.utils.FilterMapUtil;
import com.lvmoney.shiro.vo.ShiroUriVo;
import com.lvmoney.shiro.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public class FrameShiroFilter extends AccessControlFilter {
    @Autowired
    ShiroRedisService shiroRedisService;
    @Autowired
    ShiroConfigProp shiroConfigProp;
    @Value("${frame.shiro.support:false}")
    String frameSupport;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (ShiroConstant.FRAME_SHIRO_SUPPORT_FALSE.equals(frameSupport)) {
            return true;
        } else if (!ShiroConstant.FRAME_SHIRO_SUPPORT_FALSE.equals(frameSupport) && !ShiroConstant.FRAME_SHIRO_SUPPORT_TRUE.equals(frameSupport)) {
            throw new BusinessException(CommonException.Proxy.SHIRO_SUPPORT_ERROR);
        }
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String servletPath = httpServletRequest.getServletPath();
        // 先判断是否是系统配置的不需要校验的访问，例如登录请求，或者其他静态资源
        Map<String, String> filterChainDefinition = shiroConfigProp.getfilterChainDefinitionMap();
        if (FilterMapUtil.wildcardMatchMapKey(filterChainDefinition, servletPath, ShiroConstant.SHIRO_REQUEST_IGNORE)) {
            // 在这里做判断
            return true;
        }
        // 从 http 请求头中取出
        String token = httpServletRequest.getHeader("token");
        if (token == null) {
            throw new BusinessException(CommonException.Proxy.TOKEN_IS_REQUIRED);
        }
        UserVo userVo = shiroRedisService.getUser(token);
        if (userVo == null) {
            throw new BusinessException(CommonException.Proxy.TOKEN_USER_NOT_EXSIT);
        }
        String username = userVo.getUserId();
        String password = userVo.getPassWord();
        try {
            UsernamePasswordToken shiroToken = new UsernamePasswordToken(username, password);
            Subject subject = SecurityUtils.getSubject();
            subject.login(shiroToken);
            if (servletPath.endsWith(ShiroConstant.SERVLET_END_WITH)) {
                servletPath = servletPath.substring(0, servletPath.lastIndexOf(ShiroConstant.SERVLET_END_WITH));
            }
            ShiroUriVo shiroUriVo = shiroRedisService.getShiroUriData(servletPath);
            if (shiroUriVo != null) {
                List<String> roleList = new ArrayList<>(shiroUriVo.getRole());
                boolean[] roles = subject.hasRoles(roleList);
                boolean isRole = isPass(roles);
                if (isRole) {
                    return true;
                } else {
                    Set<String> permissionSet = shiroUriVo.getPermission();
                    boolean isPermitted = false;
                    for (String permission : permissionSet) {
                        if (subject.isPermitted(permission)) {
                            isPermitted = true;
                            break;
                        }
                    }
                    if (isPermitted) {
                        return true;
                    }
                    throw new BusinessException(CommonException.Proxy.SHIRO_AUTHORIZATION_EXCEPTIONT);
                }
            } else {
                throw new BusinessException(CommonException.Proxy.SHIRO_URI_EXCEPTIONT);
            }
        } catch (Exception e) {
            throw new BusinessException(CommonException.Proxy.SHIRO_AUTHORIZATION_EXCEPTIONT);
        }
    }

    private boolean isPass(boolean[] booleans) {
        boolean result = false;
        for (boolean bool : booleans) {
            if (bool) {
                result = true;
                break;
            }
        }
        return result;
    }

}
