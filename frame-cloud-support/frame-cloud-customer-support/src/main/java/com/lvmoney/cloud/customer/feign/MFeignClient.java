package com.lvmoney.cloud.customer.feign;

import com.lvmoney.cloud.customer.feign.config.MFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@FeignClient(name = "sc-provider", fallback = MFeignClientFallback.class, configuration = MFeignConfig.class)
public interface MFeignClient {
    /**
     * 这是被请求微服务的地址，也就是provider的地址
     *
     * @param msg: 信息
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 9:06
     */
    @GetMapping(value = "/test/{msg}")
    String sayHelloWorld(@PathVariable("msg") String msg);

    /**
     * 测试list
     *
     * @throws
     * @return: java.util.List<java.lang.Integer>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 9:07
     */
    @GetMapping(value = "/test/list")
    List<Integer> list();

    /**
     * 测试 array
     *
     * @throws
     * @return: java.lang.Integer[]
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 9:07
     */
    @GetMapping(value = "/test/list")
    Integer[] array();

    /**
     * 测试生产者
     *
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 9:07
     */
    @GetMapping("/producer")
    String producer();
}
