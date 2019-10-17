package com.lvmoney.log.controller;

import com.lvmoney.log.annotation.ControllerLog;
import com.lvmoney.log.annotation.NotLog;
import com.lvmoney.log.constant.LogType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@RestController
@RequestMapping("/log")
public class LogController {

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ControllerLog(descrption = "广告管理图片上传", actionType = LogType.LOG_ADD)
    @NotLog(required = true)
    public void saveFile(HttpServletRequest request) {
        System.out.println("log test");
    }

}
