/**
 * 描述:
 * 包名:com.lvmoney.router.ro.global
 * 版本信息: 版本1.0
 * 日期:2018年12月29日  下午3:01:47
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.router.vo.global;

import java.io.Serializable;

/**
 * @param <T>
 * @param <T>
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年12月29日 下午3:01:47
 */

public class PostRequestVo<T> implements Serializable {
    private static final long serialVersionUID = -3862842695983879858L;
    private String uri;
    private T data;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
