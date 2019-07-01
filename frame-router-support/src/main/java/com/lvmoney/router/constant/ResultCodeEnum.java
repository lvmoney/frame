/**
 * 描述:
 * 包名:com.lvmoney.router.JwtConstant
 * 版本信息: 版本1.0
 * 日期:2018年12月29日  下午3:40:48
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.router.constant;

/**
 * @describe：状态码定义
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年12月29日 下午3:40:48
 */

public enum ResultCodeEnum {
    // RPC层调用错误码
    SUCESS(2000, "sucess");
    private int code;
    private String description;

    ResultCodeEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
