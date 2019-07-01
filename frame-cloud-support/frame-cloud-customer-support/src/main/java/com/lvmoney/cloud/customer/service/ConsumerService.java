package com.lvmoney.cloud.customer.service;

import com.lvmoney.cloud.customer.feign.MFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @Autowired
    private MFeignClient mFeignClient;

    public String consumer() {

        return mFeignClient.producer();
    }


}
