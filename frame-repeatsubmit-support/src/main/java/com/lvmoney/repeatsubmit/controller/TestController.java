package com.lvmoney.repeatsubmit.controller;

import com.lvmoney.common.util.ResultUtil;
import com.lvmoney.common.util.vo.ResultData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "test")
    public ResultData<String> index(String username) {
        return ResultUtil.success("this is provider server99");
    }
}
