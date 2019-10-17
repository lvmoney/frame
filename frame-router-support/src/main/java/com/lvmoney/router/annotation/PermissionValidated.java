/**
 * 描述:
 * 包名:com.lvmoney.router.annotation
 * 版本信息: 版本1.0
 * 日期:2019年1月9日  下午3:54:49
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.router.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @describe：权限校验
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月9日 下午3:54:49
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface PermissionValidated {
    String role() default "";
}
