package com.lvmoney.mysql.separate.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.mysql.separate.sevice.impl
 * 版本信息: 版本1.0
 * 日期:2019/9/6
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.mysql.separate.dao.TestDao;
import com.lvmoney.mysql.separate.po.TestPo;
import com.lvmoney.mysql.separate.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/9/6 15:05
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestDao testDao;

    @Override
    public void saveTest(TestPo test) {
        testDao.insert(test);
    }

    @Override
    public TestPo getTest(String id) {
        return testDao.selectById(id);
    }

    @Override
    public void updateTest(TestPo testPo) {
        testDao.updateById(testPo);
    }
}
