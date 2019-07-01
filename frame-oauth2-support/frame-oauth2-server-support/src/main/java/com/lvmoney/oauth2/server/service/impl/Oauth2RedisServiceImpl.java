package com.lvmoney.oauth2.server.service.impl;

import com.lvmoney.common.exceptions.BusinessException;
import com.lvmoney.common.exceptions.CommonException;
import com.lvmoney.oauth2.server.constant.Oauth2ServerConstant;
import com.lvmoney.oauth2.server.ro.Oauth2ClientRo;
import com.lvmoney.oauth2.server.ro.Oauth2UserRo;
import com.lvmoney.oauth2.server.service.Oauth2RedisService;
import com.lvmoney.oauth2.server.vo.Oauth2ClientVo;
import com.lvmoney.oauth2.server.vo.Oauth2UserVo;
import com.lvmoney.redis.service.BaseRedisService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.stereotype.Service;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月18日 上午11:28:35
 */
@Service
public class Oauth2RedisServiceImpl implements Oauth2RedisService {
    @Autowired
    BaseRedisService baseRedisService;

    @Override
    public void FrameUserDetails2Redis(Oauth2UserRo oauth2UserRo) {
        baseRedisService.addMap(Oauth2ServerConstant.REDIS_FRAME_USER_DETAILS_NAME, oauth2UserRo.getData(), oauth2UserRo.getExpire());
    }

    public User getOauth2UserVo(String username) {
        Oauth2UserVo rOauth2UserVo = (Oauth2UserVo) baseRedisService.getValueByMapKey(Oauth2ServerConstant.REDIS_FRAME_USER_DETAILS_NAME, username);
        if (rOauth2UserVo == null) {
            throw new BusinessException(CommonException.Proxy.OAUTH2_USER_DETAIL_NO_EXIST);
        }
        username = rOauth2UserVo.getUsername();
        String password = Oauth2ServerConstant.OAUTH2_USER_SECRET_PREFIX + rOauth2UserVo.getPassword();
        List<GrantedAuthority> grantedAuthoritys = rOauth2UserVo.getGrantedAuthoritys();
        return new User(username, password, grantedAuthoritys);
    }

    @Override
    public ClientDetails getClientDetailsByClientId(String clientId) {
        Oauth2ClientVo oauth2ClientVo = (Oauth2ClientVo) baseRedisService.getValueByMapKey(Oauth2ServerConstant.REDIS_FRAME_CLIENT_DETAILS_NAME, clientId);
        if (oauth2ClientVo == null) {
            throw new BusinessException(CommonException.Proxy.OAUTH2_CLIENT_DETAIL_NO_EXIST);
        }
        return oauth2ClientVo.getClientDetails();
    }

    @Override
    public void FrameClientDetails2Redis(Oauth2ClientRo oauth2ClientRo) {
        baseRedisService.addMap(Oauth2ServerConstant.REDIS_FRAME_CLIENT_DETAILS_NAME, oauth2ClientRo.getData(), oauth2ClientRo.getExpire());
    }

}
