package com.lvmoney.shiro.ro;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/2/22
 * Copyright xxxx科技有限公司
 */


import com.lvmoney.shiro.vo.SysServiceDataVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
public class ShiroServerRo implements Serializable {
    private long expire;
    private Map<String, List<SysServiceDataVo>> data;
}
