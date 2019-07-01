package com.lvmoney.cloud.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


@RestController
public class ProducerController {
    private final static Logger logger = LoggerFactory.getLogger(ProducerController.class);

    @GetMapping("/producer")
    public String producer() {
        System.out.println("I'm producer");
        return "Hello, I'm producer";
    }

    @GetMapping("{msg}")
    public Mono<String> sayHelloWorld(@PathVariable("msg") String msg) {
        System.out.println("come on " + msg);
        return Mono.just("sc-provider receive : " + msg);
    }

    @GetMapping("list")
    public Flux<Integer> list() {
        List<Integer> list = new ArrayList<>();
        list.add(8);
        list.add(22);
        list.add(75);
        list.add(93);
        Flux<Integer> userFlux = Flux.fromIterable(list);
        return userFlux;
    }

    /**
     * 说明进行了三次的请求，也就是进行了两次的重试。
     * 这样也就验证了我们的配置信息，完成了Zuul的重试功能。
     * @param name
     * @return
     */
    @GetMapping("/hello")
    public String index(@RequestParam String name) {
        logger.info("request two name is "+name);
        try{
            Thread.sleep(1000000);
        }catch ( Exception e){
            logger.error(" hello two error",e);
        }
        return "hello "+name+"，this is two messge";
    }
}
