/**
 * 描述:
 * 包名:com.lvmoney.router.annotations
 * 版本信息: 版本1.0
 * 日期:2019年1月9日  下午3:55:20
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.router.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @describe：认证校验
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月9日 下午3:55:20
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface AuthenticationValidated {
    boolean isValidate() default true;
}
