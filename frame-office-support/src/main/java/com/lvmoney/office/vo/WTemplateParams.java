package com.lvmoney.office.vo;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/2/28
 * Copyright xxxx科技有限公司
 */


import com.lvmoney.office.enums.WTemplateEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
public class WTemplateParams implements Serializable {
    private String paramName;
    private WTemplateEnum wTemplateEnum;
    private String templateParam;
}
