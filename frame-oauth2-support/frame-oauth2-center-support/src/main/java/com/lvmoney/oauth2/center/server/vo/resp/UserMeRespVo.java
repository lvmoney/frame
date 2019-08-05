package com.lvmoney.oauth2.center.server.vo.resp;/**
 * 描述:
 * 包名:com.lvmoney.oauth2.center.server.vo.resp
 * 版本信息: 版本1.0
 * 日期:2019/7/29
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/7/29 10:16
 */
@Data
public class UserMeRespVo implements Serializable {
    private static final long serialVersionUID = -5280186002200313959L;
    private String username;
    private String gender;
    private String nickName;
    private String grantType;
    private String accountOpenCode;
    private String authorities;
    private int status;
}
