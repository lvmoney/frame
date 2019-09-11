package com.lvmoney.log.vo;/**
 * 描述:
 * 包名:com.lvmoney.log.vo
 * 版本信息: 版本1.0
 * 日期:2019/9/6
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/9/6 10:53
 */
@Data
public class ControllerVo implements Serializable {
    /**
     * 描述
     */
    private String descrption;
    /**
     * 类型
     */
    private String actionType;
}
