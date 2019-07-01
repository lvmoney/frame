package com.lvmoney.k8s.feign.controller;

import com.lvmoney.k8s.feign.server.FeginConfigApi;
import com.lvmoney.k8s.feign.server.FeginFileServer;
import com.lvmoney.k8s.feign.vo.req.LoginVoReq;
import com.lvmoney.k8s.feign.vo.resp.CommonVo;
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
    private FeginConfigApi feginConfigApi;
    @Autowired
    private FeginFileServer feginFileServer;


    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test(LoginVoReq loginVoReq) {
        CommonVo user = feginConfigApi.login(loginVoReq);
        return user.toString();
    }


    @RequestMapping(value = "/test2", method = RequestMethod.POST)
    public String test2(LoginVoReq loginVoReq) {
        CommonVo user = feginConfigApi.login2(loginVoReq);
        return user.toString();
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public CommonVo uploadFile(@RequestPart(value = "file") MultipartFile multiportFile) throws Exception {
        return feginFileServer.uploadFile(multiportFile);
    }

}