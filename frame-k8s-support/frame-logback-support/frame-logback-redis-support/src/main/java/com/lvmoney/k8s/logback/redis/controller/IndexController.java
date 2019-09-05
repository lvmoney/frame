package com.lvmoney.k8s.logback.redis.controller;/**
 * 描述:
 * 包名:com.lvmoney.k8s.logback.controller
 * 版本信息: 版本1.0
 * 日期:2019/8/22
 * Copyright XXXXXX科技有限公司
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/22 18:03
 */
@RestController
public class IndexController {
    private final static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/")
    public String hello() {
        logger.info("this is logback test");
        return "hello this is logback test";
    }
}
