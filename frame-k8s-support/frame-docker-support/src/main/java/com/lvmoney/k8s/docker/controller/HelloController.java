package com.lvmoney.k8s.docker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HelloController {

    @RequestMapping("/test")
    public String printDate() {
        return "this is v1";
    }


}