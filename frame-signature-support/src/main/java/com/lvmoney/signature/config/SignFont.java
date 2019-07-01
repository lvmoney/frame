package com.lvmoney.signature.config;

import java.awt.*;

/**
 * @Description: 印章字体类
 * @Author Ran.chunlin
 * @Date: Created in 12:17 2018-10-04
 */
public class SignFont {

    public SignFont(String fontText) {
        this.fontText = fontText;
    }

    public SignFont() {
    }

    /**
     * 字体内容
     */
    private String fontText;
    /**
     * 是否加粗
     */
    private Boolean isBold = true;
    /**
     * 字形名，默认为宋体
     */
    private String fontFamily = "宋体";
    /**
     * 字体大小
     */
    private Integer fontSize;
    /**
     * 字距
     */
    private Double fontSpace;
    /**
     * 边距（环边距或上边距）
     */
    private Integer marginSize;

    /**
     * 获取系统支持的字形名集合
     */
    public static String[] getSupportFontNames() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    }

    public SignFont setFontSpace(Double fontSpace) {
        this.fontSpace = fontSpace;
        return this;
    }

    public SignFont setMarginSize(Integer marginSize) {
        this.marginSize = marginSize;
        return this;
    }

    public SignFont setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
        return this;
    }

    public SignFont setFontText(String fontText) {
        this.fontText = fontText;
        return this;
    }

    public SignFont setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public SignFont setBold(Boolean bold) {
        isBold = bold;
        return this;
    }

    public String getFontText() {
        return fontText;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public Double getFontSpace() {
        return fontSpace;
    }

    public Integer getMarginSize() {
        return marginSize;
    }

    public Boolean isBold() {
        return isBold;
    }
}
