package com.lvmoney.redis.controller;

import com.lvmoney.redis.service.BaseRedisService;
import com.lvmoney.redis.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvmoney on 2019/1/18.
 */
//@RestController
public class TestContorller {

    @Autowired
    BaseRedisService redisService;

    /**
     * 查询所有用户到redis中
     *
     * @return
     */
    @RequestMapping("/test")
    public List<PageVo> selectAll() {
        return null;

    }

    /**
     * 我这里是把 user 信息存到object 中的  所有我要取出来用RedisTemplate 进行对 list的操作
     */
    @RequestMapping("/add")
    public void addTest() {
        List<PageVo> pList = new ArrayList<PageVo>();
        String key = "test";
        PageVo pv = new PageVo();
        pv.setPageNo(1);
        pv.setPageSize(100);
        pList.add(pv);

        PageVo pv1 = new PageVo();
        pv1.setPageNo(2);
        pv1.setPageSize(200);

        pList.add(pv1);
        PageVo pv3 = new PageVo();
        pv3.setPageNo(3);
        pv3.setPageSize(300);
        pList.add(pv3);
        PageVo pv4 = new PageVo();
        pv4.setPageNo(4);
        pv4.setPageSize(400);
        pList.add(pv4);

        redisService.addList(key, pList, 18000l);

    }

    /**
     * 这里取key 对应的list 那0 到 1 下标的 对象  也就是2个对象
     *
     * @return
     */
    @RequestMapping("/list")
    public PageVo aaa() {
        System.out.println(redisService.getListSize("test"));
        PageVo pv = new PageVo();
        pv.setPageNo(1);
        pv.setPageSize(2);
        pv.setKey("test");
        return redisService.getListPage(pv);
    }


}
