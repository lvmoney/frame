package com.lvmoney.k8s.resource.controller;/**
 * 描述:
 * 包名:com.lvmoney.k8s.resource.controller
 * 版本信息: 版本1.0
 * 日期:2019/8/9
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.common.utils.ResultUtil;
import com.lvmoney.common.utils.vo.ResultData;
import com.lvmoney.k8s.resource.resp.UserInfoVoResp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/9 16:33
 */
@RestController
@RequestMapping("resource")
public class ResourceController {
    @GetMapping("/user")
    public ResultData<UserInfoVoResp> getUser() {
        UserInfoVoResp userInfoVoResp = new UserInfoVoResp();
        userInfoVoResp.setName("lvmoney");
        userInfoVoResp.setSex("男");
        return ResultUtil.success(userInfoVoResp);
    }
}
