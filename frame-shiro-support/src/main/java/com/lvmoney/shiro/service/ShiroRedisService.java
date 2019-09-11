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
    /**
     * 获得shiro数据
     *
     * @param username: 用户名
     * @throws
     * @return: com.lvmoney.shiro.vo.ShiroDataVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 21:06
     */
    ShiroDataVo getShiroData(String username);

    /**
     * 通过token获得用户
     *
     * @param token: token
     * @throws
     * @return: com.lvmoney.shiro.vo.UserVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 21:06
     */
    UserVo getUser(String token);

    /**
     * 存储shiro数据
     *
     * @param shiroDataRo: shiro数据实体
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 21:07
     */
    void saveShiroData(ShiroDataRo shiroDataRo);

    /**
     * 把系统所有访问的接口数据保存到redis中
     *
     * @param shiroServerRo: 所有的访问接口数据
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 21:07
     */
    void saveShiroServerData(ShiroServerRo shiroServerRo);

    /**
     * 删除市容数据
     *
     * @param serverName: 服务名称
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 21:07
     */
    void deleteShiroServerData(String serverName);

    /**
     * 获得所有的市容数据
     *
     * @param serverName: 服务名称
     * @throws
     * @return: java.util.List<com.lvmoney.shiro.vo.SysServiceDataVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 21:08
     */
    List<SysServiceDataVo> getShiroServerAll(String serverName);

    /**
     * 存储shiro url数据
     *
     * @param shiroUriRo:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 21:08
     */
    void saveShiroUriData(ShiroUriRo shiroUriRo);

    /**
     * 通过uri获得shiro数据
     *
     * @param uri:
     * @throws
     * @return: com.lvmoney.shiro.vo.ShiroUriVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 21:08
     */
    ShiroUriVo getShiroUriData(String uri);

    /**
     * 存储用户数据
     *
     * @param userRo: 用户数据
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 21:09
     */
    void saveToken2Redis(UserRo userRo);


}
