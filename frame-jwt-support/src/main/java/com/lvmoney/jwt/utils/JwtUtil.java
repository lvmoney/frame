/**
 * 描述:
 * 包名:com.lvmoney.jwt.utils
 * 版本信息: 版本1.0
 * 日期:2019年1月4日  下午2:30:43
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.jwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lvmoney.jwt.vo.UserVo;

import java.time.Instant;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月4日 下午2:30:43
 */

public class JwtUtil {
    public static String getToken(UserVo user) {
        String token = "";
        Long millisecond = Instant.now().toEpochMilli();
        token = JWT.create().withAudience(user.getUserId() + millisecond).sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }

    public static void main(String[] args) {
        UserVo userVo = new UserVo();
        userVo.setUserId("1234567");
        userVo.setPassword("1234121");
        userVo.setUsername("test");

        String token = getToken(userVo);
        System.out.println(token);
        String userId = JWT.decode(token).getAudience().get(0);
        System.out.println(userId);
    }
}
