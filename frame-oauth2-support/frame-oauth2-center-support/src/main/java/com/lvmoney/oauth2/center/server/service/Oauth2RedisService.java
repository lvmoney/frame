package com.lvmoney.oauth2.center.server.service;

import com.lvmoney.oauth2.center.server.ro.AuthorizationCodeRo;
import com.lvmoney.oauth2.center.server.vo.AuthorizationVo;
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
     * userdetail 初始化到redis中
     *
     * @param oauth2UserRo: 用户数据
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:55
     */
    void userDetails2Redis(Oauth2UserRo oauth2UserRo);

    /**
     * 通过用户名获得用户数据
     *
     * @param username: 用户名
     * @throws
     * @return: com.lvmoney.oauth2.center.server.vo.UserInfo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:55
     */
    UserInfo getOauth2UserVo(String username);

    /**
     * 通过clientid获得client信息
     *
     * @param clientId: 客户端id
     * @throws
     * @return: org.springframework.security.oauth2.provider.client.BaseClientDetails
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:55
     */
    BaseClientDetails getClientDetailsByClientId(String clientId);

    /**
     * 保存client信息
     *
     * @param oauth2ClientDetailRo: 客户端信息
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:56
     */
    void clientDetails2Redis(Oauth2ClientDetailRo oauth2ClientDetailRo);

    /**
     * 用户权限信息存到redis
     *
     * @param authorizationCodeRo: 权限信息
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:56
     */
    void authorizationCode2Redis(AuthorizationCodeRo authorizationCodeRo);

    /**
     * 删除权限code
     *
     * @param code: code
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:56
     */
    void deleteAuthorizationCode(String code);

    /**
     * 获得权限code
     *
     * @param code: code
     * @throws
     * @return: com.lvmoney.oauth2.center.server.vo.AuthorizationVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:56
     */
    AuthorizationVo getAuthorizationCodeByCode(String code);


}
