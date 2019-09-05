package com.lvmoney.k8s.authentication.vo;/**
 * 描述:
 * 包名:com.lvmoney.k8s.login.vo
 * 版本信息: 版本1.0
 * 日期:2019/8/13
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/13 16:12
 */
@Data
public class Oauth2TokenCheck implements Serializable {
    private static final long serialVersionUID = -8165859219283822401L;
    private Set<String> scope;
    private String exp;
    private String jti;
    private String client_id;
    private boolean adopt;
}
