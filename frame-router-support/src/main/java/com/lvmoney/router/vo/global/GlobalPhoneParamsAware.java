package com.lvmoney.router.vo.global;

import java.io.Serializable;


/**
 * 全局参数接口
 */
public interface GlobalPhoneParamsAware extends Serializable {

    void setGlobalParams(GlobalPhoneParams globalPhoneParams);

    GlobalPhoneParams getGlobalParams();

}
