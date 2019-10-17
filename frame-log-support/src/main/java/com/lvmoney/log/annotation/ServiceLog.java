package com.lvmoney.log.annotation;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/1/31
 * Copyright xxxx科技有限公司
 */


import java.lang.annotation.*;

/**
 * @describe：定义注解，拦截service
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ServiceLog {
    //定义成员
    String decription() default "";
}
