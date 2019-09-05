package com.lvmoney.customer.vo.req;/**
 * 描述:
 * 包名:com.lvmoney.provider.vo.req
 * 版本信息: 版本1.0
 * 日期:2019/8/16
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/16 11:24
 */
@Data
public class UserReqVo implements Serializable {
    private static final long serialVersionUID = -2364897025377565750L;
    private String username;
}
