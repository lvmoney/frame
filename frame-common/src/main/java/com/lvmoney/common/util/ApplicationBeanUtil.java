package com.lvmoney.common.util;
/**
 * 描述:
 * 包名:com.lvmoney.common.util
 * 版本信息: 版本1.0
 * 日期:2019/2/28
 * Copyright xxxx科技有限公司
 */

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @describe：通过spring的ApplicationContext获取上下文操作bean
 * @author: lvmoney/xxxx科技有限公司
 * @version:v1.0 2019/2/28 10:05
 */
@Component
public class ApplicationBeanUtil implements ApplicationContextAware {
    /**
     * 上下文
     */
    private static ApplicationContext applicationContext = null;

    /**
     * 获取静态变量中的ApplicationContext.
     */
    public static ApplicationContext getApplicationContext() {
        return ApplicationBeanUtil.applicationContext;
    }

    /**
     * 默认构造方法，注入上下文
     *
     * @param applicationContext:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/16 14:36
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationBeanUtil.applicationContext = applicationContext;
    }

    /**
     * 获取 bean
     *
     * @param clazz:
     * @throws
     * @return: T
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/16 14:36
     */
    public static <T> T getBean(Class<T> clazz) {
        T obj;
        try {
            //从上下文获取 bean
            obj = getApplicationContext().getBean(clazz);
        } catch (Exception e) {
            obj = null;
        }
        //返回 bean
        return obj;
    }

    /**
     * 获取 bean 的类型
     *
     * @param clazz:
     * @throws
     * @return: java.util.List<T>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/16 14:36
     */
    public static <T> List<T> getListBeansOfType(Class<T> clazz) {
        //声明一个结果
        Map<String, T> map;
        try {
            //获取类型
            map = getApplicationContext().getBeansOfType(clazz);
        } catch (Exception e) {
            map = null;
        }
        //返回 bean 的类型
        return map == null ? null : new ArrayList<>(map.values());
    }

    /**
     * 获取 bean 的类型
     *
     * @param clazz:
     * @throws
     * @return: java.util.Map<java.lang.String, T>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/16 14:36
     */
    public static <T> Map<String, T> getMapBeansOfType(Class<T> clazz) {
        //声明一个结果
        Map<String, T> map;
        try {
            //获取类型
            map = getApplicationContext().getBeansOfType(clazz);
        } catch (Exception e) {
            map = null;
        }
        //返回 bean 的类型
        return map;
    }


    /**
     * 获取所有被注解的 bean
     *
     * @param anno:
     * @throws
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/16 14:35
     */
    public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> anno) {
        Map<String, Object> map;
        try {
            //获取注解的 bean
            map = getApplicationContext().getBeansWithAnnotation(anno);
        } catch (Exception e) {
            map = null;
        }
        return map;
    }

    /**
     * 获取当前用户的token
     *
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/2 16:50
     */
    public static String getToken() {
        HttpServletRequest request = getHttpServletRequest();
        String token = request.getHeader("token");
        return token;
    }

    /**
     * 获得当前的HttpServletRequest
     *
     * @throws
     * @return: javax.servlet.http.HttpServletRequest
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/2 17:21
     */
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获得当前的HttpServletResponse
     *
     * @throws
     * @return: javax.servlet.http.HttpServletResponse
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/2 17:27
     */
    public static HttpServletResponse getHttpServletResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }
}
