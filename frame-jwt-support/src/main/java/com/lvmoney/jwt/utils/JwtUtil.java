/**
 * 描述:
 * 包名:com.lvmoney.jwt.utils
 * 版本信息: 版本1.0
 * 日期:2019年1月4日  下午2:30:43
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.jwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.lvmoney.common.constant.CommonConstant;
import com.lvmoney.common.utils.SnowflakeIdFactoryUtil;
import com.lvmoney.jwt.constant.JwtConstant;
import com.lvmoney.jwt.vo.UserVo;

import java.time.Instant;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月4日 下午2:30:43
 */

public class JwtUtil {
    /**
     * @describe:获得用户的token，为了安全redis中不保存用户密码，这里默认把用户密码改为一个雪花算法的值，每一次都不同 其实这里也能不修改，如果用户传的password是加过密的就没有问题，就怕有人传的是明文，那么明文就直接丢到redis了
     * @param: [user]
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/5 10:47
     */
    public static String getToken(UserVo user) {
        String token = "";
        Long millisecond = Instant.now().toEpochMilli();
        SnowflakeIdFactoryUtil snowflakeIdFactoryUtil = new SnowflakeIdFactoryUtil(1, 2);
        String password = String.valueOf(snowflakeIdFactoryUtil.nextId());
        token = JWT.create().withAudience(user.getUserId() + millisecond).sign(Algorithm.HMAC256(password));
        token = CommonConstant.TOKEM_JWT_PREFIX + token;

        return token;
    }

    public static String getUserId(String token) {
        String verifyToken = token.startsWith(CommonConstant.TOKEM_JWT_PREFIX) ? token.replaceFirst(CommonConstant.TOKEM_JWT_PREFIX, "") : token;
        String userId = JWT.decode(verifyToken).getAudience().get(0);
        return userId;
    }

    public static void main(String[] args) {
        UserVo userVo = new UserVo();
        SnowflakeIdFactoryUtil snowflakeIdFactoryUtil = new SnowflakeIdFactoryUtil(1, 2);

        userVo.setUserId("1234567");
        userVo.setPassword(String.valueOf(snowflakeIdFactoryUtil.nextId()));
        userVo.setUsername("test");


        String token = getToken(userVo);
        System.out.println(token);
        String userId = getUserId(token);
        System.out.println(userId);
    }
}
