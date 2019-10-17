package com.lvmoney.signature.application;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/1/22
 * Copyright xxxx科技有限公司
 */


import com.lvmoney.signature.config.SignCircle;
import com.lvmoney.signature.config.SignConfiguration;
import com.lvmoney.signature.config.SignFont;
import com.lvmoney.signature.utils.AbstractSignUtil;

import java.awt.*;
import java.io.IOException;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public class Application {
    public static void main(String[] args) throws Exception {
        /**
         * 印章配置文件
         */
        SignConfiguration configuration = new SignConfiguration();

        /**
         * 主文字
         */
        SignFont mainFont = new SignFont();
        mainFont.setBold(true);
        mainFont.setFontFamily("楷体");
        mainFont.setMarginSize(10);
        /**************************************************/
        //mainFont.setFontText("欢乐无敌制图网淘宝店专用章");
        //mainFont.setFontSize(35);
        //mainFont.setFontSpace(35.0);
        /**************************************************/
        //mainFont.setFontText("ZHITUWANG CO.LTDECIDDO SH  NANNINGSHI");
        //mainFont.setFontSize(20);
        //mainFont.setFontSpace(15.0);
        /**************************************************/
        mainFont.setFontText("欢乐无敌制图网淘宝店专用章");
        mainFont.setFontSize(25);
        mainFont.setFontSpace(12.0);

        /**
         * 副文字
         */
        SignFont viceFont = new SignFont();
        viceFont.setBold(true);
        viceFont.setFontFamily("宋体");
        viceFont.setMarginSize(5);
        /**************************************************/
        //viceFont.setFontText("123456789012345");
        //viceFont.setFontSize(13);
        //viceFont.setFontSpace(12.0);
        /**************************************************/
        viceFont.setFontText("正版认证");
        viceFont.setFontSize(22);
        viceFont.setFontSpace(12.0);

        /**
         * 中心文字
         */
        SignFont centerFont = new SignFont();
        centerFont.setBold(true);
        centerFont.setFontFamily("宋体");
        /**************************************************/
        //centerFont.setFontText("★");
        //centerFont.setFontSize(100);
        /**************************************************/
        //centerFont.setFontText("淘宝欢乐\n制图网淘宝\n专用章");
        //centerFont.setFontSize(20);
        /**************************************************/
        //centerFont.setFontText("123456789012345");
        //centerFont.setFontSize(20);
        /**************************************************/
        centerFont.setFontText("发货专用");
        centerFont.setFontSize(25);

        /**
         * 抬头文字
         */
        SignFont titleFont = new SignFont();
        titleFont.setBold(true);
        titleFont.setFontFamily("宋体");
        titleFont.setFontSize(22);
        /**************************************************/
        //titleFont.setFontText("发货专用");
        //titleFont.setMarginSize(68);
        //titleFont.setFontSpace(10.0);
        /**************************************************/
        titleFont.setFontText("正版认证");
        titleFont.setMarginSize(68);
        titleFont.setMarginSize(27);

        /**
         * 添加主文字
         */
        configuration.setMainFont(mainFont);
        /**
         * 添加副文字
         */
        configuration.setViceFont(viceFont);
        /**
         * 添加中心文字
         */
        configuration.setCenterFont(centerFont);
        /**
         * 添加抬头文字
         */
        //configuration.setTitleFont(titleFont);

        /**
         * 图片大小
         */
        configuration.setImageSize(300);
        /**
         * 背景颜色
         */
        configuration.setBackgroudColor(Color.RED);
        /**
         * 边线粗细、半径
         */
        //configuration.setBorderCircle(new SignCircle(3, 140, 140));
        configuration.setBorderCircle(new SignCircle(3, 140, 100));
        /**
         * 内边线粗细、半径
         */
        //configuration.setBorderInnerCircle(new SignCircle(1, 135, 135));
        configuration.setBorderInnerCircle(new SignCircle(1, 135, 95));
        /**
         * 内环线粗细、半径
         */
        //configuration.setInnerCircle(new SignCircle(2, 105, 105));
        configuration.setInnerCircle(new SignCircle(2, 85, 45));

        //1.生成公章
        try {
            AbstractSignUtil.buildAndStoreSeal(configuration, "F:\\sclt\\file\\公章.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //2.生成私章
        SignFont font = new SignFont();
        font.setFontSize(120).setBold(true).setFontText("诸葛孔明");
        AbstractSignUtil.buildAndStorePersonSeal(300, 16, font, "印", "F:\\sclt\\file\\私章.png");
    }

}
