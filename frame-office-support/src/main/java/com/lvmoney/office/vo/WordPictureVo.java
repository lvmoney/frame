package com.lvmoney.office.vo;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/2/27
 * Copyright xxxx科技有限公司
 */


import com.deepoove.poi.data.PictureRenderData;
import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
public class WordPictureVo implements Serializable {
    /**
     * 图片key，在模板文件中用{{@picture}},@告诉模板是图片
     */
    private String key;
    /**
     * 图片填充值
     */
    private PictureRenderData value;

    public WordPictureVo(String key, PictureRenderData value) {
        this.key = key;
        this.value = value;
    }
}
