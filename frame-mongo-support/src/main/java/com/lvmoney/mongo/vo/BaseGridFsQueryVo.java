/**
 * 描述:
 * 包名:com.lvmoney.mongo.ro
 * 版本信息: 版本1.0
 * 日期:2019年1月10日  下午2:06:39
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.mongo.vo;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月10日 下午2:06:39
 */

public class BaseGridFsQueryVo implements Serializable {

    /**
     *
     */


    private static final long serialVersionUID = 8408644038062965872L;

    private String mongoFileId;
    private String name;

    public String getMongoFileId() {
        return mongoFileId;
    }

    public void setMongoFileId(String mongoFileId) {
        this.mongoFileId = mongoFileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
