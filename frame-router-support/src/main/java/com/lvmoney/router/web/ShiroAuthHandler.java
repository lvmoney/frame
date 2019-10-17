/**
 * 描述:
 * 包名:com.lvmoney.router.web
 * 版本信息: 版本1.0
 * 日期:2019年1月9日  下午4:35:49
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.router.web;

import com.lvmoney.common.exception.BusinessException;
import com.lvmoney.common.exception.CommonException;
import com.lvmoney.shiro.constant.ShiroConstant;
import com.lvmoney.shiro.properties.ShiroConfigProp;
import com.lvmoney.shiro.service.ShiroRedisService;
import com.lvmoney.shiro.util.FilterMapUtil;
import com.lvmoney.shiro.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月9日 下午4:35:49
 */
@Component
public class ShiroAuthHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroAuthHandler.class);
    @Autowired
    ShiroRedisService shiroRedisService;
    @Autowired
    ShiroConfigProp shiroConfigProp;
    @Value("${frame.shiro.support:false}")
    String frameSupport;
    /**
     * 忽略标识
     */
    private static final String IGNORE_IDENTIFICATION = "anon";

    public boolean authenticationValidated(HttpServletRequest httpServletRequest, String role) {
        if (ShiroConstant.FRAME_SHIRO_SUPPORT_FALSE.equals(frameSupport)) {
            return true;
        } else if (!ShiroConstant.FRAME_SHIRO_SUPPORT_FALSE.equals(frameSupport) && !ShiroConstant.FRAME_SHIRO_SUPPORT_TRUE.equals(frameSupport)) {
            throw new BusinessException(CommonException.Proxy.SHIRO_SUPPORT_ERROR);
        }
        String servletPath = httpServletRequest.getServletPath();
        // 先判断是否是系统配置的不需要校验的访问，例如登录请求，或者其他静态资源,这里要对请求参数中的url来做过滤处理
        Map<String, String> filterChainDefinition = shiroConfigProp.getfilterChainDefinitionMap();
        if (FilterMapUtil.wildcardMatchMapKey(filterChainDefinition, servletPath, IGNORE_IDENTIFICATION)) {
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
        String username = userVo.getUserName();
        String password = userVo.getPassWord();
        try {
            UsernamePasswordToken shiroToken = new UsernamePasswordToken(username, password);
            Subject subject = SecurityUtils.getSubject();
            subject.login(shiroToken);
            subject.hasRole(role);
        } catch (Exception e) {
            LOGGER.error("shiro 权限校验出错:{}", e.getMessage());
            throw new BusinessException(CommonException.Proxy.SHIRO_UNAUTHORIZED_EXCEPTIONT);
        }
        return true;
    }
}
