package com.lvmoney.oauth2.center.server.vo.resp;/**
 * 描述:
 * 包名:com.lvmoney.oauth2.center.server.vo
 * 版本信息: 版本1.0
 * 日期:2019/7/30
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.oauth2.center.server.vo.Authentication;
import com.lvmoney.oauth2.center.server.vo.Oauth2Request;
import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/7/30 21:06
 */
@Data
public class AuthorizationRespVo implements Serializable {
    private Authentication authentication;
    private Oauth2Request oAuth2Request;
}
