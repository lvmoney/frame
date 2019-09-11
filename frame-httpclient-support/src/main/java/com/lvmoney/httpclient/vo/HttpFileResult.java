package com.lvmoney.httpclient.vo;

import lombok.Data;

import java.io.InputStream;
import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Data
public class HttpFileResult implements Serializable {

    /**
     * 编号
     */
    private int code;

    public HttpFileResult(int code, InputStream inputStream) {
        this.code = code;
        this.inputStream = inputStream;
    }

    /**
     * 消息体
     */
    private InputStream inputStream;
}
