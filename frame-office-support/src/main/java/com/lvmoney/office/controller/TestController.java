package com.lvmoney.office.controller;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/3/22
 * Copyright xxxx科技有限公司
 */


import com.lvmoney.common.utils.ResultUtil;
import com.lvmoney.common.utils.vo.ResultData;
import com.lvmoney.office.vo.AVo;
import com.lvmoney.office.vo.TestVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
//@RestController
public class TestController {
    @RequestMapping(value = "/test7", method = RequestMethod.POST)
    public ResultData exportExcel(TestVo testVo, HttpServletRequest request) {
        testVo.setAddress(null);
        testVo.setNum(null);
        AVo aVo = new AVo();
        aVo.setMum(null);
        aVo.setName("我爱你");
        testVo.setAVo(aVo);
        return ResultUtil.success(testVo);
    }

    @RequestMapping(value = "/test8", method = RequestMethod.GET)
    public void exportExcel8(TestVo testVo) {
        System.out.println(testVo);
    }
}
