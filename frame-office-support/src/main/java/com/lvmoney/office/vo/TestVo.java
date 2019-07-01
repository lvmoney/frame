package com.lvmoney.office.vo;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/3/22
 * Copyright xxxx科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestVo implements Serializable {
    private String name;
    private String address;
    private Long num;
    private AVo aVo;
}
