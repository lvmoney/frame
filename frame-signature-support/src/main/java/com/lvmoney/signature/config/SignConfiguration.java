package com.lvmoney.signature.config;

import java.awt.*;

/**
 * @Description: 印章配置类
 * @Author Ran.chunlin
 * @Date: Created in 12:17 2018-10-04
 */
public class SignConfiguration {
    /**
     * 主文字
     */
    private SignFont mainFont;
    /**
     * 副文字
     */
    private SignFont viceFont;
    /**
     * 抬头文字
     */
    private SignFont titleFont;
    /**
     * 中心文字
     */
    private SignFont centerFont;
    /**
     * 边线圆
     */
    private SignCircle borderCircle;
    /**
     * 内边线圆
     */
    private SignCircle borderInnerCircle;
    /**
     * 内线圆
     */
    private SignCircle innerCircle;
    /**
     * 背景色，默认红色
     */
    private Color backgroudColor = Color.RED;
    /**
     * 图片输出尺寸，默认300
     */
    private Integer imageSize = 300;

    public SignConfiguration setMainFont(SignFont mainFont) {
        this.mainFont = mainFont;
        return this;
    }

    public SignConfiguration setViceFont(SignFont viceFont) {
        this.viceFont = viceFont;
        return this;
    }

    public SignConfiguration setTitleFont(SignFont titleFont) {
        this.titleFont = titleFont;
        return this;
    }

    public SignConfiguration setCenterFont(SignFont centerFont) {
        this.centerFont = centerFont;
        return this;
    }

    public SignConfiguration setBorderCircle(SignCircle borderCircle) {
        this.borderCircle = borderCircle;
        return this;
    }

    public SignConfiguration setBorderInnerCircle(SignCircle borderInnerCircle) {
        this.borderInnerCircle = borderInnerCircle;
        return this;
    }

    public SignConfiguration setInnerCircle(SignCircle innerCircle) {
        this.innerCircle = innerCircle;
        return this;
    }

    public SignConfiguration setBackgroudColor(Color backgroudColor) {
        this.backgroudColor = backgroudColor;
        return this;
    }

    public SignConfiguration setImageSize(Integer imageSize) {
        this.imageSize = imageSize;
        return this;
    }

    public SignFont getMainFont() {
        return mainFont;
    }

    public SignFont getViceFont() {
        return viceFont;
    }

    public SignFont getTitleFont() {
        return titleFont;
    }

    public SignFont getCenterFont() {
        return centerFont;
    }

    public SignCircle getBorderCircle() {
        return borderCircle;
    }

    public SignCircle getBorderInnerCircle() {
        return borderInnerCircle;
    }

    public SignCircle getInnerCircle() {
        return innerCircle;
    }

    public Color getBackgroudColor() {
        return backgroudColor;
    }

    public Integer getImageSize() {
        return imageSize;
    }
}
