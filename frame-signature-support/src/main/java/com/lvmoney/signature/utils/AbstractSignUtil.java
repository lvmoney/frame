package com.lvmoney.signature.utils;


import com.lvmoney.signature.config.SignCircle;
import com.lvmoney.signature.config.SignConfiguration;
import com.lvmoney.signature.config.SignFont;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @Description: 印章工具类
 * @Author Ran.chunlin
 * @data: Created in 18:24 2018-10-03
 */
public abstract class AbstractSignUtil {
    /**
     * 字体长度2
     */
    private static final int FONT_TEXT_LENGTH_2 = 2;
    /**
     * 字体长度3
     */
    private static final int FONT_TEXT_LENGTH_3 = 3;
    /**
     * 字体长度4
     */
    private static final int FONT_TEXT_LENGTH_4 = 4;

    /**
     * 默认从10x10的位置开始画，防止左上部分画布装不下
     */
    private static final int INIT_BEGIN = 10;

    /**
     * 生成私人印章图片，并保存到指定路径
     *
     * @param lineSize  边线宽度
     * @param font      字体对象
     * @param addString 追加字符
     * @param fullPath  保存全路径
     * @throws Exception 异常
     */
    public static void buildAndStorePersonSeal(int imageSize, int lineSize, SignFont font, String addString,
                                               String fullPath) throws Exception {
        storeBytes(buildBytes(buildPersonSeal(imageSize, lineSize, font, addString)), fullPath);
    }

    /**
     * 生成印章图片，并保存到指定路径
     *
     * @param conf     配置文件F
     * @param fullPath 保存全路径
     * @throws Exception 异常
     */
    public static void buildAndStoreSeal(SignConfiguration conf, String fullPath) throws Exception {
        storeBytes(buildBytes(buildSeal(conf)), fullPath);
    }

    /**
     * 生成印章图片的byte数组
     *
     * @param image BufferedImage对象
     * @return byte数组
     * @throws IOException 异常
     */
    public static byte[] buildBytes(BufferedImage image) throws Exception {

        try (ByteArrayOutputStream outStream = new ByteArrayOutputStream()) {
            //bufferedImage转为byte数组
            ImageIO.write(image, "png", outStream);
            return outStream.toByteArray();
        }
    }

