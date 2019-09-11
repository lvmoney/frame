package com.lvmoney.customer.server;/**
 * 描述:
 * 包名:com.lvmoney.customer.server
 * 版本信息: 版本1.0
 * 日期:2019/8/16
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.customer.vo.req.UserReqVo;
import com.lvmoney.k8s.feign.config.FeignConfig;
import com.lvmoney.k8s.feign.vo.resp.CommonVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/16 11:54
 */
@FeignClient(name = "provider", url = "${rpc.server.gateway}", configuration = FeignConfig.class)
public interface UserServer {
    /**
     * 获取用户数据
     *
     * @param userReqVo: 用户请求实体
     * @throws
     * @return: com.lvmoney.k8s.feign.vo.resp.CommonVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 8:56
     */
    @PostMapping(value = "/provider/user/data", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    CommonVo getUser(UserReqVo userReqVo);
}
