/**
 * 描述:
 * 包名:com.lvmoney.jwt.ro
 * 版本信息: 版本1.0
 * 日期:2019年1月7日  下午5:49:22
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.common.ro;

import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月7日 下午5:49:22
 */
@Data
public class UserRo implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 4541709436582146541L;
    private String userId;
    private String username;
    private String password;
    private long expire;
    private String token;
}
