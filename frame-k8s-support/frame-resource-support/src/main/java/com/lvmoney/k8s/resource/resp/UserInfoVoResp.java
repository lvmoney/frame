package com.lvmoney.k8s.resource.resp;/**
 * 描述:
 * 包名:com.lvmoney.oauth2.resource.vo.resp
 * 版本信息: 版本1.0
 * 日期:2019/8/9
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/9 16:34
 */
@Data
public class UserInfoVoResp implements Serializable {
    private String name;
    private String sex;
}
