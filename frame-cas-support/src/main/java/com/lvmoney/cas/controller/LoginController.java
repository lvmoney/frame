/**
 * 描述:
 * 包名:com.lvmoney.cas.controller
 * 版本信息: 版本1.0
 * 日期:2019年1月15日  上午9:21:55
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.cas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lvmoney.cas.vo.UserVo;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月15日 上午9:21:55
 */
@RestController
@RequestMapping("test")
public class LoginController {
    @RequestMapping(value = "login", method = RequestMethod.GET)
    private boolean login(UserVo userVo) {
        String test = "123";
        if (test.equals(userVo.getUername())) {
            return true;
        } else {
            return false;
        }
    }
}
