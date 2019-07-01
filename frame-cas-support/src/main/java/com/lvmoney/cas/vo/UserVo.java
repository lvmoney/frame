/**
 * 描述:
 * 包名:com.lvmoney.cas.ro
 * 版本信息: 版本1.0
 * 日期:2019年1月15日  上午9:23:01
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.cas.vo;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月15日 上午9:23:01
 */

public class UserVo implements Serializable {
    /**
     *
     */

    private static final long serialVersionUID = 4173443850909828181L;
    private String uername;
    private String password;

    public String getUername() {
        return uername;
    }

    public void setUername(String uername) {
        this.uername = uername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
