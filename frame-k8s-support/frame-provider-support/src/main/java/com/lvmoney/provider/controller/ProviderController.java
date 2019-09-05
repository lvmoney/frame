package com.lvmoney.provider.controller;/**
 * 描述:
 * 包名:com.lvmoney.provider.controller
 * 版本信息: 版本1.0
 * 日期:2019/8/16
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.common.utils.ResultUtil;
import com.lvmoney.common.utils.vo.ResultData;
import com.lvmoney.provider.service.UserService;
import com.lvmoney.provider.vo.req.UserReqVo;
import com.lvmoney.provider.vo.resp.UserRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/16 11:21
 */
@RestController
@RequestMapping("/user")
public class ProviderController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/data")
    public ResultData<UserRespVo> getUser(UserReqVo userReqVo) {
        return ResultUtil.success(userService.getUser(userReqVo));
    }
}
