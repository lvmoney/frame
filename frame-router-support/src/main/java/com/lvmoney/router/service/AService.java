/**
 * 描述:
 * 包名:com.lvmoney.router.service
 * 版本信息: 版本1.0
 * 日期:2018年12月29日  下午1:41:15
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.router.service;

import com.lvmoney.router.vo.req.NormalReqVo;
import com.lvmoney.router.vo.req.TestReqVo;
import com.lvmoney.router.vo.resp.TestRespVo;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年12月29日 下午1:41:15
 */

public interface AService {
    TestRespVo test(TestReqVo testReqVo);

    TestRespVo errorTest();

    TestRespVo normalTest(NormalReqVo testReqVo);

    /**
     * @return 2019年1月2日上午11:24:19
     * @describe:
     * @author: lvmoney /xxxx科技有限公司
     */

    TestRespVo voidTest();
}
