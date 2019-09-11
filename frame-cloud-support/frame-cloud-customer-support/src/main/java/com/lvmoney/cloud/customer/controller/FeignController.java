package com.lvmoney.cloud.customer.controller;

import com.lvmoney.cloud.customer.feign.MFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@RestController
public class FeignController {

    @Autowired
    private MFeignClient feignClient;

    @GetMapping("/feign/{wd}")
    public Mono<String> sayHelloWorld(@PathVariable("wd") String parm) {
        String result = feignClient.sayHelloWorld(parm);
        return Mono.just(result);
    }

    @GetMapping("/feign/list")
    public Flux<Integer> list() {
        List<Integer> list = feignClient.list();
        Flux<Integer> userFlux = Flux.fromIterable(list);
        return userFlux;
    }

    @GetMapping("/feign/array")
    public Flux<Integer> array() {
        Integer[] arrays = feignClient.array();
        Flux<Integer> userFlux = Flux.fromArray(arrays);
        return userFlux;
    }
}
