/**
 * 描述:
 * 包名:com.lvmoney.shiro.service
 * 版本信息: 版本1.0
 * 日期:2019年1月8日  下午4:49:11
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.shiro.service;


import com.lvmoney.common.ro.UserRo;
import com.lvmoney.shiro.ro.ShiroDataRo;
import com.lvmoney.shiro.ro.ShiroServerRo;
import com.lvmoney.shiro.ro.ShiroUriRo;
import com.lvmoney.shiro.vo.ShiroDataVo;
import com.lvmoney.shiro.vo.ShiroUriVo;
import com.lvmoney.shiro.vo.SysServiceDataVo;
import com.lvmoney.shiro.vo.UserVo;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月8日 下午4:49:11
 */

public interface ShiroRedisService {
    ShiroDataVo getShiroData(String username);

    /**
     * @param token
     * @return 2019年1月8日下午4:58:52
     * @describe:通过
     * @author: lvmoney /xxxx科技有限公司
     */
    UserVo getUser(String token);

    void saveShiroData(ShiroDataRo shiroDataRo);

    /**
     * @describe: 把系统所有访问的接口数据保存到redis中
     * @param: [shiroServerRo]
     * @return: void
     * @author： lvmoney /xxxx科技有限公司
     * 2019/2/22
     */
    void saveShiroServerData(ShiroServerRo shiroServerRo);

    void deleteShiroServerData();

    List<SysServiceDataVo> getShiroServerAll();

    void saveShiroUriData(ShiroUriRo shiroUriRo);

    ShiroUriVo getShiroUriData(String uri);

    void saveToken2Redis(UserRo userRo);


}
