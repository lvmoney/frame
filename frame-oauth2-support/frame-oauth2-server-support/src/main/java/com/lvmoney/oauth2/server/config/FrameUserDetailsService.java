package com.lvmoney.oauth2.server.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.lvmoney.common.exceptions.BusinessException;
import com.lvmoney.common.exceptions.CommonException;
import com.lvmoney.oauth2.server.ro.Oauth2UserRo;
import com.lvmoney.oauth2.server.service.Oauth2RedisService;
import com.lvmoney.oauth2.server.vo.Oauth2UserVo;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月16日 下午1:30:40
 */
@Service
public class FrameUserDetailsService implements UserDetailsService {
    private final static Logger logger = LoggerFactory.getLogger(FrameUserDetailsService.class);

    @Autowired
    Oauth2RedisService oauth2RedisService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        //  这个地方通过username从redisz中获取正确的用户信息，包括密码和权限等。
//        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
//        grantedAuthorityList.add(new FrameGrantedAuthority("MY_ROLE1", "MY_MENU1"));
//        grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
//        grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        return new User(username, "{noop}123456", grantedAuthorityList);

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new FrameGrantedAuthority("MY_ROLE1", "MY_MENU1"));
        grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        Map<String, Oauth2UserVo> data = new HashMap<String, Oauth2UserVo>();

        Oauth2UserVo oauth2UserVo = new Oauth2UserVo();
        oauth2UserVo.setGrantedAuthoritys(grantedAuthorityList);
        oauth2UserVo.setPassword("123456");
        oauth2UserVo.setUsername(username);
        data.put(username, oauth2UserVo);
        Oauth2UserRo oauth2UserRo = new Oauth2UserRo();
        oauth2UserRo.setExpire(18000l);
        oauth2UserRo.setData(data);
        oauth2RedisService.FrameUserDetails2Redis(oauth2UserRo);
        try {
            User user = oauth2RedisService.getOauth2UserVo(username);
            if (user == null) {
                throw new BusinessException(CommonException.Proxy.OAUTH2_USER_DETAIL_NO_EXIST);
            }
            return user;
        } catch (Exception e) {
            logger.error("从redis中获得oauth2user的信息报错:{}", e.getMessage());
            throw new BusinessException(CommonException.Proxy.OAUTH2_USER_DETAIL_ERROR);
        }
    }
}
