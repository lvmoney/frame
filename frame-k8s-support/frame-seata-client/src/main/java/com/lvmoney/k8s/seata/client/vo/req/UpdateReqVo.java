package com.lvmoney.k8s.seata.client.vo.req;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/6/27.
 */
@Data
public class UpdateReqVo implements Serializable {
    private String SUserId;
    private String AUserId;
}
