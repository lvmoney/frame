package com.lvmoney.lock.vo.req;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/3/26
 * Copyright xxxx科技有限公司
 */


import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
public class ProdLockInitReqVo implements Serializable {
    private Long expire;
    private Map<String, Integer> stock;
}
