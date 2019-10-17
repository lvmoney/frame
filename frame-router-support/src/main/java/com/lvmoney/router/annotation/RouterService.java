package com.lvmoney.router.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @describe：调用接口注解
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月2日 上午11:53:39
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface RouterService {

    String path() default "";

}
