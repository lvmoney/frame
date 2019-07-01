package com.lvmoney.lock.vo.req;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/3/9
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
public class SectionInitVo implements Serializable {
    private String name;
    private Long value;
    private Long expire;

    public SectionInitVo() {
        super();
    }

    public SectionInitVo(String name, Long value, Long expire) {
        this.name = name;
        this.value = value;
        this.expire = expire;
    }
}
