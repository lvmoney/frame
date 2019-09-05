package com.lvmoney.k8s.seata.client.server;

import com.lvmoney.k8s.seata.client.vo.req.UserReqVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "fegin1", url = "http://localhost:8070")
public interface FeginSeataServer {

    /**
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    int save(UserReqVo user);

    @RequestMapping(value = "/updateStage/{id}", method = RequestMethod.PUT)
    int update(@PathVariable(value = "id") String id);
}
