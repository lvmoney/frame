package com.lvmoney.k8s.base.vo.jyaml;

import lombok.Data;

import java.io.Serializable;

@Data
public class DeploymentSpecSelector implements Serializable {
    private MatchLabels matchLabels;
}
