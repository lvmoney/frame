package com.lvmoney.oauth2.center.server.vo.resp;

import com.lvmoney.oauth2.center.server.vo.Authentication;
import com.lvmoney.oauth2.center.server.vo.Oauth2Request;
import lombok.Data;

import java.io.Serializable;

@Data
public class OAuth2AuthenticationRespVo implements Serializable {
    private Authentication userAuthentication;
    private Oauth2Request oAuth2Request;
}
