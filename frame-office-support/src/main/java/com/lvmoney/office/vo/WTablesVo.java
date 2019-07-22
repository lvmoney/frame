package com.lvmoney.office.vo;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/3/6
 * Copyright xxxx科技有限公司
 */


import com.deepoove.poi.data.RowRenderData;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
public class WTablesVo implements Serializable {
    /**
     * table key，对应模板文件中{{#tables}}
     */
    private String key;
    /**
     * table 的head
     */
    private RowRenderData tableHeads;
    /**
     * table 的body
     */
    private List<RowRenderData> tablebodys;

    /**
     * 描述
     */
    private String dataDesc;

    public WTablesVo(String key, RowRenderData tableHeads, List<RowRenderData> tablebodys, String dataDesc, float width) {
        this.key = key;
        this.tableHeads = tableHeads;
        this.tablebodys = tablebodys;
        this.dataDesc = dataDesc;
        this.width = width;
    }

    /**
     * table的宽度
     */

    private float width;
}
