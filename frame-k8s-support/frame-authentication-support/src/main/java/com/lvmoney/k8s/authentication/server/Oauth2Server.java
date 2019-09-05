package com.lvmoney.k8s.authentication.server;/**
 * 描述:
 * 包名:com.lvmoney.k8s.feign.server
 * 版本信息: 版本1.0
 * 日期:2019/8/11
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.k8s.feign.config.FeignConfig;
import com.lvmoney.k8s.authentication.vo.Oauth2Token;
import com.lvmoney.k8s.authentication.vo.Oauth2TokenCheck;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/11 10:33
 */
@FeignClient(name = "testServer", url = "${rpc.server.oauth2}", configuration = FeignConfig.class)
public interface Oauth2Server {

    @PostMapping(value = "/oauth/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    Oauth2Token oauth2TokenAuthoriztionCode(@RequestParam(value = "client_id") String clientId,
                                            @RequestParam(value = "client_secret") String clientSecret,
                                            @RequestParam(value = "grant_type") String grantType,
                                            @RequestParam(value = "redirect_uri") String redirectUri,
                                            @RequestParam(value = "code") String code);


    @PostMapping(value = "/oauth/check_token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    Oauth2TokenCheck oauth2CheckToken(@RequestParam(value = "token") String token);
}
