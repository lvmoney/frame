package com.lvmoney.k8s.feign.vo.resp;

import lombok.Data;

/**
 * Created by Administrator on 2019/5/23.
 */
@Data
public class LoginVoResp {
    private String orgid;
    private String token;
    private String userId;
    private String username;
}
