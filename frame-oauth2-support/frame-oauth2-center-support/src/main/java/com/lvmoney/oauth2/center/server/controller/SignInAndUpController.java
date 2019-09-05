package com.lvmoney.oauth2.center.server.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SignInAndUpController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/signIn")
    public String signIn(@RequestParam(value = "error", required = false) String error,
                         Model model) {
        if (StringUtils.isNotEmpty(error)) {
            model.addAttribute("error", error);
        }
        return "signIn";
    }

}
