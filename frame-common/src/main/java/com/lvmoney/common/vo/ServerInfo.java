package com.lvmoney.common.vo;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.info
 * 版本信息: 版本1.0
 * 日期:2019/8/16
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/16 15:21
 */
@Data
public class ServerInfo implements Serializable {
    private static final long serialVersionUID = 3504451430923278739L;
    private String serverName;
    private int port;
    private String ip;
    private String httpScheme;
    private String uri;
}
