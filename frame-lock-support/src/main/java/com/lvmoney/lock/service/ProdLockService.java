package com.lvmoney.lock.service;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/3/26
 * Copyright xxxx科技有限公司
 */


import com.lvmoney.lock.vo.req.ProdLockInitReqVo;
import com.lvmoney.lock.vo.req.ProdLockStockReqVo;
import com.lvmoney.lock.vo.req.ProdLockUpdateReqVo;
import com.lvmoney.lock.vo.resp.ProdLockStockRespVo;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public interface ProdLockService {
    /**
     * @describe: 商品数量的扣减
     * @param: [prodInitReqVo]
     * @return: boolean
     * @author： lvmoney /xxxx科技有限公司
     * 2019/3/26
     */
    ProdLockStockRespVo getStock(ProdLockStockReqVo prodLockStockReqVo);

    /**
     * @describe: 初始化商品库存
     * @param: [prodLockInitReqVo]
     * @return: boolean
     * @author： lvmoney /xxxx科技有限公司
     * 2019/3/27
     */
    boolean initLockStock(ProdLockInitReqVo prodLockInitReqVo);

    /**
     * @describe: 更新商品库存
     * @param: [prodInitReqVo]
     * @return: boolean
     * @author： lvmoney /xxxx科技有限公司
     * 2019/3/27
     */
    ProdLockStockRespVo updateLockStock(ProdLockUpdateReqVo prodLockUpdateReqVo);
}
