package com.lvmoney.oauth2.server.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @describe：
 * @author: lvmoney/xxxx科技有限公司
 * @version:v1.0 2019/2/28 10:05
 */
@Controller
public class LoginController {

    @Autowired
    @Qualifier("consumerTokenServices")
    ConsumerTokenServices tokenServices;

    @ApiOperation(value = "转发到登录页面")
    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("ftl/login");
    }

    @ApiOperation(value = "获取登录人的授权信息")
    @GetMapping("/get")
    @Secured("ROLE_ADMIN")
    @ResponseBody
    public Authentication get() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @ApiOperation(value = "退出登录", notes = "通过移除token来达到退出登录的效果", httpMethod = "GET")
    @ApiImplicitParam(name = "accessToken", value = "用来认证的access_token", required = true, dataType = "String")
    @GetMapping("/logout")
    public ModelAndView logout(String accessToken) {
        tokenServices.revokeToken(accessToken);
        return new ModelAndView("ftl/login");
    }

}
