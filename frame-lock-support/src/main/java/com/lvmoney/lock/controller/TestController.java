package com.lvmoney.lock.controller;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/3/8
 * Copyright xxxx科技有限公司
 */


import com.lvmoney.lock.constant.LockConstant;
import com.lvmoney.redis.service.BaseRedisService;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.concurrent.TimeUnit;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
//@RestController
public class TestController {
    @Autowired
    BaseRedisService baseRedisService;
    @Autowired
    private Redisson redisson;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test() throws Exception {
        //设置一个key，aaa商品的库存数量为100
        baseRedisService.set("aaa", "100", 18000l);
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public void testDistributed() {
        //执行的业务代码
        for (int i = 0; i < 55; i++) {
            RLock lock = redisson.getLock(LockConstant.SECTION_LOCK_KEY);
            lock.lock(60, TimeUnit.SECONDS); //设置60秒自动释放锁  （默认是30秒自动过期）
            int stock = Integer.parseInt(baseRedisService.getString("aaa").toString());
            if (stock > 0) {
                //stringRedisTemplate.opsForValue().set("aaa",(stock-1)+"");
                baseRedisService.set("aaa", (stock - 1) + "", 18000l);

                System.out.println("test2_:lockkey:" + LockConstant.SECTION_LOCK_KEY + ",stock:" + (stock - 1) + "");
            }
            lock.unlock(); //释放锁
        }
    }

    @RequestMapping(value = "/test100", method = RequestMethod.GET)
    public void test100() throws Exception {
        //设置一个key，aaa商品的库存数量为100
        baseRedisService.set("aaa", "100", 18000l);
    }

    @RequestMapping(value = "/test200", method = RequestMethod.GET)
    public void test200() throws Exception {
        //设置一个key，aaa商品的库存数量为100
        baseRedisService.set("aaa", "100", 18000l);
    }
}
