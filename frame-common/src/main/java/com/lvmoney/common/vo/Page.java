package com.lvmoney.common.vo;/**
 * 描述:
 * 包名:com.lvmoney.bigdata.canal.redis.vo
 * 版本信息: 版本1.0
 * 日期:2019/7/20
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/7/20 10:38
 */
@Data
public class Page<T> implements Serializable {
    private static final long serialVersionUID = 3250193167291923077L;
    private int pageSize = 10;
    private int pageNo = 1;
    private Long total;
    private List<T> data;
    private boolean all;
}
