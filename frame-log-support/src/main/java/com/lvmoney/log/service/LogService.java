package com.lvmoney.log.service;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/1/31
 * Copyright xxxx科技有限公司
 */


import com.lvmoney.log.vo.LogVo;
import com.lvmoney.log.vo.UserVo;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public interface LogService {
    /**
     * 通过token从redis中获得用户信息
     *
     * @param userVo: 用户实体
     * @throws
     * @return: com.lvmoney.log.vo.UserVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:53
     */
    UserVo getUser(UserVo userVo);

    /**
     * 记录操作日志
     *
     * @param logVo: 日志数据实体
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:53
     */
    void saveLog(LogVo logVo);
}
