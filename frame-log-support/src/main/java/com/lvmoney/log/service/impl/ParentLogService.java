package com.lvmoney.log.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/1/31
 * Copyright xxxx科技有限公司
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lvmoney.common.ro.UserRo;
import com.lvmoney.log.service.LogService;
import com.lvmoney.log.vo.LogVo;
import com.lvmoney.log.vo.UserVo;
import com.lvmoney.redis.service.BaseRedisService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public abstract class ParentLogService implements LogService {
    @Autowired
    BaseRedisService baseRedisService;

    @Override
    public UserVo getUser(UserVo userVo) {
        String token = userVo.getToken();
        String jwtString = baseRedisService.getString(token);
        if (jwtString != null) {
            UserRo userRo = JSON.parseObject(jwtString, new TypeReference<UserRo>() {
            });
            if (userRo != null) {
                userVo.setUsername(userRo.getUsername());
                userVo.setUserId(userRo.getUserId());
                return userVo;
            }
        }
        return null;
    }

    @Override
    public void saveLog(LogVo logVo) {
        //TODO 把日志保存到数据库，暂未实现
        System.out.println(logVo);
    }
}
