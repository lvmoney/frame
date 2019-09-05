package com.lvmoney.k8s.logback.elasticsearch.eo;/**
 * 描述:
 * 包名:com.lvmoney.k8s.logback.elasticsearch.mo
 * 版本信息: 版本1.0
 * 日期:2019/8/23
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import com.lvmoney.k8s.logback.elasticsearch.constant.LogbackEsConstant;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/23 17:47
 */
@Data
@Document(indexName = LogbackEsConstant.LOG_INDEXNMAE, type = LogbackEsConstant.LOG_TYPE, shards = 1, replicas = 0)
public class LogEo implements Serializable {
    @Id
    private Long id;
    private String level;
    private String logger;
    private String thread;
    private String message;
    private Long timeStamp;
}
