package com.lvmoney.bigdata.canal.redis.controller;/**
 * 描述:
 * 包名:com.lvmoney.bigdata.canal.redis.controller
 * 版本信息: 版本1.0
 * 日期:2019/7/21
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.bigdata.canal.redis.service.Canal2RedisService;
import com.lvmoney.common.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/7/21 20:43
 */
@RestController
public class TestController {
    @Autowired
    Canal2RedisService canal2RedisService;

    @RequestMapping("/list")
    public Page aaa(String key, int pNum, int size, boolean all) {
        Page page = new Page();
        page.setPageNo(pNum);
        page.setPageSize(size);
        page.setAll(all);
        return canal2RedisService.getData(page, key);
    }
}
