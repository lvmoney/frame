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
     * @describe: 通过token从redis中获得用户信息
     * @param: [userVo]
     * @return: UserVo
     * @author： lvmoney /xxxx科技有限公司
     * 2019/2/1
     */
    UserVo getUser(UserVo userVo);

    /**
     * @describe: 记录操作日志
     * @param: [logVo]
     * @return: void
     * @author： lvmoney /xxxx科技有限公司
     * 2019/2/1
     */
    void saveLog(LogVo logVo);
}
