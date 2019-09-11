package com.lvmoney.test;/**
 * 描述:
 * 包名:com.lvmoney.test
 * 版本信息: 版本1.0
 * 日期:2019/9/6
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.common.utils.JsonUtil;
import com.lvmoney.mysql.rwseparate.application.Application;
import com.lvmoney.mysql.rwseparate.po.TestPo;
import com.lvmoney.mysql.rwseparate.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/9/6 18:20
 */
@Slf4j
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = Application.class)
public class DemoServiceTest {
    @Autowired
    public TestService service;

    @Test

    public void CRUDTest() {

        TestPo o = new TestPo();
        o.setName("test");
        service.saveTest(o);
//        TestPo testPo = new TestPo();
//        testPo.setId("1");
//        testPo.setName("testAA");
//        service.updateTest(testPo);
        System.out.println(JsonUtil.t2JsonString(service.getTest("1")));
    }
}
