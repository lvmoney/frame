//package com.lvmoney.office.controller;/**
// * 描述:
// * 包名:com.lvmoney.jwt.annotation
// * 版本信息: 版本1.0
// * 日期:2019/3/11
// * Copyright xxxx科技有限公司
// */
//
//
//import com.lvmoney.office.utils.ExcelUtil;
//import com.lvmoney.office.vo.PersonExportVo;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import javax.servlet.http.HttpServletResponse;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @describe：
// * @author: lvmoney /xxxx科技有限公司
// * @version:v1.0 2018年10月30日 下午3:29:38
// */
////@RestController
//public class ExcelController {
//
//
//    /**
//     * 导出
//     *
//     * @param response
//     */
//    @RequestMapping(value = "/export", method = RequestMethod.GET)
//    public void exportExcel(HttpServletResponse response) {
//        long start = System.currentTimeMillis();
//        List<PersonExportVo> personList = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            PersonExportVo personVo = new PersonExportVo();
//            personVo.setName("张三" + i);
//            personVo.setUsername("张三" + i);
//            personVo.setPhoneNumber("18888888888");
//            personVo.setImageUrl("");
//            personList.add(personVo);
//        }
//        ExcelUtil.exportExcel(personList, "员工信息表", "员工信息", PersonExportVo.class, "员工信息.xls", response);
//    }
//}
