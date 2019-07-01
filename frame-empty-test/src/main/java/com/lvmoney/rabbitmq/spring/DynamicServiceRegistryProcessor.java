package com.lvmoney.rabbitmq.spring;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import com.lvmoney.rabbitmq.annotation.DynamicService;
import com.lvmoney.rabbitmq.config.DynamicServiceConfig;

@Component
public class DynamicServiceRegistryProcessor implements BeanPostProcessor, ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(DynamicServiceRegistryProcessor.class);

    private ApplicationContext applicationContext = null;

    private DynamicServiceHolder serviceHolder = DynamicServiceHolder.getInstance();

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
        DynamicService serviceAnnotation = AnnotationUtils.findAnnotation(clazz, DynamicService.class);
        if (serviceAnnotation != null) {
            DynamicServiceConfig serviceConfig = new DynamicServiceConfig(clazz, bean,
                    applicationContext, beanName);

            if (logger.isDebugEnabled()) {
                logger.info("regist routerService {}", clazz.getName());
            }
            serviceHolder.put(serviceConfig);
        }
        return bean;
    }
}