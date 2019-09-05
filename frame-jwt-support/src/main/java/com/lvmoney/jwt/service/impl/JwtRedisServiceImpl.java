/**
 * 描述:
 * 包名:com.lvmoney.jwt.service.impl
 * 版本信息: 版本1.0
 * 日期:2019年1月4日  下午2:55:23
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.jwt.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.lvmoney.common.exceptions.BusinessException;
import com.lvmoney.common.exceptions.CommonException;
import com.lvmoney.common.ro.UserRo;
import com.lvmoney.common.utils.JsonUtil;
import com.lvmoney.jwt.service.JwtRedisService;
import com.lvmoney.jwt.vo.UserVo;
import com.lvmoney.redis.service.BaseRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月4日 下午2:55:23
 */
@Service("frameJwtRedisService")
public class JwtRedisServiceImpl implements JwtRedisService {
    @Autowired
    BaseRedisService baseRedisService;
    @Value("${jwt.server.expire:18000}")
    String expire;

    @Override
    public void saveToken2Redis(UserRo userRo) {
        if (userRo.getExpire() > 0l) {
            baseRedisService.set(userRo.getToken(), JsonUtil.t2JsonString(userRo), userRo.getExpire());
        } else {
            baseRedisService.set(userRo.getToken(), JsonUtil.t2JsonString(userRo), Long.parseLong(expire));
        }
    }

    @Override
    public UserVo getUserVo(String token) {
        String jwtString = baseRedisService.getString(token).toString();
        UserVo userVo = new UserVo();
        if (jwtString != null) {
            UserRo userRo = JSON.parseObject(jwtString, new TypeReference<UserRo>() {
            });
            if (userRo != null) {
                userVo.setUserId(userRo.getUserId());
                userVo.setPassword(userRo.getPassword());
                userVo.setUsername(userRo.getUsername());
                return userVo;
            }
        }
        return null;
    }

    @Override
    public boolean checkToken(String token) {
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new BusinessException(CommonException.Proxy.TOKEN_USER_ID_ERROR);
        }
        UserVo userVo = getUserVo(token);
        if (userVo == null) {
            throw new BusinessException(CommonException.Proxy.TOKEN_USER_NOT_EXSIT);
        }
        if (!userId.startsWith(userVo.getUserId())) {
            throw new BusinessException(CommonException.Proxy.TOKEN_USER_NOT_EXSIT);
        }
        // 验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(userVo.getPassword())).build();
        try {
            jwtVerifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }

    }

}
