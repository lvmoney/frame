package com.lvmoney.log.controller;

import com.lvmoney.log.annotations.ControllerLog;
import com.lvmoney.log.annotations.NotLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/log")
public class LogController {

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ControllerLog(descrption = "广告管理图片上传", actionType = "4")
    @NotLog(required = true)
    public void saveFile(HttpServletRequest request) {
        System.out.println("log test");
    }

}
