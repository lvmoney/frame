package com.lvmoney.k8s.seata.client.vo.req;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/6/27.
 */
@Data
public class UserReqVo implements Serializable {
    private String userId;
    private String username;
    private String password;
}
