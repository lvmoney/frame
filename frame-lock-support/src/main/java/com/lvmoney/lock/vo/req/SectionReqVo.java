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
public class SectionReqVo implements Serializable {

    /**
     * 区间长度
     */
    private int seqSize;
    /**
     * 结果长度
     */
    private int size;

    private Long expire;

    private String name;


}
