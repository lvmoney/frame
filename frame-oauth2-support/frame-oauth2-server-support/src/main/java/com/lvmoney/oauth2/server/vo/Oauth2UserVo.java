/**
 * 描述:
 * 包名:com.lvmoney.oauth2.server.ro
 * 版本信息: 版本1.0
 * 日期:2019年1月18日  下午1:41:36
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.oauth2.server.vo;

import java.io.Serializable;
import java.util.List;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月18日 下午1:41:36
 */
@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Oauth2UserVo implements Serializable {
    private static final long serialVersionUID = 8241186887237664689L;
    private String username;
    private String password;
    private List<GrantedAuthority> grantedAuthoritys;

}
