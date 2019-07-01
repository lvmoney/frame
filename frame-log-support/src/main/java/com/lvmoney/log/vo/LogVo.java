package com.lvmoney.log.vo;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/1/31
 * Copyright xxxx科技有限公司
 */


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
public class LogVo implements Serializable {

    private String requestIp; //操作IP

    private String type;//  操作类型 1 操作记录 2异常记录

    private String username;// 操作人ID

    private String userId;

    private String description;// 操作描述

    private Long actionDate;// 操作时间

    private Integer exceptionCode;// 异常code

    private String exceptionDetail;// 异常详情

    private String actionMethod;//请求方法

    private String params;//请求参数

    private String token;//操作人员token
}
