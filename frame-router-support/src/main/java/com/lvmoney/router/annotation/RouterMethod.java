package com.lvmoney.router.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @describe：调用的方法注解
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月2日 上午11:53:59
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface RouterMethod {

    String name() default "";

    // not use
    // boolean offlineAble = true;

}
