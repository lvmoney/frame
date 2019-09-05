package com.lvmoney.k8s.logback.redis.ro;/**
 * 描述:
 * 包名:com.lvmoney.k8s.logback.ro
 * 版本信息: 版本1.0
 * 日期:2019/8/23
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.k8s.logback.common.vo.LogVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/23 8:56
 */
@Data
public class LogRo implements Serializable {
    private String key;
    private Long expired;
    private List<LogVo> data;
}
