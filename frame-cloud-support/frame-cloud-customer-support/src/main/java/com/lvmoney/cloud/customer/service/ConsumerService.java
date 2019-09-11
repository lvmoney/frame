package com.lvmoney.cloud.customer.service;

import com.lvmoney.cloud.customer.feign.MFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Service
public class ConsumerService {

    @Autowired
    private MFeignClient mFeignClient;

    public String consumer() {

        return mFeignClient.producer();
    }


}
