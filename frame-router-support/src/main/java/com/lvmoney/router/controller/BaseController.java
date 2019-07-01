/**
 * 描述:
 * 包名:com.lvmoney.router.controller
 * 版本信息: 版本1.0
 * 日期:2018年12月29日  下午5:02:46
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.router.controller;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.lvmoney.router.vo.req.TestReqVo;
import com.lvmoney.router.vo.resp.TestRespVo;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年12月29日 下午5:02:46
 */
@RestController
@RequestMapping("normal")
public class BaseController {
    @RequestMapping(value = "test", method = RequestMethod.POST)
    public TestRespVo postProxy(@Validated TestReqVo testReqVo) throws Exception {
        TestRespVo result = new TestRespVo();
        result.setAddress("test");
        result.setAge(10);
        result.setName("jay");
        return result;
    }
}
