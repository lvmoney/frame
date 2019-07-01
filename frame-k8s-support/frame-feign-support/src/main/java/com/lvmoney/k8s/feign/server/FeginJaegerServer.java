package com.lvmoney.k8s.feign.server;

import com.lvmoney.k8s.feign.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "fegin1", url = "http://localhost:8070", configuration = FeignConfig.class)
public interface FeginJaegerServer {

    /**
     * @return
     */
    @RequestMapping(value = "/api", method = RequestMethod.GET)
    String jaeger();
}
