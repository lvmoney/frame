package com.lvmoney.router.vo.global;


import java.io.Serializable;

/**
 * @describe：三方接口统一请求的入参实体
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年12月29日 上午11:23:19
 */
public class DataVo<T> implements Serializable {
    private static final long serialVersionUID = 4842017835932216458L;

    /**
     * 公参
     */
    private String globalParam;

    /**
     * 请求接口的json对象
     */
    private T data;

    public String getGlobalParam() {
        return globalParam;
    }

    public void setGlobalParam(String globalParam) {
        this.globalParam = globalParam;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
