package com.lvmoney.signature.application;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/1/22
 * Copyright xxxx科技有限公司
 */


import com.lvmoney.signature.config.SignFont;
import com.lvmoney.signature.utils.AbstractSignUtil;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public class Test2 {
    public static void main(String[] args) throws Exception {
        SignFont font = new SignFont();
        font.setFontSize(120).setBold(true).setFontText("诸葛孔明");
        AbstractSignUtil.buildAndStorePersonSeal(300, 16, font, "印", "F:\\sclt\\file\\私章.png");
    }
}
