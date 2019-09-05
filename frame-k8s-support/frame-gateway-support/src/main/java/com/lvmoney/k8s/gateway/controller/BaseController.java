package com.lvmoney.k8s.gateway.controller;

//import com.lvmoney.common.exceptions.BusinessException;
//import com.lvmoney.common.exceptions.CommonException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    @RequestMapping("/defaultfallback")
    public void defaultfallback() {
        System.out.println("降级操作...");
//        throw new BusinessException(CommonException.Proxy.SERVER_IS_DOWNGRADE);
    }
}
