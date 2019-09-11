package com.lvmoney.mysql.separate.holder;/**
 * 描述:
 * 包名:com.lvmoney.mysql.separate.holder
 * 版本信息: 版本1.0
 * 日期:2019/9/6
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.mysql.separate.enums.DSNames;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/9/6 14:51
 */
@Slf4j
public class DsContextHolder {
    private static final int SLAVE_COUNTER = 9999;

    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    private static final AtomicInteger COUNTER = new AtomicInteger(0);

    private static List<String> slaves;

    public static void setSlaves(List<String> slaves) {
        DsContextHolder.slaves = slaves;
    }

    public static void set(String dsName) {
        CONTEXT_HOLDER.set(dsName);
    }

    public static String get() {
        return CONTEXT_HOLDER.get();
    }

    public static void master() {
        set(DSNames.MASTER.name());
    }

    public void remove() {
        CONTEXT_HOLDER.remove();
    }

    public static void slave() {
        if (slaves.size() > 0) {
            int index = COUNTER.getAndIncrement() % slaves.size();
            if (COUNTER.get() > SLAVE_COUNTER) {
                COUNTER.set(-1);
            }
            set(slaves.get(index));
        } else {
            master();
        }
    }


}