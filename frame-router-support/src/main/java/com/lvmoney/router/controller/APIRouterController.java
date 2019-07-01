/**
 * 描述:
 * 包名:com.lvmoney.controller
 * 版本信息: 版本1.0
 * 日期:2018年12月29日  上午10:59:59
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.router.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.lvmoney.common.exceptions.BusinessException;
import com.lvmoney.common.exceptions.CommonException;
import com.lvmoney.common.utils.JsonUtil;
import com.lvmoney.common.utils.vo.ResultData;
import com.lvmoney.jwt.annotations.NotToken;
import com.lvmoney.router.config.RouterMethodConfig;
import com.lvmoney.router.config.AuthenticationValidatedConfig;
import com.lvmoney.router.config.ParamValidatedConfig;
import com.lvmoney.router.config.PermissionValidatedConfig;
import com.lvmoney.router.constant.ResultCodeEnum;
import com.lvmoney.router.spring.RouterServiceHolder;
import com.lvmoney.router.vo.global.DataVo;
import com.lvmoney.router.vo.global.PostRequestVo;
import com.lvmoney.router.web.BuildRequestHandler;
import com.lvmoney.router.web.HibernateValidationHandler;
import com.lvmoney.router.web.JwtTokenHandler;
import com.lvmoney.router.web.ShiroAuthHandler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Set;

/**
 * @describe：1、接口调用统一入口，根据配置自动路由到不同的接口 接口错误异常直接在接口实现类中抛出 throw new
 * BusinessException(CommonException.Proxy.
 * METHOD_NOT_MAP, String.format(
 * "%s is not mapping", uri)); 2、
 * 集成hibernate的
 * validate，如果要使用，使用自定义的方法注解@ValidateMethod
 * 3、方法参数的入参以vo的形势，单实体
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年12月29日 上午10:59:59
 */
@RestController
@RequestMapping("route")
public class APIRouterController {
    private final static Logger logger = LoggerFactory.getLogger(APIRouterController.class);

