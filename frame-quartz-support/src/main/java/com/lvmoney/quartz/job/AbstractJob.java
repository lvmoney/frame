/**
 * 描述:
 * 包名:com.lvmoney.quartz.job
 * 版本信息: 版本1.0
 * 日期:2019年1月3日  下午3:07:09
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.quartz.job;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;


/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月3日 下午3:07:09
 */
@SuppressWarnings("ALL")
public abstract class AbstractJob extends QuartzJobBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractJob.class);

    /**
     * 定时任务执行
     *
     * @param context: 执行内容体
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @data: 2019/9/9 19:04
     */
    @Override
    protected abstract void executeInternal(JobExecutionContext context);

    protected String cronExpression;


    public String getCronExpression() {
        return cronExpression;
    }
}
