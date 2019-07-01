package com.lvmoney.log.vo;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/2/1
 * Copyright xxxx科技有限公司
 */


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
public class ThrowableVo implements Serializable {
    private Integer code;
    private String description;
    private String message;
    private String type;
    @JSONField(name = "@type")
    private String eType;
}