    @Autowired
    JwtTokenHandler jwtTokenHandler;
    @Autowired
    ShiroAuthHandler shiroAuthHandler;
    @Autowired(required = false)
    private List<BuildRequestHandler> handlers = new ArrayList<>();
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception 2018年12月29日下午2:46:02
     * @describe:get:http://xxxx:port//xxx?uri=test.test&data= {"data":{
     * "name":"test",
     * "address":"teat"}
     * ,
     * "globalParam":""
     * } 如果调用方法没有入参，
     * 可以不写data参数或者不写data
     * .data
     * @author: lvmoney /xxxx科技有限公司
     */
    @SuppressWarnings("rawtypes")
    @NotToken
    @RequestMapping(value = "proxyGet", method = RequestMethod.GET)
    public ResultData getProxy(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String data = request.getParameter("data");
        String uri = request.getParameter("uri");
        logger.debug("uri:{},data:{}", uri, data);
        return execMethod(request, uri, data);
    }

    /**
     * @param request
     * @param response
     * @param postRequestVo
     * @return
     * @throws Exception 2018年12月29日下午3:21:06
     * @describe: /**
     * @describe:post:http://xxxx:port//xxx requestbody:{"data":{
     * <p>
     * "data":{"name":"test","address"
     * :"teat"}, "globalParam":""
     * <p>
     * }, "uri":"testService.test" }
     * 如果调用方法没有入参，可以不写data参数或者不写data.data
     * @author: lvmoney /xxxx科技有限公司
     */
    @SuppressWarnings("rawtypes")
    @NotToken
    @RequestMapping(value = "proxyPost", method = RequestMethod.POST)
    public ResultData postProxy(HttpServletRequest request, HttpServletResponse response,
                                @RequestBody PostRequestVo postRequestVo) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String data = JsonUtil.t2JsonString(postRequestVo.getData());
        String uri = postRequestVo.getUri();
        return execMethod(request, uri, data);
    }

    private void validate(Object obj, Class<?>... groups) throws ValidationException {
        Set<ConstraintViolation<Object>> constraintViolations = null;
        if (groups == null || groups.length == 0) {
            constraintViolations = validator.validate(obj);
        } else {
            constraintViolations = validator.validate(obj, groups);
        }
        if (constraintViolations != null && !constraintViolations.isEmpty()) {
            StringBuilder errorInfo = new StringBuilder();
            for (ConstraintViolation<Object> cv : constraintViolations) {
                errorInfo.append(cv.getPropertyPath().toString());
                errorInfo.append(" ");
                errorInfo.append(cv.getMessage() + "\r\n");
            }
            throw new BusinessException(CommonException.Proxy.PARAMETER_ERROR, errorInfo.toString());
        }
    }

    @SuppressWarnings("rawtypes")
    private void validataDataVo(DataVo vo) {
        try {
            validate(vo);
        } catch (ValidationException e) {
            Throwable cause = e.getCause();
            if (cause instanceof BusinessException) {
                throw (BusinessException) cause;
            }
            throw e;
        }
    }

    @SuppressWarnings("rawtypes")
    private void buildRequestHandler(HttpServletRequest request, DataVo vo) {
        for (BuildRequestHandler handler : handlers) {
            handler.handle(request, vo.getData());
        }
    }

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(Throwable.class)
    protected ResultData exceptionHandler(Exception ex, HttpServletRequest request) {
        logger.error("", ex);
        if (ex instanceof BusinessException) {
            BusinessException be = (BusinessException) ex;
            return new ResultData(be.getCode(), be.getDescription());
        } else {
            return new ResultData(CommonException.Proxy.OTHER.getCode(), ex.getMessage());
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private ResultData execMethod(HttpServletRequest request, String uri, String data) throws Exception {
        logger.debug("uri:{},data:{}", uri, data);
        // 获取 映射的service方法
        RouterMethodConfig routerMethodConfig = RouterServiceHolder.getInstance().findMethodByUri(uri);
        if (routerMethodConfig == null) {
            throw new BusinessException(CommonException.Proxy.METHOD_NOT_MAP, String.format("%s is not mapping", uri));
        }
        if (logger.isDebugEnabled()) {
            logger.debug("find mapping uri:{},service:{},method:{},parameterType:{}", uri,
                    routerMethodConfig.getRouterServiceConfig().getClazz().getName(),
                    routerMethodConfig.getMethod().getName(),
                    routerMethodConfig.hasParameter() ? routerMethodConfig.getParameterType().getName() : "none");
        }

        //jwt token认证校验
        AuthenticationValidatedConfig authConfig = RouterServiceHolder.getInstance().findAuthenticationByUri(uri);
        if (authConfig.isValidate()) {
            boolean loginState = jwtTokenHandler.tokenValidated(request);
            if (!loginState) {
                throw new BusinessException(CommonException.Proxy.TOKEN_VERIFY_ERROR);
            }
        }

        //shiro 权限认证校验

        PermissionValidatedConfig permissionConfig = RouterServiceHolder.getInstance().findPermissionByUri(uri);
        String role = permissionConfig.getRole();
        boolean authState = shiroAuthHandler.AuthenticationValidated(request, role);
        if (!authState) {
            throw new BusinessException(CommonException.Proxy.TOKEN_VERIFY_ERROR);
        }

        // 根据方法参数类型 构建VO
        DataVo dataVo = null;
        try {
            if (routerMethodConfig.hasParameter()) {
                dataVo = JsonUtil.json2Obj(data, DataVo.class, routerMethodConfig.getParameterType());
            } else {
                // dataVo = JsonUtil.json2Obj(data, DataVo.class);
                dataVo = JSON.parseObject(data, DataVo.class);
            }
        } catch (Exception e) {
            logger.error("解析接口业务参数出错{}", e.getMessage());
            throw new BusinessException(CommonException.Proxy.JSON_ERROR, e.getCause().getMessage());
        }


        if (dataVo != null) {//如果入参不为空，对入参进行参数校验
            this.validataDataVo(dataVo);
            ParamValidatedConfig paramValidatedConfig = RouterServiceHolder.getInstance().findValidateByUri(uri);
            if (paramValidatedConfig.isValidate()) {
                HibernateValidationHandler.validate(dataVo.getData());
            }
            this.buildRequestHandler(request, dataVo);
        }
        // 调用 service方法
        Object methodResult = null;
        try {
            if (routerMethodConfig.hasParameter()) {
                methodResult = routerMethodConfig.getMethod()
                        .invoke(routerMethodConfig.getRouterServiceConfig().getBeanInstance(), dataVo.getData());
            } else {
                methodResult = routerMethodConfig.getMethod()
                        .invoke(routerMethodConfig.getRouterServiceConfig().getBeanInstance());
            }
        } catch (InvocationTargetException ex) {
            if (ex.getCause() instanceof BusinessException) {
                BusinessException be = (BusinessException) ex.getCause();
                throw be;
            } else {
                throw ex;
            }
        } catch (Throwable t) {
            logger.error("动态调用方法报错:{}", t.getMessage());
            throw new BusinessException(CommonException.Proxy.METHOD_INVOKE_ERROR);
        }

        // 判断返回值
        if (methodResult != null && methodResult.toString().length() > 2) {
            return new ResultData(ResultCodeEnum.SUCESS.getCode(), ResultCodeEnum.SUCESS.getDescription(),
                    methodResult);
        } else {
            return new ResultData(ResultCodeEnum.SUCESS.getCode(), ResultCodeEnum.SUCESS.getDescription());
        }

    }
}
