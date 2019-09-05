package com.lvmoney.customer.controller;/**
 * 描述:
 * 包名:com.lvmoney.customer.controller
 * 版本信息: 版本1.0
 * 日期:2019/8/9
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.common.utils.ResultUtil;
import com.lvmoney.common.utils.vo.ResultData;
import com.lvmoney.customer.server.UserServer;
import com.lvmoney.customer.vo.req.UserReqVo;
import com.lvmoney.customer.vo.resp.UserRespVo;
import com.lvmoney.k8s.feign.vo.resp.CommonVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/9 9:43
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    UserServer userServer;

    @GetMapping("/user/data")
    public ResultData<UserRespVo> userData(UserReqVo userReqVo) {
        CommonVo<UserRespVo> data = userServer.getUser(userReqVo);
        return ResultUtil.success(data.getData());
    }

    @GetMapping("/")
    public String index() {
        return "welcome";
    }


}
