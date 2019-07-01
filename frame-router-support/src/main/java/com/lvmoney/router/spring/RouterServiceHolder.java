package com.lvmoney.router.spring;

import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lvmoney.router.config.PermissionValidatedConfig;
import com.lvmoney.router.annotations.AuthenticationValidated;
import com.lvmoney.router.annotations.ParamValidated;
import com.lvmoney.router.annotations.PermissionValidated;
import com.lvmoney.router.config.AuthenticationValidatedConfig;
import com.lvmoney.router.config.RouterMethodConfig;
import com.lvmoney.router.config.RouterServiceConfig;
import com.lvmoney.router.config.ParamValidatedConfig;

/**
 * @describe：根据定义的uri获得对应的接口实现方法
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年12月29日 上午11:32:20
 */
public class RouterServiceHolder {
    private static final Logger logger = LoggerFactory.getLogger(RouterServiceHolder.class);

    public Map<String, RouterServiceConfig> serviceMap = new java.util.concurrent.ConcurrentHashMap<>();

    private final String SPLIT = ".";

    private static class InstanceHolder {
        private static final RouterServiceHolder INSTANCE = new RouterServiceHolder();
    }

    private RouterServiceHolder() {
    }

    public static final RouterServiceHolder getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void put(RouterServiceConfig service) {
        serviceMap.put(service.getPath(), service);

        if (logger.isDebugEnabled()) {
            logger.info("service {} mapping {} ", service.getClazz().getName(), service.getPath());
        }
    }

    public RouterMethodConfig findMethodByUri(String uri) {
        if (StringUtils.isNotBlank(uri)) {
            String path = StringUtils.substringBeforeLast(uri, SPLIT);
            String methodName = StringUtils.substringAfterLast(uri, SPLIT);
            RouterServiceConfig service = serviceMap.get(path);
            if (service != null) {
                return service.getMethod(methodName);
            }
        }
        return null;
    }

    public ParamValidatedConfig findValidateByUri(String uri) {
        RouterMethodConfig RouterMethodConfig = this.findMethodByUri(uri);
//		Annotation[] annotations = RouterMethodConfig.getMethod().getAnnotations();
//		boolean isValidate=false;
//		for(Annotation annotations:annotations){
//			if(annotations instanceof ValidateMethod){
//				ValidateMethod ValidateMethod = (ValidateMethod)annotations;
//				isValidate = ValidateMethod.isValidate();
//				break;
//			}
//		}
        boolean isValidate = false;
        Method method = RouterMethodConfig.getMethod();
        if (method.isAnnotationPresent(ParamValidated.class)) {
            ParamValidated paramValidated = method.getAnnotation(ParamValidated.class);
            isValidate = paramValidated.isValidate();
        }
        return new ParamValidatedConfig(RouterMethodConfig.getMethod(), isValidate);
    }

    public AuthenticationValidatedConfig findAuthenticationByUri(String uri) {
        RouterMethodConfig RouterMethodConfig = this.findMethodByUri(uri);
        boolean isValidate = false;
        Method method = RouterMethodConfig.getMethod();
        if (method.isAnnotationPresent(AuthenticationValidated.class)) {
            AuthenticationValidated authValidated = method.getAnnotation(AuthenticationValidated.class);
            isValidate = authValidated.isValidate();
        }
        return new AuthenticationValidatedConfig(RouterMethodConfig.getMethod(), isValidate);
    }


    public PermissionValidatedConfig findPermissionByUri(String uri) {
        RouterMethodConfig RouterMethodConfig = this.findMethodByUri(uri);
        String role = "";
        Method method = RouterMethodConfig.getMethod();
        if (method.isAnnotationPresent(PermissionValidated.class)) {
            PermissionValidated permissionValidated = method.getAnnotation(PermissionValidated.class);
            role = permissionValidated.role();
        }
        return new PermissionValidatedConfig(RouterMethodConfig.getMethod(), role);
    }
}
