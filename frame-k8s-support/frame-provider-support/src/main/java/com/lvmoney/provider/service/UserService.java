package com.lvmoney.provider.service;/**
 * 描述:
 * 包名:com.lvmoney.provider.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/8/16
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.provider.vo.req.UserReqVo;
import com.lvmoney.provider.vo.resp.UserRespVo;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/16 11:36
 */
public interface UserService {
    UserRespVo getUser(UserReqVo userReqVo);
}
