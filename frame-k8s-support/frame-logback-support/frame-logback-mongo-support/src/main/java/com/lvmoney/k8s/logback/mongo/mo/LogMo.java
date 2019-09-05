package com.lvmoney.k8s.logback.mongo.mo;/**
 * 描述:
 * 包名:com.lvmoney.k8s.logback.mo
 * 版本信息: 版本1.0
 * 日期:2019/8/22
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.k8s.logback.mongo.constant.LogbackMongConstant;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/22 17:51
 */
@Document(collection = LogbackMongConstant.MONGO_LOGBACK_COLLECTION)
@Data
public class LogMo implements Serializable {
    private String level;
    private String logger;
    private String thread;
    private String message;
    private Long timeStamp;
}
