package com.lvmoney.captcha.vo;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/2/15
 * Copyright XXXXXX有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /XXXXXX有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
public class ValidateResultVo implements Serializable {
    private String value;
    private String code;
    private String serialNumber;
}
