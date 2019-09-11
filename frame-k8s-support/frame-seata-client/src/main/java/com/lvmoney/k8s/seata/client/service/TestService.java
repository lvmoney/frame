package com.lvmoney.k8s.seata.client.service;


import com.lvmoney.k8s.seata.client.vo.req.UpdateReqVo;
import com.lvmoney.k8s.seata.client.vo.req.UserReqVo;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public interface TestService {
    boolean seataService(UpdateReqVo updateReqVo);

    int save(UserReqVo userReqVo);
}
