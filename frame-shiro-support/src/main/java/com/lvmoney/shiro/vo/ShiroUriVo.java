package com.lvmoney.shiro.vo;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/2/23
 * Copyright xxxx科技有限公司
 */


import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
public class ShiroUriVo implements Serializable {
    private Set<String> role;
    private Set<String> permission;
}
