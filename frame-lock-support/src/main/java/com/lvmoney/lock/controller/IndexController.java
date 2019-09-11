package com.lvmoney.lock.controller;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/3/8
 * Copyright xxxx科技有限公司
 */

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 * @RestController
 */
public class IndexController {
    /**
     * 商品key
     */
    private static String commodityCount = "commodityCount";
    /**
     *分布式锁的key
     */
    private static String lockKey = "testRedisson";

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private Redisson redisson;

    private int index = 0;

    /**
     *@describe: 查询是否健康
     *@param: []
     *@return: java.lang.String
     *@author: lvmoney /XXXXXX科技有限公司
     *2019/9/9 10:14
     */
    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public String health() {
        return "health";
    }

    /**
     *@describe: 设置商品数量
     *@param: [value]
     *@return: java.lang.String
     *@author: lvmoney /XXXXXX科技有限公司
     *2019/9/9 10:14
     */
    @RequestMapping("/setValue")
    public String setValue(int value) {
        redisTemplate.opsForValue().set(commodityCount, value + "");
        return "success";
    }
    /**
     *@describe: 模拟秒杀抢购，并发200个请求过来，查看是否出现超卖
     *@param: []
     *@return: java.lang.String
     *@author: lvmoney /XXXXXX科技有限公司
     *2019/9/9 10:15
     */
    @RequestMapping("/spike")
    public String spike() {
        String flag = "success";
        RLock lock = redisson.getLock(lockKey);
        try {
            //lock.lockAsync(5 , TimeUnit.SECONDS);
            //lock.lock(5, TimeUnit.SECONDS); //设置60秒自动释放锁  （默认是30秒自动过期）
            Future<Boolean> res = lock.tryLockAsync(100, 5, TimeUnit.SECONDS);
            boolean result = res.get();
            System.out.println("result:" + result);
            if (result) {
                index++;
                System.out.println("index:" + index);
                int stock = Integer.parseInt(redisTemplate.opsForValue().get(commodityCount).toString());
                if (stock > 0) {
                    redisTemplate.opsForValue().set(commodityCount, (stock - 1) + "");
                } else {
                    flag = "fail";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); //释放锁
        }
        return flag;
    }
}
