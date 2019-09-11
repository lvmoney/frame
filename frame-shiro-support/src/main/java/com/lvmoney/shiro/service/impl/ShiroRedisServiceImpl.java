/**
 * 描述:
 * 包名:com.lvmoney.shiro.service.impl
 * 版本信息: 版本1.0
 * 日期:2019年1月8日  下午5:30:27
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.shiro.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lvmoney.common.constant.CommonConstant;
import com.lvmoney.common.ro.UserRo;
import com.lvmoney.common.utils.JsonUtil;
import com.lvmoney.redis.service.BaseRedisService;
import com.lvmoney.shiro.constant.ShiroConstant;
import com.lvmoney.shiro.ro.ShiroDataRo;
import com.lvmoney.shiro.ro.ShiroServerRo;
import com.lvmoney.shiro.ro.ShiroUriRo;
import com.lvmoney.shiro.service.ShiroRedisService;
import com.lvmoney.shiro.vo.ShiroDataVo;
import com.lvmoney.shiro.vo.ShiroUriVo;
import com.lvmoney.shiro.vo.SysServiceDataVo;
import com.lvmoney.shiro.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月8日 下午5:30:27
 */
@Service("frameShiroRedisService")
public class ShiroRedisServiceImpl implements ShiroRedisService {
    @Autowired
    BaseRedisService baseRedisService;
    /**
     * 登录用户相关数据失效时间
     */
    @Value("${shiro.data.expire:18000}")
    String expire;
    /**
     * 系统uri失效时间，方便权限配置，应该为永远
     */
    @Value("${shiro.auth.expire:0}")
    String expireUri;

    @Override
    public UserVo getUser(String token) {
        UserVo result = new UserVo();
        String shiroString = baseRedisService.getString(token).toString();
        if (StringUtils.isBlank(shiroString)) {
            return null;
        }
        UserRo userRo = JSON.parseObject(shiroString, new TypeReference<UserRo>() {
        });
        if (userRo != null) {
            result.setUserId(userRo.getUserId());
            result.setPassWord(userRo.getPassword());
            result.setUserName(userRo.getUsername());
            return result;
        }
        return null;
    }

    @Override
    public void saveShiroData(ShiroDataRo shiroDataRo) {
        long ex = shiroDataRo.getExpire();
        if (ex > 0) {
            baseRedisService.set(shiroDataRo.getUsername(), JsonUtil.t2JsonString(shiroDataRo), shiroDataRo.getExpire());
        } else {
            baseRedisService.set(shiroDataRo.getUsername(), JsonUtil.t2JsonString(shiroDataRo), Long.parseLong(expire));
        }
    }

    @Override
    public void saveShiroServerData(ShiroServerRo shiroServerRo) {
        long ex = shiroServerRo.getExpire();
        if (ex > 0) {
            baseRedisService.addMap(ShiroConstant.SYS_SHIRO_RES, shiroServerRo.getData(), shiroServerRo.getExpire());
        } else if (Long.parseLong(expireUri) > 0) {
            baseRedisService.addMap(ShiroConstant.SYS_SHIRO_RES, shiroServerRo.getData(), Long.parseLong(expireUri));
        } else {
            baseRedisService.addMap(ShiroConstant.SYS_SHIRO_RES, shiroServerRo.getData(), null);
        }
    }

    @Override
    public void deleteShiroServerData(String serverName) {
        baseRedisService.deleteValueByMapKey(ShiroConstant.SYS_SHIRO_RES, serverName);
    }

    @Override
    public List<SysServiceDataVo> getShiroServerAll(String serverName) {
        Object obj = baseRedisService.getValueByMapKey(ShiroConstant.SYS_SHIRO_RES, serverName);
        List<SysServiceDataVo> sysServiceDataVos = JSON.parseObject(obj.toString(), new TypeReference<List<SysServiceDataVo>>() {
        });
        return sysServiceDataVos;
    }

    @Override
    public void saveShiroUriData(ShiroUriRo shiroUriRo) {
        Map<String, String> map = new HashMap<>(CommonConstant.MAP_DEFAULT_SIZE);
        map.put(shiroUriRo.getUri(), JsonUtil.t2JsonString(shiroUriRo.getShiroUriVo()));
        baseRedisService.addMap(ShiroConstant.SYS_SHIRO_URI, map, shiroUriRo.getExpire());
    }

    @Override
    public ShiroUriVo getShiroUriData(String uri) {
        String str = (String) baseRedisService.getValueByMapKey(ShiroConstant.SYS_SHIRO_URI, uri);
        ShiroUriVo result = JSONObject.parseObject(str, ShiroUriVo.class);
        return result;
    }

    @Override
    public void saveToken2Redis(UserRo userRo) {
        baseRedisService.set(userRo.getToken(), JsonUtil.t2JsonString(userRo), userRo.getExpire());
    }

    @Override
    public ShiroDataVo getShiroData(String username) {
        ShiroDataVo result = new ShiroDataVo();
        String shiroString = baseRedisService.getString(username).toString();
        if (StringUtils.isBlank(shiroString)) {
            return null;
        }
        ShiroDataRo shiroDataRo = JSON.parseObject(shiroString, new TypeReference<ShiroDataRo>() {
        });
        if (shiroDataRo != null) {
            result.setPermissions(shiroDataRo.getPermissions());
            result.setRoles(shiroDataRo.getRoles());
            result.setExpire(shiroDataRo.getExpire());
            return result;
        }
        return null;
    }

}
