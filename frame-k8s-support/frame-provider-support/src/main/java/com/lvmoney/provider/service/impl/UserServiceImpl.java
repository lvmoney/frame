package com.lvmoney.provider.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.provider.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/8/16
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.provider.service.UserService;
import com.lvmoney.provider.vo.req.UserReqVo;
import com.lvmoney.provider.vo.resp.UserRespVo;
import org.springframework.stereotype.Service;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/16 11:31
 */
@Service
public class UserServiceImpl implements UserService {
    public UserRespVo getUser(UserReqVo userReqVo) {
        if (userReqVo.getUsername().equals("lvmoney")) {
            UserRespVo userRespVo = new UserRespVo();
            userRespVo.setAddress("成都武侯");
            userRespVo.setAge(29);
            userRespVo.setSex("男");
            return userRespVo;
        } else if (userReqVo.getUsername().equals("cx")) {
            UserRespVo userRespVo = new UserRespVo();
            userRespVo.setAddress("成都龙泉");
            userRespVo.setAge(23);
            userRespVo.setSex("女");
            return userRespVo;
        } else {
            UserRespVo userRespVo = new UserRespVo();
            userRespVo.setAddress("中国台湾省");
            userRespVo.setAge(40);
            userRespVo.setSex("男");
            return userRespVo;
        }
    }
}
