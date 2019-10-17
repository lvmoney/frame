package com.lvmoney.k8s.base.vo.jyaml;

import lombok.Data;

import java.io.Serializable;

@Data
public class MatchLabels implements Serializable {
    private String app;
    private String version;
}
