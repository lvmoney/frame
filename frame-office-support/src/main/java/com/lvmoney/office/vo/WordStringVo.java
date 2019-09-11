package com.lvmoney.office.vo;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/2/27
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
public class WordStringVo implements Serializable {
    /**
     * 字符串key，对应模板中{{gender}}
     */
    private String key;
    /**
     * 字符串填充内容
     */
    private String value;

    public WordStringVo(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
