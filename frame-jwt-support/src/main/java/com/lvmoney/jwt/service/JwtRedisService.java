/**
 * 描述:
 * 包名:com.lvmoney.jwt.service
 * 版本信息: 版本1.0
 * 日期:2019年1月4日  下午2:38:07
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.jwt.service;

import com.lvmoney.common.ro.UserRo;
import com.lvmoney.jwt.vo.UserVo;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月4日 下午2:38:07
 */

public interface JwtRedisService {
    /**
     * 存储token到redis
     *
     * @param userRo: 用户信息
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:48
     */
    void saveToken2Redis(UserRo userRo);

    /**
     * 通过token获得用户信息
     *
     * @param token:
     * @throws
     * @return: com.lvmoney.jwt.vo.UserVo 用户信息
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:48
     */
    UserVo getUserVo(String token);

    /**
     * 校验token是否存在
     *
     * @param token: token
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:49
     */
    boolean checkToken(String token);
}
