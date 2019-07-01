/**
 * 描述:
 * 包名:com.lvmoney.quartz.ro
 * 版本信息: 版本1.0
 * 日期:2019年1月3日  下午2:56:44
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.quartz.vo;

import java.io.Serializable;

import com.lvmoney.quartz.job.ParentJob;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月3日 下午2:56:44
 */

public class JobParameter implements Serializable {
    private static final long serialVersionUID = -889078797671910136L;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务出发频率
     */
    private String cron;
    /**
     * 具体执行的任务
     */
    private ParentJob clazz;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public ParentJob getClazz() {
        return clazz;
    }

    public void setClazz(ParentJob clazz) {
        this.clazz = clazz;
    }
}
