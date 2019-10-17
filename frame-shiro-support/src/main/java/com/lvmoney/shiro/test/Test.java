package com.lvmoney.shiro.test;/**
 * 描述:
 * 包名:com.lvmoney.shiro.test
 * 版本信息: 版本1.0
 * 日期:2019/10/8
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/10/8 11:42
 */
public class Test {
    public static void main(String[] args) {
        method("");
        System.out.println(testRe());
    }

    public static void method(String param) {
        switch (param) {
            // 肯定不是进入这里
            case "sth":
                System.out.println("it's sth");
                break;
            // 也不是进入这里
            case "null":
                System.out.println("it's null");
                break;
            // 也不是进入这里
            default:
                System.out.println("default");
        }
    }

    public static String testRe(){
        try{
            return "test";
        }catch(Exception e){

        }finally {
            return "11111";
        }
    }
}
