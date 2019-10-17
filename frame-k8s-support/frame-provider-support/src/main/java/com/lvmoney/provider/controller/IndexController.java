package com.lvmoney.provider.controller;/**
 * 描述:
 * 包名:com.lvmoney.provider.controller
 * 版本信息: 版本1.0
 * 日期:2019/8/21
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.common.util.ResultUtil;
import com.lvmoney.common.util.vo.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/21 15:09
 */
@RestController
public class IndexController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @GetMapping(value = "/")
    public ResultData<String> index() {
        return ResultUtil.success("this is provider server99");
    }
}
