package com.lvmoney.office.vo;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/2/27
 * Copyright xxxx科技有限公司
 */


import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.data.RenderData;
import com.deepoove.poi.data.TextRenderData;
import com.deepoove.poi.util.BytePictureUtils;
import com.lvmoney.common.utils.JsonUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
public class WordTemplateVo implements Serializable {
    private static final long serialVersionUID = -7729141625128880430L;
    private String source;
    private String target;
    private List<WordNumbericVo> numberic;
    private List<WordPictureVo> picture;
    private List<WordStringVo> str;
    private List<WordTablesVo> table;
}
