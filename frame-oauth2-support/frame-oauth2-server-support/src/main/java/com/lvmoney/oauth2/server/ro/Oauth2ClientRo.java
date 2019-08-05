package com.lvmoney.oauth2.server.ro;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:${{
 * Copyright xxxx科技有限公司
 */


import com.lvmoney.oauth2.server.vo.Oauth2ClientVo;
import lombok.*;

import java.io.Serializable;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Oauth2ClientRo implements Serializable {
    private static final long serialVersionUID = 9012980599938513391L;
    /**
     * 失效时间
     */

    private Long expire;
    /**
     * 把oauth2的user信息统一放到redis中
     */
    private Map<String, Object> data;
}
