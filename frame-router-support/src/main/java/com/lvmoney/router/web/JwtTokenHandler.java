/**
 * 描述:
 * 包名:com.lvmoney.router.web
 * 版本信息: 版本1.0
 * 日期:2019年1月9日  下午4:35:16
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.router.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.lvmoney.common.exception.BusinessException;
import com.lvmoney.common.exception.CommonException;
import com.lvmoney.jwt.constant.JwtConstant;
import com.lvmoney.jwt.service.JwtRedisService;
import com.lvmoney.jwt.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月9日 下午4:35:16
 */
@Component
public class JwtTokenHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenHandler.class);
    @Autowired
    JwtRedisService jwtRedisService;
    @Value("${frame.jwt.support:false}")
    String frameSupport;

    public boolean tokenValidated(HttpServletRequest httpServletRequest) {
        if (JwtConstant.FRAME_JWT_SUPPORT_FALSE.equals(frameSupport)) {
            return true;
        } else if (!JwtConstant.FRAME_JWT_SUPPORT_FALSE.equals(frameSupport) && !JwtConstant.FRAME_JWT_SUPPORT_TRUE.equals(frameSupport)) {
            throw new BusinessException(CommonException.Proxy.SHIRO_SUPPORT_ERROR);
        }
        // 从 http 请求头中取出
        String token = httpServletRequest.getHeader("token");
        if (token == null) {
            throw new BusinessException(CommonException.Proxy.TOKEN_IS_REQUIRED);
        }
        // 获取 token 中的 user id
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new BusinessException(CommonException.Proxy.TOKEN_USER_ID_ERROR);
        }
        UserVo userVo = jwtRedisService.getUserVo(token);
        if (userVo == null) {
            throw new BusinessException(CommonException.Proxy.TOKEN_USER_NOT_EXSIT);
        }
        if (!userId.equals(userVo.getUserId())) {
            throw new BusinessException(CommonException.Proxy.TOKEN_USER_NOT_EXSIT);
        }
        // 验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(userVo.getPassword())).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            LOGGER.error("token 校验出错:{}", e.getMessage());
            throw new BusinessException(CommonException.Proxy.TOKEN_VERIFY_ERROR);
        }
        return true;
    }
}
