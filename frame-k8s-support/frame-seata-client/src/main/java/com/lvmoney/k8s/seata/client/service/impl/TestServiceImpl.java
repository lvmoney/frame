package com.lvmoney.k8s.seata.client.service.impl;

import com.lvmoney.k8s.seata.client.server.FeginSeataServer;
import com.lvmoney.k8s.seata.client.server.FeginSeataUServer;
import com.lvmoney.k8s.seata.client.service.TestService;
import com.lvmoney.k8s.seata.client.vo.req.UpdateReqVo;
import com.lvmoney.k8s.seata.client.vo.req.UserReqVo;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2019/6/27.
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    FeginSeataServer feginSeataServer;
    @Autowired
    FeginSeataUServer feginSeataUServer;

    @Override
    @GlobalTransactional
    public boolean seataService(UpdateReqVo updateReqVo) {
        try {
            int v1 = feginSeataServer.update(updateReqVo.getSUserId());
            int v2 = feginSeataUServer.update(updateReqVo.getAUserId());
        } catch (Exception e) {
            return false;
        }
        throw new RuntimeException();
        // return true;

    }

    @Override
    @GlobalTransactional
    public int save(UserReqVo userReqVo) {
        return feginSeataServer.save(userReqVo);
    }
}
