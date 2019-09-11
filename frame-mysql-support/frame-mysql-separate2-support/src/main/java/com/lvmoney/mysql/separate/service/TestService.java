package com.lvmoney.mysql.separate.service;/**
 * 描述:
 * 包名:com.lvmoney.mysql.separate.sevice
 * 版本信息: 版本1.0
 * 日期:2019/9/6
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.mysql.separate.po.TestPo;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/9/6 15:05
 */
public interface TestService {
    void saveTest(TestPo test);

    TestPo getTest(String id);
}
