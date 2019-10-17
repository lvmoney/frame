package com.lvmoney.office.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lvmoney.common.constant.CommonConstant;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.converter.core.XWPFConverterException;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

/**
 * @describe：未测试
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public class DocxUtil {
    //word模板的标签为【string】
    /**
     * 标签左侧
     */
    private static final String FLAG_L = "【";
    /**
     * 标签右侧
     */
    private static final String FLAG_R = "】";
    /**
     *
     */
    //private static final String REGEX = "(\\w|\\W)*\\【\\w+\\】(\\w|\\W)*";//正则匹配*【*】*类型字符串
    /**
     * 如果没有替换的将显示"  "
     */
    public static final String NULL_REPLACE = "   /   ";
    /**
     * 下载替换word模板标签
     */
    public static final String DOWNLOAD_REPLACE = "      ";
    /**
     * 预览替换word模板标签
     */
    public static final String PREVIEW_REPLACE = "   /   ";

    /**
     * <br>
     * 描 述: doc内容改变 <br>
     * 作 者: shizhenwei <br>
     * 历 史: (版本) 作者 时间 注释
     *
     * @param is     doc文档模板
     * @param params key value 将模板里的可以替换为响应VALUE
     * @return
     * @throws IOException
     */
    public static byte[] docContentChange(InputStream is, Map<String, String> params) throws IOException {
        if (null == params) {
            params = new HashMap<String, String>(CommonConstant.MAP_DEFAULT_SIZE);
        }
        HWPFDocument document = new HWPFDocument(is);
        Range range = document.getRange();

        Set<String> keys = params.keySet();
        for (String key : keys) {
            range.replaceText(key.toString(), params.get(key));
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        document.write(baos);
        byte[] bytes = baos.toByteArray();

        document.close();
        baos.close();
        return bytes;
    }

    /**
     * <br>描 述:    改变word内容，讲word标签【*】替换为指定内容
     * <br>作 者: shizhenwei
     * <br>历 史: (版本) 作者 时间 注释
     *
     * @return
     * @throws IOException
     * @throws XWPFConverterException
     */
    public static byte[] docxContentChange(InputStream is, Map<String, String> params) throws XWPFConverterException, IOException {
        return docxContentChange(is, params, NULL_REPLACE);
    }


    /**
     * <br>描 述: docx内容改变
     * <br>作 者: shizhenwei
     * <br>历 史: (版本) 作者 时间 注释
     *
     * @param is     docx文档模板
     * @param params key value 将模板里的可以替换为响应VALUE
     * @return
     * @throws IOException
     * @throws XWPFConverterException
     */
    public static byte[] docxContentChange(InputStream is, Map<String, String> params, String replace) throws XWPFConverterException, IOException {
        if (null == params) {
            params = new HashMap<String, String>(CommonConstant.MAP_DEFAULT_SIZE);
        }
        XWPFDocument document = new XWPFDocument(is);
        //替换段落内容
        List<XWPFParagraph> list = document.getParagraphs();
        for (XWPFParagraph paragraph : list) {
            replaceParagraph(paragraph, params, replace, true);
        }

        //替换表格内容
        List<XWPFTable> tableList = document.getTables();
        for (XWPFTable table : tableList) {
            List<XWPFTableRow> rows = table.getRows();
            for (XWPFTableRow row : rows) {
                List<XWPFTableCell> cells = row.getTableCells();
                for (XWPFTableCell cell : cells) {
                    List<XWPFParagraph> paras = cell.getParagraphs();
                    for (XWPFParagraph para : paras) {
                        replaceParagraph(para, params, replace, false);
                    }
                }
            }
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        document.write(baos);
        byte[] bytes = baos.toByteArray();
        document.close();
        baos.close();
        return bytes;
    }

    /**
     * <br>描 述:    将docx字节数组流转换为pdf字节数组流
     * <br>作 者: shizhenwei
     * <br>历 史: (版本) 作者 时间 注释
     *
     * @param docxBytes docx文档字节数组
     * @return
     * @throws XWPFConverterException
     * @throws IOException            注：需在部署系统安装word对应的中文字体
     */
    public static byte[] docx2pdf(byte[] docxBytes) throws XWPFConverterException, IOException {
        PdfOptions options = PdfOptions.create();
        XWPFDocument document = new XWPFDocument(new ByteArrayInputStream(docxBytes));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfConverter.getInstance().convert(document, baos, options);
        return baos.toByteArray();
    }


    /**
     * <br>描 述:    将Word模板流改变内容后转换为pdf字节数组流
     * <br>作 者: shizhenwei
     * <br>历 史: (版本) 作者 时间 注释
     *
     * @param is     docx文档输入流
     * @param params key value 将模板里的可以替换为响应VALUE
     * @return
     * @throws IOException
     * @throws XWPFConverterException * 注：需在部署系统安装word对应的中文字体
     */
    public static byte[] docx2pdf(InputStream is, Map<String, String> params, String replace) throws XWPFConverterException, IOException {
        if (null == params) {
            params = new HashMap<String, String>(CommonConstant.MAP_DEFAULT_SIZE);
        }
        XWPFDocument document = new XWPFDocument(is);
        //替换段落内容
        List<XWPFParagraph> list = document.getParagraphs();
        for (XWPFParagraph paragraph : list) {
            replaceParagraph(paragraph, params, replace, true);
        }

        //替换表格内容
        List<XWPFTable> tableList = document.getTables();
        for (XWPFTable table : tableList) {
            List<XWPFTableRow> rows = table.getRows();
            for (XWPFTableRow row : rows) {
                List<XWPFTableCell> cells = row.getTableCells();
                for (XWPFTableCell cell : cells) {
                    List<XWPFParagraph> paras = cell.getParagraphs();
                    for (XWPFParagraph para : paras) {
                        replaceParagraph(para, params, replace, false);
                    }
                }
            }
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfOptions options = PdfOptions.create();
        PdfConverter.getInstance().convert(document, baos, options);
        byte[] bytes = baos.toByteArray();
        document.close();
        baos.close();
        return bytes;
    }

    /**
     * @describe: 在替换的值左右延长两个空格长度
     * @param: [old]
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 10:08
     */
    public static String getReplaceValue(String old) {
        return " " + old + " ";
    }

    /**
     * <br>描 述: 替换段落内容
     * <br>作 者: shizhenwei
     * <br>历 史: (版本) 作者 时间 注释
     *
     * @param paragraph
     * @param params
     * @param replace
     * @param underline
     * @return
     */
    public static String replaceParagraph(XWPFParagraph paragraph, Map<String, String> params, String replace, boolean underline) {
        //字符串过长，调用matches方法报堆溢出错误
        /*String regex = REGEX;//【string】匹配
        if(!paragraph.getText().matches(regex)){
            continue;
        }*/
        //改为contains
        String pghTxt = paragraph.getText();
        if (!(pghTxt.contains(FLAG_L) && pghTxt.contains(FLAG_R))) {
            return null;
        }
        List<XWPFRun> runs = paragraph.getRuns();
        String key = "";
        //left=true 说明有【
        boolean left = false;
        //right=true 说明有】
        boolean right = false;
        //这里没有做到百分百细致化，如果run中出现这样的形式 *【*】*】或者*】*【*】，将会存在问题，所以要监管好模板。
        for (int i = 0; i < runs.size(); i++) {
            XWPFRun run = runs.get(i);
            String runText = run.text();
            if (runText.contains(FLAG_L) && runText.contains(FLAG_R)) {
                key = runText.substring(runText.indexOf(FLAG_L) + 1, runText.indexOf(FLAG_R));
                String text = runText.replaceAll(FLAG_L + key + FLAG_R, StringUtils.isEmpty(params.get(key)) ? replace : getReplaceValue(params.get(key)));
                run.setText(text, 0);
                key = "";
                addUnderline(run, underline);
            } else if (runText.contains(FLAG_L)) {
                left = true;
                String temp = runText.substring(runText.indexOf(FLAG_L) + 1);
                key += temp;
                String text = runText.replaceAll(FLAG_L + temp, "");
                run.setText(text, 0);
                addUnderline(run, underline);
            } else if (runText.contains(FLAG_R)) {
                right = true;
                String temp = runText.substring(0, runText.indexOf(FLAG_R));
                key += temp;
                String text = runText.replaceAll(temp + FLAG_R, (StringUtils.isEmpty(params.get(key)) ? replace : getReplaceValue(params.get(key))));
                run.setText(text, 0);
                key = "";
                left = false;
                right = false;
                addUnderline(run, underline);
            } else {
                if (left == true && right == false) {
                    key += runText;
                    run.setText("", 0);
                    addUnderline(run, underline);
                }
                if (left == false) {
                    continue;
                }
            }
        }
        return StringUtils.isEmpty(params.get(key)) ? replace : getReplaceValue(params.get(key));
    }

    /**
     * <br>描 述: 添加下划线
     * <br>作 者: shizhenwei
     * <br>历 史: (版本) 作者 时间 注释
     *
     * @param run
     * @param underline
     * @return
     */
    public static XWPFRun addUnderline(XWPFRun run, boolean underline) {
        //是否添加下划线
        if (underline) {
            run.setUnderline(UnderlinePatterns.SINGLE);
        } else {
            run.setUnderline(UnderlinePatterns.NONE);
        }
        return run;
    }
}
