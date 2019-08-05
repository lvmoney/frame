package com.lvmoney.jwt.controller.test;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/3/25
 * Copyright xxxx科技有限公司
 */


import com.lvmoney.common.ro.UserRo;
import com.lvmoney.common.utils.JsonUtil;
import com.lvmoney.jwt.annotations.NotToken;
import com.lvmoney.jwt.utils.JwtUtil;
import com.lvmoney.jwt.vo.UserVo;
import com.lvmoney.redis.service.BaseRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@RestController
public class TestController {
    @Autowired
    BaseRedisService baseRedisService;
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @NotToken
    public void save(HttpServletRequest request, UserVo userVo) {
        UserRo userRo = new UserRo();
        userRo.setPassword(userVo.getPassword());
        userRo.setUsername(userVo.getUsername());
        userRo.setUserId(userVo.getUserId());
        String token = JwtUtil.getToken(userVo);
        userRo.setToken(token);
        baseRedisService.set(token, JsonUtil.t2JsonString(userRo),1800l);
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public void get(HttpServletRequest request, UserVo userVo) {
        String token = request.getHeader("token");// 从 http 请求头中取出
        baseRedisService.getString(token);
    }

}
