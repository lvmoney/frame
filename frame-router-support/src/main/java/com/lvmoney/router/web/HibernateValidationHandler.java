/**
 * 描述:
 * 包名:com.lvmoney.router.utils
 * 版本信息: 版本1.0
 * 日期:2018年12月29日  下午5:12:15
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.router.web;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年12月29日 下午5:12:15
 */

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.validator.HibernateValidator;

import com.lvmoney.common.exceptions.BusinessException;
import com.lvmoney.common.exceptions.CommonException;

public class HibernateValidationHandler {

    /**
     * 使用hibernate的注解来进行验证
     *
     */
    private static Validator validator = Validation
            .byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();

    /**
     * 功能描述: <br>
     * 〈注解验证参数〉
     *
     * @param obj
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static <T> void validate(T obj) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
        // 抛出检验异常
        if (constraintViolations.size() > 0) {
            throw new BusinessException(CommonException.Proxy.PARAMETER_ERROR, String.format("参数校验失败:%s", constraintViolations.iterator().next().getMessage()));
        }
    }
}
