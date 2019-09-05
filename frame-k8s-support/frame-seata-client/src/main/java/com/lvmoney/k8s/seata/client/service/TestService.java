package com.lvmoney.k8s.seata.client.service;


import com.lvmoney.k8s.seata.client.vo.req.UpdateReqVo;
import com.lvmoney.k8s.seata.client.vo.req.UserReqVo;

/**
 * Created by Administrator on 2019/6/27.
 */
public interface TestService {
    boolean seataService(UpdateReqVo updateReqVo);

    int save(UserReqVo userReqVo);
}
