package com.lvmoney.office.vo;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/2/27
 * Copyright xxxx科技有限公司
 */


import com.deepoove.poi.data.TextRenderData;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
public class WordNumbericVo implements Serializable {
    /**
     * 列表key，对应模板文件中{{*active}}
     */
    private String key;
    /**
     * 列表填充内容
     */
    private List<TextRenderData> body;

    public WordNumbericVo(String key, List<TextRenderData> body) {
        this.key = key;
        this.body = body;
    }
}
