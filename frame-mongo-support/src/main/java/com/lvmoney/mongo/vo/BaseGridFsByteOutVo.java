/**
 * 描述:
 * 包名:com.lvmoney.mongo.ro
 * 版本信息: 版本1.0
 * 日期:2019年1月10日  下午3:28:38
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.mongo.vo;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月10日 下午3:28:38
 */

public class BaseGridFsByteOutVo implements Serializable {
    /**
     *
     */


    private static final long serialVersionUID = 1234993514856144789L;
    private byte[] fileByte;
    private String fileName;

    public byte[] getFileByte() {
        return fileByte;
    }

    public void setFileByte(byte[] fileByte) {
        this.fileByte = fileByte;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
