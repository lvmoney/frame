/**
 * 描述:
 * 包名:com.lvmoney.hotel.utils
 * 版本信息: 版本1.0
 * 日期:2018年12月4日  下午1:57:49
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年12月4日 下午1:57:49
 */

public class StringUtil extends StringUtils {
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * @describe: 不足左侧补零, 超过返回原值
     * @param: [str, strLength]
     * @return: java.lang.String
     * @author： lvmoney /xxxx科技有限公司
     * 2019/3/9
     */
    public static String addZeroForNum(String str, int strLength) {
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                sb.append("0").append(str);// 左补0
                // sb.append(str).append("0");//右补0
                str = sb.toString();
                strLen = str.length();
            }
        }
        return str;
    }


    /**
     * 16进制转 byte[]
     *
     * @param hexString
     * @return
     */
    public static byte[] hexToByte(String hexString) {
        if (StringUtils.isEmpty(hexString))
            return null;
        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() >> 1];
        int index = 0;
        for (int i = 0; i < hexString.length(); i++) {
            if (index > hexString.length() - 1)
                return byteArray;
            byte highDit = (byte) (Character.digit(hexString.charAt(index), 16) & 0xFF);
            byte lowDit = (byte) (Character.digit(hexString.charAt(index + 1), 16) & 0xFF);
            byteArray[i] = (byte) (highDit << 4 | lowDit);
            index += 2;
        }
        return byteArray;
    }

    // 将Unicode字符串转换成01字符串
    public static String strToBinStr(String str) {
        return boolArrToBinStr(strToBool(str));
    }

    // 将01字符串转换成Unicode字符串
    public static String binStrToStr(String arrStr) {
        return boolToStr(binStrToBoolArr(arrStr));
    }

    // 将Unicode字符串转换成01字符串
    public static int[] strToBinArr(String str) {
        return boolArrToBinArr(strToBool(str));
    }

    // 将01数组转换成Unicode字符串
    public static String binArrToStr(int[] intArr) {
        return boolToStr(binArrToBoolArr(intArr));
    }

    // 将bool型数组转换成01数组
    public static int[] boolArrToBinArr(boolean[] boolArr) {
        int[] intArr = new int[boolArr.length];
        for (int i = 0; i < boolArr.length; i++) {
            intArr[i] = boolArr[i] ? 1 : 0;
        }
        return intArr;
    }

    // 将01数组转换成bool型数组
    public static boolean[] binArrToBoolArr(int[] intArr) {
        boolean[] boolArr = new boolean[intArr.length];
        for (int i = 0; i < intArr.length; i++) {
            boolArr[i] = intArr[i] == 1 ? true : false;
        }
        return boolArr;
    }

    // 将bool型数组转换成二进制字符串
    public static String boolArrToBinStr(boolean[] boolArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (boolean b : boolArr) {
            if (b) {
                stringBuffer.append("1");
            } else {
                stringBuffer.append("0");
            }
        }
        return stringBuffer.toString();
    }

    // 将二进制字符串转换成bool型数组
    public static boolean[] binStrToBoolArr(String str) {
        char[] chs = str.toCharArray();
        boolean[] boolArr = new boolean[chs.length];
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == '0') {
                boolArr[i] = false;
            } else {
                boolArr[i] = true;
            }
        }
        return boolArr;
    }

    // 将Unicode字符串转换成bool型数组
    public static boolean[] strToBool(String input) {
        boolean[] output = binstr16ToBool(binstrToBinstr16(strToBinstr(input)));
        return output;
    }

    // 将bool型数组转换成Unicode字符串
    public static String boolToStr(boolean[] input) {
        String output = binstrToStr(binstr16ToBinstr(boolToBinstr16(input)));
        return output;
    }

    // 将字符串转换成二进制字符串，以空格相隔
    private static String strToBinstr(String str) {
        char[] strChar = str.toCharArray();
        String result = "";
        for (int i = 0; i < strChar.length; i++) {
            result += Integer.toBinaryString(strChar[i]) + " ";
        }
        return result;
    }

    // 将二进制字符串转换成Unicode字符串
    private static String binstrToStr(String binStr) {
        String[] tempStr = strToStrArray(binStr);
        char[] tempChar = new char[tempStr.length];
        for (int i = 0; i < tempStr.length; i++) {
            tempChar[i] = binstrToChar(tempStr[i]);
        }
        return String.valueOf(tempChar);
    }

    // 将二进制字符串格式化成全16位带空格的Binstr
    private static String binstrToBinstr16(String input) {
        StringBuffer output = new StringBuffer();
        String[] tempStr = strToStrArray(input);
        for (int i = 0; i < tempStr.length; i++) {
            for (int j = 16 - tempStr[i].length(); j > 0; j--)
                output.append('0');
            output.append(tempStr[i] + " ");
        }
        return output.toString();
    }

    // 将全16位带空格的Binstr转化成去0前缀的带空格Binstr
    private static String binstr16ToBinstr(String input) {
        StringBuffer output = new StringBuffer();
        String[] tempStr = strToStrArray(input);
        for (int i = 0; i < tempStr.length; i++) {
            for (int j = 0; j < 16; j++) {
                if (tempStr[i].charAt(j) == '1') {
                    output.append(tempStr[i].substring(j) + " ");
                    break;
                }
                if (j == 15 && tempStr[i].charAt(j) == '0')
                    output.append("0" + " ");
            }
        }
        return output.toString();
    }

    // 二进制字串转化为boolean型数组 输入16位有空格的Binstr
    private static boolean[] binstr16ToBool(String input) {
        String[] tempStr = strToStrArray(input);
        boolean[] output = new boolean[tempStr.length * 16];
        for (int i = 0, j = 0; i < input.length(); i++, j++)
            if (input.charAt(i) == '1')
                output[j] = true;
            else if (input.charAt(i) == '0')
                output[j] = false;
            else
                j--;
        return output;
    }

    // boolean型数组转化为二进制字串 返回带0前缀16位有空格的Binstr
    private static String boolToBinstr16(boolean[] input) {
        StringBuffer output = new StringBuffer();
        for (int i = 0; i < input.length; i++) {
            if (input[i])
                output.append('1');
            else
                output.append('0');
            if ((i + 1) % 16 == 0)
                output.append(' ');
        }
        output.append(' ');
        return output.toString();
    }

    // 将二进制字符串转换为char
    private static char binstrToChar(String binStr) {
        int[] temp = binstrToIntArray(binStr);
        int sum = 0;
        for (int i = 0; i < temp.length; i++) {
            sum += temp[temp.length - 1 - i] << i;
        }
        return (char) sum;
    }

    // 将初始二进制字符串转换成字符串数组，以空格相隔
    private static String[] strToStrArray(String str) {
        return str.split(" ");
    }

    // 将二进制字符串转换成int数组
    private static int[] binstrToIntArray(String binStr) {
        char[] temp = binStr.toCharArray();
        int[] result = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            result[i] = temp[i] - 48;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(StringUtil.addZeroForNum("123", 1));
    }
}
