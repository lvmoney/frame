package com.lvmoney.oauth2.center.server.controller;

import com.lvmoney.captcha.service.CaptchaService;
import com.lvmoney.common.exceptions.BusinessException;
import com.lvmoney.oauth2.center.server.exception.Oauth2Exception;
import com.lvmoney.oauth2.center.server.service.UserAccountService;
import com.lvmoney.oauth2.center.server.utils.PasswordUtil;
import com.lvmoney.oauth2.center.server.vo.*;
import com.lvmoney.oauth2.center.server.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    private Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    ClientDetailsService clientDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CaptchaService captchaService;

    @Autowired
    RoleService roleService;

    @GetMapping("/signIn")
    public String signIn(@RequestParam(value = "error", required = false) String error,
                         Model model) {
        if (StringUtils.isNotEmpty(error)) {
            model.addAttribute("error", error);
        }
        return "signIn";
    }

    @GetMapping("/signUp")
    public String signUp(HttpServletRequest request,
                         @RequestParam(value = "error", required = false) String error,
                         Model model) {
        if (StringUtils.isNotEmpty(error)) {
            model.addAttribute("error", error);
        }
        return "signUp";
    }

    @ResponseBody
    @PostMapping("/signUp")
    public void handleSignUp(HttpServletRequest request,
                             @RequestParam(value = "verificationCode") String verificationCode,
                             @RequestParam(value = "graphId") String graphId,
                             @RequestParam(value = "username") String username,
                             @RequestParam(value = "password") String password,
                             @RequestParam(value = "confirmPassword") String confirmPassword) {
        username = StringUtils.trimToEmpty(username).toLowerCase();
        password = StringUtils.trimToEmpty(password);
        if (PasswordUtil.check(password) < 4) {
            throw new BusinessException(Oauth2Exception.Proxy.PASSWORD_NOT_STANDARD);
        }
        String captcha = captchaService.getValidate(graphId).getValue();
        if (!StringUtils.equalsIgnoreCase(verificationCode, captcha)) {
            throw new BusinessException(Oauth2Exception.Proxy.VERIFICATION_ERROR);
        }
        UserAccount userAccount = new UserAccount();
        Role userRole = roleService.findByRoleName(RoleEnum.ROLE_USER.name());
        userAccount.getRoles().add(userRole);
        userAccount.setUsername(StringEscapeUtils.escapeHtml4(username));
        userAccount.setPassword(passwordEncoder.encode(password));
        try {
            userAccountService.create(userAccount);
            //删除验证码
            captchaService.deleteValidate(graphId);
        } catch (Exception e) {
            log.error("创建用户报错:{}", e.getMessage());
            throw new BusinessException(Oauth2Exception.Proxy.USER_EXSIT);
        }
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping("/oauth/signUp")
    public void handleOauthSignUp(@RequestParam(value = "client_id") String clientId,
                                  @RequestParam(value = "client_secret") String clientSecret,
                                  @RequestParam(value = "username") String username,
                                  @RequestParam(value = "password") String password) {
        username = StringUtils.trimToEmpty(username).toLowerCase();
        password = StringUtils.trimToEmpty(password);
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        if (clientDetails == null || passwordEncoder.matches(password, clientDetails.getClientSecret())) {
            throw new BusinessException(Oauth2Exception.Proxy.CLIENT_DETAIL_NOT_EXSIT);
        }
        UserAccount userAccount = new UserAccount();
        userAccount.setClientId(clientId);
        Role userRole = roleService.findByRoleName(RoleEnum.ROLE_USER.name());
        userAccount.getRoles().add(userRole);
        userAccount.getRoles().add(userRole);
        userAccount.setUsername(username);
        userAccount.setPassword(passwordEncoder.encode(password));
        try {
            userAccountService.create(userAccount);
        } catch (Exception e) {
            log.error("创建用户报错:{}", e.getMessage());
            throw new BusinessException(Oauth2Exception.Proxy.USER_EXSIT);
        }
    }
}
