package com.lvmoney.oauth2.center.server.vo.resp;

import com.lvmoney.oauth2.center.server.vo.Authentication;
import com.lvmoney.oauth2.center.server.vo.Oauth2Request;
import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Data
public class Oauth2AuthenticationRespVo implements Serializable {
    private Authentication userAuthentication;
    private Oauth2Request oAuth2Request;
}
