package com.lvmoney.lock.controller;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/3/9
 * Copyright xxxx科技有限公司
 */


import com.lvmoney.lock.constant.LockConstant;
import com.lvmoney.lock.service.DistributedLockerService;
import com.lvmoney.redis.service.BaseRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
//@RestController
public class Test2Controller {
    @Autowired
    BaseRedisService baseRedisService;
    @Autowired
    DistributedLockerService distributedLockerService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test() throws Exception {
        //设置一个key，aaa商品的库存数量为100
        baseRedisService.set("aaa", "20190300000001", 18000l);
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public List<String> testDistributed() {
        List<String> result = new ArrayList<>();
        //执行的业务代码
        for (int i = 0; i < 10; i++) {
            distributedLockerService.lock(LockConstant.SECTION_LOCK_KEY, TimeUnit.SECONDS, 60);
            int stock = Integer.parseInt(baseRedisService.getString("aaa"));
            if (stock > 0) {
                //stringRedisTemplate.opsForValue().set("aaa",(stock-1)+"");
                baseRedisService.set("aaa", (stock - 1) + "", 18000l);
                result.add("test2_:lockkey:" + LockConstant.SECTION_LOCK_KEY + ",stock:" + (stock - 1) + "");
            }
            distributedLockerService.unlock(LockConstant.SECTION_LOCK_KEY);
        }
        return result;
    }


    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    public List<String> testDistributed3(int size) {
        List<String> result = new ArrayList<>();
        distributedLockerService.lock(LockConstant.SECTION_LOCK_KEY, TimeUnit.SECONDS, 60);
        Long stock = Long.parseLong(baseRedisService.getString("aaa"));
        baseRedisService.set("aaa", (stock + size) + "", 18000l);
        distributedLockerService.unlock(LockConstant.SECTION_LOCK_KEY);
        return result;
    }


}