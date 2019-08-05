package com.lvmoney.oauth2.server.vo;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:${{
 * Copyright xxxx科技有限公司
 */


import lombok.*;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Oauth2ClientVo implements Serializable {
    private static final long serialVersionUID = 3535719119827205720L;
    private String client;
    private BaseClientDetails clientDetails;

}
