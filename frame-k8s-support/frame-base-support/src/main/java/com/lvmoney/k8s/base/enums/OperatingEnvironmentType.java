package com.lvmoney.k8s.base.enums;/**
 * 描述:
 * 包名:com.lvmoney.k8s.gateway.enums
 * 版本信息: 版本1.0
 * 日期:2019/8/20
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/20 11:58
 */
public enum OperatingEnvironmentType {
    local("local"),
    istio("istio");
    private String value;

    OperatingEnvironmentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
