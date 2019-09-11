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
    /**
     * @describe: 测试
     * @param: [testReqVo]
     * @return: com.lvmoney.router.vo.resp.TestRespVo
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 9:50
     */
    TestRespVo test(TestReqVo testReqVo);

    /**
     * @describe: 错误测试
     * @param: []
     * @return: com.lvmoney.router.vo.resp.TestRespVo
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 9:51
     */
    TestRespVo errorTest();

    /**
     * @describe: 正常测试
     * @param: [testReqVo]
     * @return: com.lvmoney.router.vo.resp.TestRespVo
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 9:51
     */
    TestRespVo normalTest(NormalReqVo testReqVo);

    /**
     * @describe: 空值测试
     * @param: []
     * @return: com.lvmoney.router.vo.resp.TestRespVo
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 9:51
     */
    TestRespVo voidTest();
}
