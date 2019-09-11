package com.lvmoney.office.vo;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/2/27
 * Copyright xxxx科技有限公司
 */


import com.deepoove.poi.data.RenderData;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
public class WordTableVo implements Serializable {
    /**
     * table key，对应模板文件中{{#tables}}
     */
    private String key;
    /**
     * table 的head
     */
    private List<RenderData> tableHeads;
    /**
     * table 的body
     */
    private List<Object> tablebodys;
    /**
     * 描述
     */
    private String dataDesc;
    /**
     * table的宽度
     */
    private int width;

    public WordTableVo(String key, List<RenderData> tableHeads, List<Object> tablebodys, String dataDesc, int width) {
        this.key = key;
        this.tableHeads = tableHeads;
        this.tablebodys = tablebodys;
        this.dataDesc = dataDesc;
        this.width = width;
    }
}
