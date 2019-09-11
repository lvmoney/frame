package com.lvmoney.mysql.separate.aspect;/**
 * 描述:
 * 包名:com.lvmoney.mysql.separate.aspect
 * 版本信息: 版本1.0
 * 日期:2019/9/6
 * Copyright XXXXXX科技有限公司
 */

import com.lvmoney.mysql.separate.holder.DsContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/9/6 14:50
 */


@Slf4j
@Aspect
@Order(1)
public class DataSourceAspect {

    /**
     * 切面启动时设置数据源从库列表名称
     *
     * @param slaves
     */
    public DataSourceAspect(List<String> slaves) {
        DsContextHolder.setSlaves(slaves);
    }

    @Pointcut("@annotation(com.lvmoney.mysql.separate.annotation.Slave) && execution(* com.lvmoney.mysql.separate.service.impl.*.*(..))")
    public void readPointcut() {
    }

    @Pointcut("@annotation(com.lvmoney.mysql.separate.annotation.Master) && execution(* com.lvmoney.mysql.separate.service.impl.*.*(..))")
    public void writePointcut() {
    }

    @Before("readPointcut()")
    public void readBefore(JoinPoint joinPoint) {
        DsContextHolder.slave();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        log.debug("{}.{} USE DATASOURCE SLAVE", className, methodName);
    }

    @After("readPointcut()")
    public void readAfter(JoinPoint joinPoint) {
        DsContextHolder.master();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        log.debug("{}.{} RESET DATASOURCE MASTER", className, methodName);
    }

    @Before("writePointcut()")
    public void writeBefore(JoinPoint joinPoint) {
        DsContextHolder.master();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        log.debug("{}.{} USE DATASOURCE MASTER", className, methodName);
    }
}