    /**
     * 生成印章图片
     *
     * @param conf 配置文件
     * @return BufferedImage对象
     * @throws Exception 异常
     */
    public static BufferedImage buildSeal(SignConfiguration conf) throws Exception {

        //1.画布
        BufferedImage bi = new BufferedImage(conf.getImageSize(), conf.getImageSize(), BufferedImage.TYPE_4BYTE_ABGR);

        //2.画笔
        Graphics2D g2d = bi.createGraphics();

        //2.1抗锯齿设置
        //文本不抗锯齿，否则圆中心的文字会被拉长
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        //其他图形抗锯齿
        hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(hints);

        //2.2设置背景透明度
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 0));

        //2.3填充矩形
        g2d.fillRect(0, 0, conf.getImageSize(), conf.getImageSize());

        //2.4重设透明度，开始画图
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 1));

        //2.5设置画笔颜色
        g2d.setPaint(conf.getBackgroudColor());

        //3.画边线圆
        if (conf.getBorderCircle() != null) {
            drawCicle(g2d, conf.getBorderCircle(), INIT_BEGIN, INIT_BEGIN);
        } else {
            throw new Exception("BorderCircle can not null！");
        }

        int borderCircleWidth = conf.getBorderCircle().getWidth();
        int borderCircleHeight = conf.getBorderCircle().getHeight();

        //4.画内边线圆
        if (conf.getBorderInnerCircle() != null) {
            int x = INIT_BEGIN + borderCircleWidth - conf.getBorderInnerCircle().getWidth();
            int y = INIT_BEGIN + borderCircleHeight - conf.getBorderInnerCircle().getHeight();
            drawCicle(g2d, conf.getBorderInnerCircle(), x, y);
        }

        //5.画内环线圆
        if (conf.getInnerCircle() != null) {
            int x = INIT_BEGIN + borderCircleWidth - conf.getInnerCircle().getWidth();
            int y = INIT_BEGIN + borderCircleHeight - conf.getInnerCircle().getHeight();
            drawCicle(g2d, conf.getInnerCircle(), x, y);
        }

        //6.画弧形主文字
        if (borderCircleHeight != borderCircleWidth) {
            drawArcFont4Oval(g2d, conf.getBorderCircle(), conf.getMainFont(), true);
        } else {
            drawArcFont4Circle(g2d, borderCircleHeight, conf.getMainFont(), true);
        }

        //7.画弧形副文字
        if (borderCircleHeight != borderCircleWidth) {
            drawArcFont4Oval(g2d, conf.getBorderCircle(), conf.getViceFont(), false);
        } else {
            drawArcFont4Circle(g2d, borderCircleHeight, conf.getViceFont(), false);
        }

        //8.画中心字
        drawFont(g2d, (borderCircleWidth + INIT_BEGIN) * 2, (borderCircleHeight + INIT_BEGIN) * 2,
                conf.getCenterFont());

        //9.画抬头文字
        drawFont(g2d, (borderCircleWidth + INIT_BEGIN) * 2, (borderCircleHeight + INIT_BEGIN) * 2, conf.getTitleFont());

        g2d.dispose();
        return bi;
    }

    /**
     * 生成私人印章图片
     *
     * @param lineSize  线条粗细
     * @param font      字体对象
     * @param addString 是否添加文字，如“印”
     * @return BufferedImage对象
     * @throws Exception 异常
     */
    public static BufferedImage buildPersonSeal(int imageSize, int lineSize, SignFont font, String addString)
            throws Exception {
        if (font == null || font.getFontText().length() < FONT_TEXT_LENGTH_2 || font.getFontText().length() > FONT_TEXT_LENGTH_4) {
            throw new Exception("FontText.length illegal!");
        }

        int fixHeight = 18;
        int fixWeight = 2;

        //1.画布
        BufferedImage bi = new BufferedImage(imageSize, imageSize / 2, BufferedImage.TYPE_4BYTE_ABGR);

        //2.画笔
        Graphics2D g2d = bi.createGraphics();

        //2.1设置画笔颜色
        g2d.setPaint(Color.RED);

        //2.2抗锯齿设置
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //3.写签名
        int marginWeight = fixWeight + lineSize;
        float marginHeight;
        FontRenderContext context = g2d.getFontRenderContext();
        Rectangle2D rectangle;
        Font f;

        if (font.getFontText().length() == FONT_TEXT_LENGTH_2) {
            if (addString != null && addString.trim().length() > 0) {
                bi = drawThreeFont(bi, g2d, font.setFontText(font.getFontText() + addString), lineSize, imageSize, fixHeight,
                        fixWeight, true);
            } else {
                f = new Font(font.getFontFamily(), Font.BOLD, font.getFontSize());
                g2d.setFont(f);
                rectangle = f.getStringBounds(font.getFontText().substring(0, 1), context);
                marginHeight = (float) (Math.abs(rectangle.getCenterY()) * 2 + marginWeight) + fixHeight - 4;
                g2d.drawString(font.getFontText().substring(0, 1), marginWeight, marginHeight);
                marginWeight += Math.abs(rectangle.getCenterX()) * 2 + (font.getFontSpace() == null ?
                        INIT_BEGIN :
                        font.getFontSpace());
                g2d.drawString(font.getFontText().substring(1), marginWeight, marginHeight);

                //拉伸
                BufferedImage nbi = new BufferedImage(imageSize, imageSize, bi.getType());
                Graphics2D ng2d = nbi.createGraphics();
                ng2d.setPaint(Color.RED);
                ng2d.drawImage(bi, 0, 0, imageSize, imageSize, null);

                //画正方形
                ng2d.setStroke(new BasicStroke(lineSize));
                ng2d.drawRect(0, 0, imageSize, imageSize);
                ng2d.dispose();
                bi = nbi;
            }
        } else if (font.getFontText().length() == FONT_TEXT_LENGTH_3) {
            if (addString != null && addString.trim().length() > 0) {
                bi = drawFourFont(bi, font.setFontText(font.getFontText() + addString), lineSize, imageSize, fixHeight,
                        fixWeight);
            } else {
                bi = drawThreeFont(bi, g2d, font.setFontText(font.getFontText()), lineSize, imageSize, fixHeight, fixWeight,
                        false);
            }
        } else {
            bi = drawFourFont(bi, font, lineSize, imageSize, fixHeight, fixWeight);
        }

        return bi;
    }

    /**
     * 将byte数组保存为本地文件
     *
     * @param buf      byte数组
     * @param fullPath 文件全路径
     * @throws IOException 异常
     */
    private static void storeBytes(byte[] buf, String fullPath) throws IOException {

        File file = new File(fullPath);
        try (FileOutputStream fos = new FileOutputStream(file);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {

            //1.如果父目录不存在，则创建
            File dir = file.getParentFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }

            //2.写byte数组到文件
            bos.write(buf);
        }
    }

    /**
     * 画三字
     *
     * @param bi        图片
     * @param g2d       原画笔
     * @param font      字体对象
     * @param lineSize  线宽
     * @param imageSize 图片尺寸
     * @param fixHeight 修复膏
     * @param fixWeight 修复宽
     * @param isWithYin 是否含有“印”
     */
    private static BufferedImage drawThreeFont(BufferedImage bi, Graphics2D g2d, SignFont font, int lineSize,
                                               int imageSize, int fixHeight, int fixWeight, boolean isWithYin) {
        fixHeight -= 9;
        int marginWeight = fixWeight + lineSize;
        //设置字体
        Font f = new Font(font.getFontFamily(), Font.BOLD, font.getFontSize());
        g2d.setFont(f);
        FontRenderContext context = g2d.getFontRenderContext();
        Rectangle2D rectangle = f.getStringBounds(font.getFontText().substring(0, 1), context);
        float marginHeight = (float) (Math.abs(rectangle.getCenterY()) * 2 + marginWeight) + fixHeight;
        int oldWeight = marginWeight;

        if (isWithYin) {
            g2d.drawString(font.getFontText().substring(2, 3), marginWeight, marginHeight);
            marginWeight += rectangle.getCenterX() * 2 + (font.getFontSpace() == null ? INIT_BEGIN : font.getFontSpace());
        } else {
            marginWeight += rectangle.getCenterX() * 2 + (font.getFontSpace() == null ? INIT_BEGIN : font.getFontSpace());
            g2d.drawString(font.getFontText().substring(0, 1), marginWeight, marginHeight);
        }

        //拉伸
        BufferedImage nbi = new BufferedImage(imageSize, imageSize, bi.getType());
        Graphics2D ng2d = nbi.createGraphics();
        ng2d.setPaint(Color.RED);
        ng2d.drawImage(bi, 0, 0, imageSize, imageSize, null);

        //画正方形
        ng2d.setStroke(new BasicStroke(lineSize));
        ng2d.drawRect(0, 0, imageSize, imageSize);
        ng2d.dispose();
        bi = nbi;

        g2d = bi.createGraphics();
        g2d.setPaint(Color.RED);
        g2d.setFont(f);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (isWithYin) {
            g2d.drawString(font.getFontText().substring(0, 1), marginWeight, marginHeight += fixHeight);
            rectangle = f.getStringBounds(font.getFontText(), context);
            marginHeight += Math.abs(rectangle.getHeight());
            g2d.drawString(font.getFontText().substring(1), marginWeight, marginHeight);
        } else {
            g2d.drawString(font.getFontText().substring(1, 2), oldWeight, marginHeight += fixHeight);
            rectangle = f.getStringBounds(font.getFontText(), context);
            marginHeight += Math.abs(rectangle.getHeight());
            g2d.drawString(font.getFontText().substring(2, 3), oldWeight, marginHeight);
        }
        return bi;
    }

    /**
     * 画四字
     *
     * @param bi        图片
     * @param font      字体对象
     * @param lineSize  线宽
     * @param imageSize 图片尺寸
     * @param fixHeight 修复膏
     * @param fixWeight 修复宽
     */
    private static BufferedImage drawFourFont(BufferedImage bi, SignFont font, int lineSize, int imageSize, int fixHeight,
                                              int fixWeight) {
        int marginWeight = fixWeight + lineSize;
        //拉伸
        BufferedImage nbi = new BufferedImage(imageSize, imageSize, bi.getType());
        Graphics2D ng2d = nbi.createGraphics();
        ng2d.setPaint(Color.RED);
        ng2d.drawImage(bi, 0, 0, imageSize, imageSize, null);

        //画正方形
        ng2d.setStroke(new BasicStroke(lineSize));
        ng2d.drawRect(0, 0, imageSize, imageSize);
        ng2d.dispose();
        bi = nbi;

        Graphics2D g2d = bi.createGraphics();
        g2d.setPaint(Color.RED);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        FontRenderContext context = g2d.getFontRenderContext();

        Font f = new Font(font.getFontFamily(), Font.BOLD, font.getFontSize());
        g2d.setFont(f);
        Rectangle2D rectangle = f.getStringBounds(font.getFontText().substring(0, 1), context);
        float marginHeight = (float) (Math.abs(rectangle.getCenterY()) * 2 + marginWeight) + fixHeight;

        g2d.drawString(font.getFontText().substring(2, 3), marginWeight, marginHeight);
        int oldWeight = marginWeight;
        marginWeight +=
                Math.abs(rectangle.getCenterX()) * 2 + (font.getFontSpace() == null ? INIT_BEGIN : font.getFontSpace());

        g2d.drawString(font.getFontText().substring(0, 1), marginWeight, marginHeight);
        marginHeight += Math.abs(rectangle.getHeight());

        g2d.drawString(font.getFontText().substring(3, 4), oldWeight, marginHeight);

        g2d.drawString(font.getFontText().substring(1, 2), marginWeight, marginHeight);

        return bi;
    }

    /**
     * 绘制圆弧形文字
     *
     * @param g2d          画笔
     * @param circleRadius 弧形半径
     * @param font         字体对象
     * @param isTop        是否字体在上部，否则在下部
     */
    private static void drawArcFont4Circle(Graphics2D g2d, int circleRadius, SignFont font, boolean isTop) {
        if (font == null) {
            return;
        }

        //1.字体长度
        int fontTextLen = font.getFontText().length();

        //2.字体大小，默认根据字体长度动态设定 TODO
        int fontSize = font.getFontSize() == null ? (55 - fontTextLen * 2) : font.getFontSize();

        //3.字体样式
        int fontStyle = font.isBold() ? Font.BOLD : Font.PLAIN;

        //4.构造字体
        Font f = new Font(font.getFontFamily(), fontStyle, fontSize);

        FontRenderContext context = g2d.getFontRenderContext();
        Rectangle2D rectangle = f.getStringBounds(font.getFontText(), context);

        //5.文字之间间距，默认动态调整
        double fontSpace;
        if (font.getFontSpace() != null) {
            fontSpace = font.getFontSpace();
        } else {
            if (fontTextLen == 1) {
                fontSpace = 0;
            } else {
                fontSpace = rectangle.getWidth() / (fontTextLen - 1) * 0.9;
            }
        }

        //6.距离外圈距离
        int marginSize = font.getMarginSize() == null ? INIT_BEGIN : font.getMarginSize();

        //7.写字
        double newRadius = circleRadius + rectangle.getY() - marginSize;
        double radianPerInterval = 2 * Math.asin(fontSpace / (2 * newRadius));

        double fix = 0.04;
        if (isTop) {
            fix = 0.18;
        }
        double firstAngle;
        if (!isTop) {
            if (fontTextLen % FONT_TEXT_LENGTH_2 == 1) {
                firstAngle = Math.PI + Math.PI / 2 - (fontTextLen - 1) * radianPerInterval / 2.0 - fix;
            } else {
                firstAngle = Math.PI + Math.PI / 2 - ((fontTextLen / 2.0 - 0.5) * radianPerInterval) - fix;
            }
        } else {
            if (fontTextLen % FONT_TEXT_LENGTH_2 == 1) {
                firstAngle = (fontTextLen - 1) * radianPerInterval / 2.0 + Math.PI / 2 + fix;
            } else {
                firstAngle = (fontTextLen / 2.0 - 0.5) * radianPerInterval + Math.PI / 2 + fix;
            }
        }

        for (int i = 0; i < fontTextLen; i++) {
            double theta;
            double thetaXaxis;
            double thetaYaxis;

            if (!isTop) {
                theta = firstAngle + i * radianPerInterval;
                thetaXaxis = newRadius * Math.sin(Math.PI / 2 - theta);
                thetaYaxis = newRadius * Math.cos(theta - Math.PI / 2);
            } else {
                theta = firstAngle - i * radianPerInterval;
                thetaXaxis = newRadius * Math.sin(Math.PI / 2 - theta);
                thetaYaxis = newRadius * Math.cos(theta - Math.PI / 2);
            }

            AffineTransform transform;
            if (!isTop) {
                transform = AffineTransform.getRotateInstance(Math.PI + Math.PI / 2 - theta);
            } else {
                transform = AffineTransform.getRotateInstance(Math.PI / 2 - theta + Math.toRadians(8));
            }
            Font f2 = f.deriveFont(transform);
            g2d.setFont(f2);
            g2d.drawString(font.getFontText().substring(i, i + 1), (float) (circleRadius + thetaXaxis + INIT_BEGIN),
                    (float) (circleRadius - thetaYaxis + INIT_BEGIN));
        }
    }

    /**
     * 绘制椭圆弧形文字
     *
     * @param g2d    画笔
     * @param circle 外围圆
     * @param font   字体对象
     * @param isTop  是否字体在上部，否则在下部
     */
    private static void drawArcFont4Oval(Graphics2D g2d, SignCircle circle, SignFont font, boolean isTop) {
        if (font == null) {
            return;
        }
        float radiusXaxis = circle.getWidth();
        float radiusYaxis = circle.getHeight();
        float radiusWidth = radiusXaxis + circle.getLineSize();
        float radiusHeight = radiusYaxis + circle.getLineSize();

        //1.字体长度
        int fontTextLen = font.getFontText().length();

        //2.字体大小，默认根据字体长度动态设定
        int fontSize = font.getFontSize() == null ? 25 + (10 - fontTextLen) / 2 : font.getFontSize();

        //3.字体样式
        int fontStyle = font.isBold() ? Font.BOLD : Font.PLAIN;

        //4.构造字体
        Font f = new Font(font.getFontFamily(), fontStyle, fontSize);

        //5.总的角跨度
        float totalArcAng = (float) (font.getFontSpace() * fontTextLen);

        //6.从边线向中心的移动因子
        float minRat = 0.90f;

        double startAngle = isTop ? -90f - totalArcAng / 2f : 90f - totalArcAng / 2f;
        double step = 0.5;
        int alCount = (int) Math.ceil(totalArcAng / step) + 1;
        double[] angleArr = new double[alCount];
        double[] arcLenArr = new double[alCount];
        int num = 0;
        double accArcLen = 0.0;
        angleArr[num] = startAngle;
        arcLenArr[num] = accArcLen;
        num++;
        double angRaxis = startAngle * Math.PI / 180.0;
        double lastXaxis = radiusXaxis * Math.cos(angRaxis) + radiusWidth;
        double lastYaxis = radiusYaxis * Math.sin(angRaxis) + radiusHeight;
        for (double i = startAngle + step; num < alCount; i += step) {
            angRaxis = i * Math.PI / 180.0;
            double x = radiusXaxis * Math.cos(angRaxis) + radiusWidth, y = radiusYaxis * Math.sin(angRaxis) + radiusHeight;
            accArcLen += Math.sqrt((lastXaxis - x) * (lastXaxis - x) + (lastYaxis - y) * (lastYaxis - y));
            angleArr[num] = i;
            arcLenArr[num] = accArcLen;
            lastXaxis = x;
            lastYaxis = y;
            num++;
        }
        double arcPer = accArcLen / fontTextLen;
        for (int i = 0; i < fontTextLen; i++) {
            double arcLeft = i * arcPer + arcPer / 2.0;
            double ang = 0.0;
            for (int p = 0; p < arcLenArr.length - 1; p++) {
                if (arcLenArr[p] <= arcLeft && arcLeft <= arcLenArr[p + 1]) {
                    ang = (arcLeft >= ((arcLenArr[p] + arcLenArr[p + 1]) / 2.0)) ? angleArr[p + 1] : angleArr[p];
                    break;
                }
            }
            angRaxis = (ang * Math.PI / 180f);
            Float x = radiusXaxis * (float) Math.cos(angRaxis) + radiusWidth;
            Float y = radiusYaxis * (float) Math.sin(angRaxis) + radiusHeight;
            double qxang = Math.atan2(radiusYaxis * Math.cos(angRaxis), -radiusXaxis * Math.sin(angRaxis));
            double fxang = qxang + Math.PI / 2.0;

            int subIndex = isTop ? i : fontTextLen - 1 - i;
            String c = font.getFontText().substring(subIndex, subIndex + 1);

            //获取文字高宽
            FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(f);
            int w = fm.stringWidth(c), h = fm.getHeight();

            if (isTop) {
                x += h * minRat * (float) Math.cos(fxang);
                y += h * minRat * (float) Math.sin(fxang);
                x += -w / 2f * (float) Math.cos(qxang);
                y += -w / 2f * (float) Math.sin(qxang);
            } else {
                x += (h * minRat) * (float) Math.cos(fxang);
                y += (h * minRat) * (float) Math.sin(fxang);
                x += w / 2f * (float) Math.cos(qxang);
                y += w / 2f * (float) Math.sin(qxang);
            }

            // 旋转
            AffineTransform affineTransform = new AffineTransform();
            affineTransform.scale(0.8, 1);
            if (isTop) {
                affineTransform.rotate(Math.toRadians((fxang * 180.0 / Math.PI - 90)), 0, 0);
            } else {
                affineTransform.rotate(Math.toRadians((fxang * 180.0 / Math.PI + 180 - 90)), 0, 0);
            }
            Font f2 = f.deriveFont(affineTransform);
            g2d.setFont(f2);
            g2d.drawString(c, x.intValue() + INIT_BEGIN, y.intValue() + INIT_BEGIN);
        }
    }

    /**
     * 画文字
     *
     * @param g2d          画笔
     * @param circleWidth  边线圆宽度
     * @param circleHeight 边线圆高度
     * @param font         字体对象
     */
    private static void drawFont(Graphics2D g2d, int circleWidth, int circleHeight, SignFont font) {
        if (font == null) {
            return;
        }

        //1.字体长度
        int fontTextLen = font.getFontText().length();

        //2.字体大小，默认根据字体长度动态设定
        int fontSize = font.getFontSize() == null ? (55 - fontTextLen * 2) : font.getFontSize();

        //3.字体样式
        int fontStyle = font.isBold() ? Font.BOLD : Font.PLAIN;

        //4.构造字体
        Font f = new Font(font.getFontFamily(), fontStyle, fontSize);
        g2d.setFont(f);

        FontRenderContext context = g2d.getFontRenderContext();
        String[] fontTexts = font.getFontText().split("\n");
        if (fontTexts.length > 1) {
            int y = 0;
            for (String fontText : fontTexts) {
                y += Math.abs(f.getStringBounds(fontText, context).getHeight());
            }
            //5.设置上边距
            float marginSize = INIT_BEGIN + (float) (circleHeight / 2 - y / 2);
            for (String fontText : fontTexts) {
                Rectangle2D rectangle2Dr = f.getStringBounds(fontText, context);
                g2d.drawString(fontText, (float) (circleWidth / 2 - rectangle2Dr.getCenterX() + 1), marginSize);
                marginSize += Math.abs(rectangle2Dr.getHeight());
            }
        } else {
            Rectangle2D rectangle2Dr = f.getStringBounds(font.getFontText(), context);
            //5.设置上边距，默认在中心
            float marginSize = font.getMarginSize() == null ?
                    (float) (circleHeight / 2 - rectangle2Dr.getCenterY()) :
                    (float) (circleHeight / 2 - rectangle2Dr.getCenterY()) + (float) font.getMarginSize();
            g2d.drawString(font.getFontText(), (float) (circleWidth / 2 - rectangle2Dr.getCenterX() + 1), marginSize);
        }
    }

    /**
     * 画圆
     *
     * @param g2d    画笔
     * @param circle 圆配置对象
     */
    private static void drawCicle(Graphics2D g2d, SignCircle circle, int x, int y) {
        if (circle == null) {
            return;
        }

        //1.圆线条粗细默认是圆直径的1/35
        int lineSize = circle.getLineSize() == null ? circle.getHeight() * 2 / (35) : circle.getLineSize();

        //2.画圆
        g2d.setStroke(new BasicStroke(lineSize));
        g2d.drawOval(x, y, circle.getWidth() * 2, circle.getHeight() * 2);
    }

}
