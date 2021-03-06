package com.lvmoney.bigdata.canal.redis.annotation.ddl;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.lvmoney.bigdata.canal.redis.annotation.ListenPoint;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;
/**
 * @describe：創建索引操作
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ListenPoint(eventType = CanalEntry.EventType.CINDEX)
public @interface CreateIndexListenPoint {
    /**
     * canal 指令
     * default for all
     *
     * @return canal destination
     */
    @AliasFor(annotation = ListenPoint.class)
    String destination() default "";

    /**
     * 数据库实例
     *
     * @return canal destination
     */
    @AliasFor(annotation = ListenPoint.class)
    String[] schema() default {};

    /**
     * 监听的表
     * default for all
     *
     * @return canal destination
     */
    @AliasFor(annotation = ListenPoint.class)
    String[] table() default {};
}
