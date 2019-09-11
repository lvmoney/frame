package com.lvmoney.router.vo.global;

import java.io.Serializable;

/**
 * @describe：全局参数接口
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public interface GlobalPhoneParamsAware extends Serializable {
    /**
     * 设置手机的全局参数
     *
     * @param globalPhoneParams:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:40
     */
    void setGlobalParams(GlobalPhoneParams globalPhoneParams);

    /**
     * 获得全局参数
     *
     * @throws
     * @return: com.lvmoney.router.vo.global.GlobalPhoneParams
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:40
     */
    GlobalPhoneParams getGlobalParams();

}
