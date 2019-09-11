package com.lvmoney.redis.controller;

import com.lvmoney.common.vo.Page;
import com.lvmoney.redis.service.BaseRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 * @RestController
 */
public class TestContorller {

    @Autowired
    BaseRedisService redisService;

    /**
     * 查询所有用户到redis中
     *
     * @return
     */
    @RequestMapping("/test")
    public List<Page> selectAll() {
        return null;

    }

    /**
     * 我这里是把 user 信息存到object 中的  所有我要取出来用RedisTemplate 进行对 list的操作
     */
    @RequestMapping("/add")
    public void addTest() {
        List<Page> pList = new ArrayList<Page>();
        String key = "test";
        Page pv = new Page();
        pv.setPageNo(1);
        pv.setPageSize(100);
        pList.add(pv);

        Page pv1 = new Page();
        pv1.setPageNo(2);
        pv1.setPageSize(200);

        pList.add(pv1);
        Page pv3 = new Page();
        pv3.setPageNo(3);
        pv3.setPageSize(300);
        pList.add(pv3);
        Page pv4 = new Page();
        pv4.setPageNo(4);
        pv4.setPageSize(400);
        pList.add(pv4);

        redisService.addList(key, pList, 18000L);

    }

    /**
     * 这里取key 对应的list 那0 到 1 下标的 对象  也就是2个对象
     *
     * @return
     */
    @RequestMapping("/list")
    public Page aaa() {
        System.out.println(redisService.getListSize("test"));
        Page pv = new Page();
        pv.setPageNo(1);
        pv.setPageSize(2);
        return redisService.getListPage(pv, "test");
    }


}
