/**
 * 描述:
 * 包名:com.lvmoney.router.service.impl
 * 版本信息: 版本1.0
 * 日期:2018年12月29日  下午1:41:35
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.router.service.impl;

import org.springframework.stereotype.Service;

import com.lvmoney.common.exception.BusinessException;
import com.lvmoney.common.exception.CommonException;
import com.lvmoney.router.annotation.AuthenticationValidated;
import com.lvmoney.router.annotation.ParamValidated;
import com.lvmoney.router.annotation.PermissionValidated;
import com.lvmoney.router.annotation.RouterMethod;
import com.lvmoney.router.annotation.RouterService;
import com.lvmoney.router.service.AService;
import com.lvmoney.router.vo.req.NormalReqVo;
import com.lvmoney.router.vo.req.TestReqVo;
import com.lvmoney.router.vo.resp.TestRespVo;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年12月29日 下午1:41:35
 */
@RouterService(path = "aService")
@Service
public class AServiceImpl implements AService {

    @Override
    @RouterMethod(name = "test")
    @ParamValidated(isValidate = false)
    public TestRespVo test(TestReqVo testReqVo) {
        TestRespVo result = new TestRespVo();
        result.setAddress(testReqVo.getAddress());
        result.setAge(testReqVo.getAge());
        result.setName(testReqVo.getName());
        return result;
    }

    @Override
    @RouterMethod(name = "exception")
    public TestRespVo errorTest() {
        throw new BusinessException(CommonException.Proxy.OTHER);
    }

    @Override
    @RouterMethod(name = "normal")
    @ParamValidated(isValidate = true)
    @PermissionValidated(role = "USER")
    @AuthenticationValidated
    public TestRespVo normalTest(NormalReqVo testReqVo) {
        TestRespVo result = new TestRespVo();
        result.setAddress(testReqVo.getAddress());
        result.setAge(testReqVo.getAge());
        result.setName(testReqVo.getName());
        return result;
    }

    @Override
    @RouterMethod(name = "void")
    @ParamValidated(isValidate = true)
    public TestRespVo voidTest() {
        TestRespVo result = new TestRespVo();
        result.setAddress("test");
        result.setAge(10);
        result.setName("test");
        return result;
    }

}
