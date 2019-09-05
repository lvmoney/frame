package com.lvmoney.oauth2.center.server.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class Role extends CommonVo {
    private String roleName;
}
