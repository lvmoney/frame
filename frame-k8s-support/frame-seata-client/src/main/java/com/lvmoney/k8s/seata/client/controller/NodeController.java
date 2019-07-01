package com.lvmoney.k8s.seata.client.controller;

import com.lvmoney.k8s.seata.client.server.FeginSeataServer;
import com.lvmoney.k8s.seata.client.server.FeginSeataUServer;
import com.lvmoney.k8s.seata.client.service.TestService;
import com.lvmoney.k8s.seata.client.vo.req.UpdateReqVo;
import com.lvmoney.k8s.seata.client.vo.req.UserReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Administrator on 2019/5/23.
 */
@RestController
@RequestMapping("/fegin")
public class NodeController {
    @Autowired
    TestService testService;

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public int test2(UserReqVo userReqVo) {
        return testService.save(userReqVo);
    }

    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    public boolean test3(UpdateReqVo updateReqVo) {
        return testService.seataService(updateReqVo);
    }
}