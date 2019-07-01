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
public class WTemplateVo implements Serializable {
    private String source;
    private String target;
    private List<WNumbericVo> numberic;
    private List<WPictureVo> picture;
    private List<WStringVo> str;
    private List<WTableVo> table;

    public static void main(String[] args) {
        WTemplateVo templateVo = new WTemplateVo();
        templateVo.setTarget("F:\\sclt\\file\\hanyunxi3.docx");
        templateVo.setSource("F:\\sclt\\file\\test.docx");

        List<WStringVo> str = new ArrayList<WStringVo>() {{
            add(new WStringVo("name", "鞠婧祎"));
            add(new WStringVo("team", "前SNH48"));
            add(new WStringVo("gender", "女"));
            add(new WStringVo("birthday", "19940618"));
            add(new WStringVo("remark", "鞠婧祎，1994年6月18日出生于四川遂宁，毕业于四川音乐学院附属中学，中国女演员、歌手."));
        }};
        templateVo.setStr(str);

        List<WPictureVo> pic = new ArrayList<WPictureVo>() {{
            add(new WPictureVo("picture", new PictureRenderData(200, 250, ".png", BytePictureUtils.getUrlByteArray("https://pic.baike.soso.com/ugc/baikepic2/18293/cut-20170602162513-2088410512.jpg/300"))));
        }};

        templateVo.setPicture(pic);
        List<WNumbericVo> numberic = new ArrayList<WNumbericVo>() {{
            add(new WNumbericVo("active", new ArrayList<TextRenderData>() {{
                add(new TextRenderData("FF00FF", "2013年 以《剧场女神》公演正式出道"));
                add(new TextRenderData("FF00FF", "2014年 拍摄个人首支MV《足球派对》"));
                add(new TextRenderData("FF00FF", "2015年 发行出道两周年EP《青春的约定》"));
                add(new TextRenderData("FF00FF", "2016年 主演玄幻剧《九州天空城》"));
            }}
            ));
        }};
        templateVo.setNumberic(numberic);

        List<WTableVo> table = new ArrayList<WTableVo>() {{
            add(new WTableVo("tables", new ArrayList<RenderData>() {{
                add(new TextRenderData("d0d0d0", "节目"));
                add(new TextRenderData("d0d0d0", "次数"));
            }}, new ArrayList<Object>() {{
                add("《SNH星剧院公演》;999");
                add("《敢ZUO敢为女声秀》;4");
                add("《快乐大本营》;2");
            }}, "no datas", 10600));
        }};
        templateVo.setTable(table);
        System.out.println(JsonUtil.t2JsonString(templateVo));
    }
}
