package com.lvmoney.k8s.base.enums;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.enums
 * 版本信息: 版本1.0
 * 日期:2019/8/19
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：yaml kind
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/19 11:16
 */
public enum YamlKind {
    Service("Service"),
    Deployment("Deployment"),
    Gateway("Gateway"),
    VirtualService("VirtualService"),
    Ingress("Ingress"),
    DestinationRule("DestinationRule"),
    listchecker("listchecker"),
    QuotaSpec("QuotaSpec"),
    listentry("listentry"),
    rule("rule"),
    memquota("memquota"),
    QuotaSpecBinding("QuotaSpecBinding"),
    quota("quota"),
    handler("handler"),
    instance("instance");
    private String value;

    YamlKind(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
