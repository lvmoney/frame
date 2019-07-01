package com.lvmoney.cloud.customer.feign;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MFeignClientFallback implements MFeignClient {
    @Override
    public String sayHelloWorld(String msg) {
        return "fallback";
    }

    @Override
    public List<Integer> list() {
        return new ArrayList<>();
    }

    @Override
    public Integer[] array() {
        return new Integer[0];
    }

    @Override
    public String producer() {
        return "";
    }
}
