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
    void saveToken2Redis(UserRo userRo);

    UserVo getUserVo(String token);

    boolean checkToken(String token);
}
