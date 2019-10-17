package com.lvmoney.log.controller;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/1/31
 * Copyright xxxx科技有限公司
 *
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */


import com.lvmoney.log.annotation.ControllerLog;
import com.lvmoney.log.constant.LogType;
import com.lvmoney.log.vo.ThrowableVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@RestController
public class UserController {
    @RequestMapping(value = "/userlist")
    @ControllerLog(descrption = "查询用户信息", actionType = LogType.LOG_OTHER)
    public ThrowableVo getUserList(String id) throws Exception {
        ThrowableVo throwableVo = new ThrowableVo();
        throwableVo.setCode(10000);
        return throwableVo;
    }
}
