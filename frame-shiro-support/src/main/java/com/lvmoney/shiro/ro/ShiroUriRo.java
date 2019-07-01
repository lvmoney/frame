package com.lvmoney.shiro.ro;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/2/23
 * Copyright xxxx科技有限公司
 */


import com.lvmoney.shiro.vo.ShiroUriVo;
import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
public class ShiroUriRo implements Serializable {
    private String username;
    private String uri;
    private long expire;
    private ShiroUriVo shiroUriVo;
}
