package com.lvmoney.cloud.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@RestController
public class IndexController {

    @RequestMapping("/hello")
    public String hello(String name) {
        return "hi " + name;
    }

    @RequestMapping("/timeout")
    public String timeout() {
        try {
            //睡5秒，网关Hystrix3秒超时，会触发熔断降级操作
            Thread.sleep(6000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "timeout";
    }
}
