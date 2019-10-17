package com.lvmoney.k8s.config.client.controller;/**
 * 描述:
 * 包名:com.lvmoney.k8s.config.client.controller
 * 版本信息: 版本1.0
 * 日期:2019/9/12
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.common.util.JsonUtil;
import com.lvmoney.k8s.config.client.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/9/12 11:48
 */
@RefreshScope
@RestController
public class ClientController {
    @Value("${username}")
    String username;

    @Value("${password}")
    String password;
    @Autowired
    TestDao testDao;

    @RequestMapping(value = "/info")
    public String info() {
        return "(生产环境)用户名：" + username + ",密码：" + password;
    }

    @RequestMapping(value = "/db")
    public String test() {
        return JsonUtil.t2JsonString(testDao.selectById(1));
    }
}