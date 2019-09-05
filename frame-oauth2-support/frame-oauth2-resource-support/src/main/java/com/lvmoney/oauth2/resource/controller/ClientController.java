package com.lvmoney.oauth2.resource.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月16日 下午1:34:08
 */
@RestController
public class ClientController {

    @GetMapping("/user")
    public String getUser() {
        return "hello";
    }

    @GetMapping("/test")
    public String gettest() {
        return "this is test";
    }
}
