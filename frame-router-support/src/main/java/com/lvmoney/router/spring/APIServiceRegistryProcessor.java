package com.lvmoney.router.spring;


import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import com.lvmoney.router.annotation.RouterMethod;
import com.lvmoney.router.annotation.RouterService;
import com.lvmoney.router.config.RouterMethodConfig;
import com.lvmoney.router.config.RouterServiceConfig;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Component
public class ApiServiceRegistryProcessor implements BeanPostProcessor, ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiServiceRegistryProcessor.class);

    private ApplicationContext applicationContext = null;

    private RouterServiceHolder serviceHolder = RouterServiceHolder.getInstance();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clazz = AopUtils.getTargetClass(bean);
        RouterService serviceAnnotation = AnnotationUtils.findAnnotation(clazz, RouterService.class);
        if (serviceAnnotation != null) {
            RouterServiceConfig serviceConfig = new RouterServiceConfig(clazz, bean, serviceAnnotation.path(),
                    applicationContext, beanName);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.info("regist routerService {}", clazz.getName());
            }
            serviceHolder.put(serviceConfig);
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                RouterMethod methodAnnotation = AnnotationUtils.findAnnotation(method, RouterMethod.class);
                if (methodAnnotation != null) {
                    if (!method.isAccessible()) {
                        method.setAccessible(true);
                    }
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    int parameterCount = parameterTypes == null ? 0 : parameterTypes.length;
                    if (parameterCount > 1) {
                        LOGGER.error("method {}#{} parameter count is {} ignore it!!!", serviceConfig.getClazz().getName(),
                                method.getName(), parameterCount);
                    } else {
                        Class<?> parameterType = null;
                        if (parameterCount == 1) {
                            parameterType = method.getParameterTypes()[0];
                        }
                        RouterMethodConfig methodConfig = new RouterMethodConfig(method, methodAnnotation.name(), parameterType);
                        serviceConfig.addMethod(methodConfig);

                        if (LOGGER.isDebugEnabled()) {
                            LOGGER.info("method {}#{} mapping {}.{} ", serviceConfig.getClazz().getName(),
                                    method.getName(), serviceConfig.getPath(), methodConfig.getName());
                        }
                    }
                }
            }

        }
        return bean;
    }
}