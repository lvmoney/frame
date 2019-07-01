package com.lvmoney.common.web;

import com.lvmoney.common.exceptions.BusinessException;
import com.lvmoney.common.exceptions.FrameRuntimeException;
import com.lvmoney.common.utils.ResultUtil;
import com.lvmoney.common.utils.vo.ResultData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 框架异常统一处理类
 *
 * @author AXM
 */
@ControllerAdvice
@RestControllerAdvice
public class FrameExceptionHandlerAdvice {
    private static final Log logger = LogFactory.getLog(FrameExceptionHandlerAdvice.class);

    @ExceptionHandler(Exception.class)
    public ResultData<?> handleException(HttpServletRequest req, HttpServletResponse res, Exception e) {
        logger.error("系统错误", e);
        return ResultUtil.error(600, "系统错误");
    }

    @ExceptionHandler(FrameRuntimeException.class)
    public ResultData<?> handleException(HttpServletRequest req, HttpServletResponse res, FrameRuntimeException e) {
        logger.error("系统错误", e);
        return ResultUtil.error(e.getCode(), e.getMessage());
    }


    @ExceptionHandler(BusinessException.class)
    public ResultData<?> handleBusinessException(HttpServletRequest req, HttpServletResponse res, BusinessException e) {
        logger.error("系统错误", e);
        return ResultUtil.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public ResultData<?> handleBindException(HttpServletRequest req, HttpServletResponse res, BindException e) {
        BindException bException = (BindException) e;
        List<BeanValidateExceptionVo> excepitonVos = new ArrayList<>();
        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        for (FieldError error : bException.getBindingResult().getFieldErrors()) {
            BeanValidateExceptionVo excepitonVo = new BeanValidateExceptionVo();
            excepitonVo.setDefaultMessage(error.getDefaultMessage());
            excepitonVo.setField(error.getField());
            excepitonVo.setRejectedValue(error.getRejectedValue());
            excepitonVos.add(excepitonVo);
        }
        return ResultUtil.error(4041, "请求参数错误", excepitonVos);
    }
}
