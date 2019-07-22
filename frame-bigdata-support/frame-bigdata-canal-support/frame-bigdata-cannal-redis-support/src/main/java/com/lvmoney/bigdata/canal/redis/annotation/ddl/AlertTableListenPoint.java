package com.lvmoney.bigdata.canal.redis.annotation.ddl;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.lvmoney.bigdata.canal.redis.annotation.ListenPoint;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 修改字段属性监听器
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ListenPoint(eventType = CanalEntry.EventType.ALTER)
public @interface AlertTableListenPoint {
    /**
     * canal 指令
     * default for all
     */
    @AliasFor(annotation = ListenPoint.class)
    String destination() default "";

    /**
     * 数据库实例
     */
    @AliasFor(annotation = ListenPoint.class)
    String[] schema() default {};

    /**
     * 监听的表
     * default for all
     */
    @AliasFor(annotation = ListenPoint.class)
    String[] table() default {};
}
