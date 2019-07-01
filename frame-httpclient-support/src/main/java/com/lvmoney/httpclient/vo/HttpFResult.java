package com.lvmoney.httpclient.vo;

import lombok.Data;

import java.io.InputStream;
import java.io.Serializable;

/**
 * Created by Administrator on 2019/6/24.
 */
@Data
public class HttpFResult implements Serializable {

    /**
     * 编号
     */
    private int code;

    public HttpFResult(int code, InputStream inputStream) {
        this.code = code;
        this.inputStream = inputStream;
    }

    /*

         * 消息体
         */
    private InputStream inputStream;
}
