package com.lvmoney.common.utils;/**
 * 描述:
 * 包名:com.lvmoney.common.utils
 * 版本信息: 版本1.0
 * 日期:2019/9/7
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/9/7 14:31
 */
public class MapUtil {
    public static int initMapSize(int sum) {
        return (int) (sum / 0.75 + 1);
    }

    public static void main(String[] args) {
        System.out.println(initMapSize(7));

        System.out.println(1 / 0.75);
    }
}
