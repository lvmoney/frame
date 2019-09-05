package com.lvmoney.k8s.seata.client.server;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2019/6/27.
 */
@FeignClient(name = "fegin2", url = "http://localhost:8072")
public interface FeginSeataUServer {
    /**
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    int update(@PathVariable(value = "id") String id);
}
