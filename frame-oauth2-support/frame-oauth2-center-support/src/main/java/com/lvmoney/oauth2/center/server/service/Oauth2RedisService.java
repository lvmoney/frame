package com.lvmoney.oauth2.center.server.service;

import com.lvmoney.oauth2.center.server.ro.AuthorizationCodeRo;
import com.lvmoney.oauth2.center.server.vo.AuthorizationVo;
import com.lvmoney.oauth2.center.server.vo.OauthClient;
import com.lvmoney.oauth2.center.server.ro.Oauth2ClientRo;
import com.lvmoney.oauth2.center.server.vo.UserInfo;
import com.lvmoney.oauth2.center.server.ro.Oauth2ClientDetailRo;
import com.lvmoney.oauth2.center.server.ro.Oauth2UserRo;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月18日 下午2:17:09
 */
public interface Oauth2RedisService {
    /**
     * @param oauth2UserRo 2019年1月18日下午2:17:12
     * @describe:oauth2 userdetail 初始化到redis中
     * @author: lvmoney /xxxx科技有限公司
     */
    void userDetails2Redis(Oauth2UserRo oauth2UserRo);

    /**
     * @param username
     * @return 2019年1月18日下午2:17:38
     * @describe:oauth2获取特定用户的 user信息
     * @author: lvmoney /xxxx科技有限公司
     */
    UserInfo getOauth2UserVo(String username);

    /**
     * @param clientId
     * @return 2019年1月18日下午2:17:38
     * @describe:通过clientid获得client信息
     * @author: lvmoney /xxxx科技有限公司
     */
    BaseClientDetails getClientDetailsByClientId(String clientId);

    /**
     * @param oauth2ClientDetailRo
     * @return 2019年1月18日下午2:17:38
     * @describe:保存client信息
     * @author: lvmoney /xxxx科技有限公司
     */
    void clientDetails2Redis(Oauth2ClientDetailRo oauth2ClientDetailRo);


    void client2Redis(Oauth2ClientRo oauth2ClientRo);

    /**
     * @param clientId
     * @return 2019年1月18日下午2:17:38
     * @describe:oauth2获取特定用户的 user信息
     * @author: lvmoney /xxxx科技有限公司
     */
    OauthClient getClientByClientId(String clientId);

    void authorizationCode2Redis(AuthorizationCodeRo authorizationCodeRo);

    void deleteAuthorizationCode(String code);

    AuthorizationVo getAuthorizationCodeByCode(String code);


}
