package com.lvmoney.lock.constant;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/3/8
 * Copyright xxxx科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public class LockConstant {
    /**
     * 分布式锁的key
     */
    public static final String SECTION_LOCK_KEY = "sectionLock";
    /**
     * 分布式锁的key
     */
    public static final String PROD_SECTION_LOCK_KEY = "prodSectionLock";
    /**
     * 分布式锁的key
     */
    public static final String PROD_SECTION_UPDATE_LOCK_KEY = "prodSectionUpdateLock";

    public static final String PROD_LOCK_KEY = "prodLock";

    public static final int LOCK_TIME = 60;
}
