package com.lvmoney.lock.service;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/3/9
 * Copyright xxxx科技有限公司
 */


import com.lvmoney.lock.vo.req.SectionInitVo;
import com.lvmoney.lock.vo.req.SectionReqVo;
import com.lvmoney.lock.vo.resp.SectionRespVo;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public interface SequenceService {
    /**
     * @describe: 获得连续数据的开始和结束。这里需要锁定的值是Long。size不足在前面自动补零
     * @param: [sectionVo]
     * @return: SectionReqVo
     * @author： lvmoney /xxxx科技有限公司
     * 2019/3/9
     */
    SectionRespVo getSectionData(SectionReqVo sectionVo);

    /**
     * @describe: 初始化分布式锁最新值
     * @param: [sectionInitVo]
     * @return: boolean
     * @author： lvmoney /xxxx科技有限公司
     * 2019/3/9
     */
    boolean initSectionData(SectionInitVo sectionInitVo);
}
