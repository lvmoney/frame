package com.lvmoney.oauth2.center.server.service.impl;

import com.lvmoney.common.exceptions.BusinessException;
import com.lvmoney.common.exceptions.CommonException;
import com.lvmoney.oauth2.center.server.exception.CustomOauthException;
import com.lvmoney.oauth2.center.server.exception.Oauth2Exception;
import com.lvmoney.oauth2.center.server.service.Db2RedisService;
import com.lvmoney.oauth2.center.server.vo.UserInfo;
import com.lvmoney.oauth2.center.server.ro.Oauth2UserRo;
import com.lvmoney.oauth2.center.server.service.Oauth2RedisService;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月16日 下午1:30:40
 */
@Service
public class RedisUserDetailsServiceImpl implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisUserDetailsServiceImpl.class);

    @Autowired
    Oauth2RedisService oauth2RedisService;
    @Autowired
    Db2RedisService db2RedisService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserInfo userInfo = oauth2RedisService.getOauth2UserVo(username);
        if (ObjectUtils.anyNotNull(userInfo)) {
            return userInfo;
        } else {
//            List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
//            grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_SUPER"));
//
//            Map<String, UserInfo> data = new HashMap<String, UserInfo>();
//            Long userid = 1L;
//            String userName = "zhangsan";
//            String password = "$2a$10$gcrWom7ubcRaVD1.6ZIrIeJP0mtPLH5J9V/.8Qth59lZ4B/5HMq96";
//            boolean enabled = true;
//
//            UserInfo oauth2UserVo = new UserInfo(userid, userName, password,
//                    enabled, true, true, enabled, grantedAuthorityList);
//            data.put(username, oauth2UserVo);
//            Oauth2UserRo oauth2UserRo = new Oauth2UserRo();
//            oauth2UserRo.setExpire(18000L);
//            oauth2UserRo.setData(data);
//            oauth2RedisService.userDetails2Redis(oauth2UserRo);
            db2RedisService.loadUserByUsername(username);
            userInfo = oauth2RedisService.getOauth2UserVo(username);
            if (userInfo == null) {
                throw new CustomOauthException(Oauth2Exception.Proxy.OAUTH2_CLIENT_DETAIL_NO_EXIST.getDescription());
            }
            return userInfo;
        }


    }
}
