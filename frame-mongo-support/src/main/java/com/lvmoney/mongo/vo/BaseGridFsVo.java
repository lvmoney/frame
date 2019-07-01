/**
 * 描述:
 * 包名:com.lvmoney.mongo.ro
 * 版本信息: 版本1.0
 * 日期:2019年1月10日  下午1:20:15
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.mongo.vo;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.mongodb.DBObject;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月10日 下午1:20:15
 */

public class BaseGridFsVo implements Serializable {
    /**
     *
     */


    private static final long serialVersionUID = 6293937260761911341L;
    private String fileName;
    private MultipartFile file;
    private DBObject dbObj;
    private long maxSize;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public DBObject getDbObj() {
        return dbObj;
    }

    public void setDbObj(DBObject dbObj) {
        this.dbObj = dbObj;
    }

    public long getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }
}
