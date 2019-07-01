package com.lvmoney.lock.controller;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/3/9
 * Copyright xxxx科技有限公司
 */


import com.lvmoney.lock.service.ProdLockService;
import com.lvmoney.lock.service.SequenceService;
import com.lvmoney.lock.vo.req.ProdLockInitReqVo;
import com.lvmoney.lock.vo.req.SectionInitVo;
import com.lvmoney.lock.vo.req.ProdLockStockReqVo;
import com.lvmoney.lock.vo.req.SectionReqVo;
import com.lvmoney.lock.vo.resp.ProdLockStockRespVo;
import com.lvmoney.lock.vo.resp.SectionRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
//@RestController
//@RequestMapping(value = "/test3")
public class Test3Controller {
    @Autowired
    SequenceService sequenceService;
    @Autowired
    ProdLockService prodLockService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test() throws Exception {
        SectionInitVo sectionInitVo = new SectionInitVo("aaaa", 0L, 180000L);
        sequenceService.initSectionData(sectionInitVo);
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public SectionRespVo testDistributed(SectionReqVo sectionReqVo) {
        return sequenceService.getSectionData(sectionReqVo);
    }

    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    public boolean testDistributed3() {
        ProdLockInitReqVo sectionReqVo = new ProdLockInitReqVo();
        sectionReqVo.setStock(new HashMap<String, Integer>() {
            {
                put("name", 100);
                put("name2", 100);
                put("name3", 0);
            }
        });
        return prodLockService.initLockStock(sectionReqVo);
    }

    @RequestMapping(value = "/test4", method = RequestMethod.GET)
    public ProdLockStockRespVo testDistributed4(ProdLockStockReqVo sectionReqVo) {
        return prodLockService.getStock(sectionReqVo);
    }
}
