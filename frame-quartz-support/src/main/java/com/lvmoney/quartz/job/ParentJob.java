/**
 * 描述:
 * 包名:com.lvmoney.quartz.job
 * 版本信息: 版本1.0
 * 日期:2019年1月3日  下午3:07:09
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.quartz.job;

import com.lvmoney.common.exceptions.BusinessException;
import com.lvmoney.common.exceptions.CommonException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月3日 下午3:07:09
 */
public abstract class ParentJob implements Job {

    private final static Logger logger = LoggerFactory.getLogger(ParentJob.class);

    protected abstract void executeInternal(JobExecutionContext context);

    protected String cronExpression;

    @Override
    public void execute(JobExecutionContext context) {
        try {
            executeInternal(context);
        } catch (Exception e) {
            logger.error("定时任务开始执行报错: {}", e.getMessage());
            throw new BusinessException(CommonException.Proxy.QUARTZ_JOB_EXE_ERROR);
        }
    }

    public String getCronExpression() {
        return cronExpression;
    }
}
