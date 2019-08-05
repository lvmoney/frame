package com.lvmoney.captcha.ro;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/2/12
 * Copyright xxxx科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
public class ValidateCodeRo implements Serializable {
    /**
     * 验证码流水号
     */
    private String serialNumber;
    /**
     * 验证码的值
     */
    private String value;

    private String code;

    private long expire;
}
