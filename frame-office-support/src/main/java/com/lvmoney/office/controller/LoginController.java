package com.lvmoney.office.controller;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/2/25
 * Copyright xxxx科技有限公司
 */


import com.lvmoney.office.vo.WordTemplateVo;
import org.jodconverter.DocumentConverter;
import org.jodconverter.office.OfficeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 * @RestController
 * @RequestMapping(value = "/login")
 */
public class LoginController {
    @Autowired
    private DocumentConverter documentConverter;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void submitLogin() {
// 源文件 （office）
        File source = new File("F:\\sclt\\file\\test.docx");
// 源文件 （pdf）
        File target = new File("F:\\sclt\\file\\test5.html");
// 转换文件
        if (!target.exists()) {
            try {
                documentConverter.convert(source).to(target).execute();
            } catch (OfficeException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * @describe: 测试
     * @param: [wordTemplateVo]
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 10:20
     */
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public void test(@RequestBody WordTemplateVo wordTemplateVo) {
        System.out.println("test");
    }


}