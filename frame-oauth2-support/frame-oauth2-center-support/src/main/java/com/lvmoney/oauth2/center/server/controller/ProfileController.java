package com.lvmoney.oauth2.center.server.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lvmoney.common.exceptions.BusinessException;
import com.lvmoney.oauth2.center.server.exception.Oauth2Exception;
import com.lvmoney.oauth2.center.server.service.UserAccountService;
import com.lvmoney.oauth2.center.server.vo.UserAccount;
import com.lvmoney.oauth2.center.server.vo.resp.UserMeRespVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class ProfileController {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private static final Pattern authorizationPattern = Pattern.compile("^Bearer (?<token>[a-zA-Z0-9-._~+/]+)=*$");

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    TokenStore tokenStore;

    @ResponseBody
    @RequestMapping("/user/me")
    public UserMeRespVo info(@RequestParam(value = "access_token", required = false) String paramToken,
                             @RequestHeader(value = "Authorization", required = false) String headerToken,
                             @CookieValue(value = "access_token", required = false) String cookieToken) {
        UserMeRespVo userMeRespVo = new UserMeRespVo();
        try {
            String token = null;
            if (StringUtils.isNoneBlank(headerToken)) {
                Matcher matcher = authorizationPattern.matcher(headerToken);
                if (matcher.matches()) {
                    token = matcher.group("token");
                }
            }

            if (token == null && StringUtils.isNoneBlank(paramToken)) {
                token = paramToken;
            }

            if (token == null && StringUtils.isNoneBlank(cookieToken)) {
                token = cookieToken;
            }

            if (token != null) {
                OAuth2AccessToken auth2AccessToken = tokenStore.readAccessToken(token);
                if (auth2AccessToken.isExpired()) {
                    throw new BusinessException(Oauth2Exception.Proxy.OAUTH2_TOKEM_EXPIRED);
                }

                String username = auth2AccessToken.getAdditionalInformation().get("sub").toString();
                UserAccount userAccount = userAccountService.findByUsername(username);
                userMeRespVo.setUsername(username);
                if (StringUtils.isNotEmpty(userAccount.getGender())) {
                    userMeRespVo.setGender(userAccount.getGender());
                }
                if (StringUtils.isNotEmpty(userAccount.getNickName())) {
                    userMeRespVo.setNickName(userAccount.getNickName());
                }
                userMeRespVo.setGrantType(auth2AccessToken.getAdditionalInformation().get("grantType").toString());
                userMeRespVo.setAccountOpenCode(userAccount.getId());
//                userMeRespVo.setAuthorities(auth2AccessToken.getAdditionalInformation().get("authorities").toString());
                userMeRespVo.setStatus(1);
            } else {
                throw new BusinessException(Oauth2Exception.Proxy.OAUTH2_ACCESS_TOKEM_REQUIRED);
            }
        } catch (Exception e) {
            LOGGER.error("校验access_token报错:{}", e.getMessage());
            throw new BusinessException(Oauth2Exception.Proxy.OAUTH2_TOKEM_EXPIRED);

        }

        return userMeRespVo;
    }


    @GetMapping(value = {"", "/", "/user/profile"})
    public String profile(Principal principal,
                          Model model) {
        try {
            UserAccount userAccount = userAccountService.findByUsername(principal.getName());
            model.addAttribute("userAccount", userAccount);
        } catch (Exception e) {
            LOGGER.error("findByUsername exception", e);
        }

        return "profile";
    }

    @PostMapping("/user/profile")
    public String handleProfile(Principal principal,
                                @RequestParam(value = "nickName", required = false) String nickName,
                                @RequestParam(value = "avatarUrl", required = false) String avatarUrl,
                                @RequestParam(value = "email", required = false) String email,
                                @RequestParam(value = "mobile", required = false) String mobile,
                                @RequestParam(value = "province", required = false) String province,
                                @RequestParam(value = "city", required = false) String city,
                                @RequestParam(value = "address", required = false) String address,
                                @JsonFormat(pattern = "MM-dd-yyyy") @DateTimeFormat(pattern = "MM-dd-yyyy")
                                @RequestParam(value = "birthday", required = false) Date birthday,
                                Model model) {

        try {
            UserAccount userAccount = userAccountService.findByUsername(principal.getName());
            userAccount.setNickName(StringEscapeUtils.escapeHtml4(nickName));
            userAccount.setAvatarUrl(StringEscapeUtils.escapeHtml4(avatarUrl));
            userAccount.setEmail(StringEscapeUtils.escapeHtml4(email));
            userAccount.setMobile(StringEscapeUtils.escapeHtml4(mobile));
            userAccount.setProvince(StringEscapeUtils.escapeHtml4(province));
            userAccount.setCity(StringEscapeUtils.escapeHtml4(city));
            userAccount.setAddress(StringEscapeUtils.escapeHtml4(address));
            userAccount.setBirthday(birthday);
            userAccountService.updateById(userAccount);
        } catch (Exception e) {
            LOGGER.error("findByUsername exception", e);
        }

        return "redirect:/user/profile?success=update";
    }
}
