/**
 * 描述:
 * 包名:com.lvmoney.router.annotation
 * 版本信息: 版本1.0
 * 日期:2019年1月2日  上午10:13:50
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.router.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @describe：方法入参校验
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月2日 上午10:13:50
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ParamValidated {
    boolean isValidate() default true;
}
