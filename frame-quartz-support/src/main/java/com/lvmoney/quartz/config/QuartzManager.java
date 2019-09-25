/**
 * 描述:
 * 包名:com.lvmoney.quartz.config
 * 版本信息: 版本1.0
 * 日期:2019年1月3日  下午3:03:07
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.quartz.config;

import com.lvmoney.common.exceptions.BusinessException;
import com.lvmoney.common.exceptions.CommonException;
import com.lvmoney.common.utils.ApplicationBeanUtil;
import com.lvmoney.quartz.constant.QuartzConstant;
import com.lvmoney.quartz.job.AbstractJob;
import com.lvmoney.quartz.vo.JobParameter;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月3日 下午3:03:07
 */
@Component
@Scope("singleton")
public class QuartzManager {

    private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();

    private static final String JOB_DEFAULT_GROUP_NAME = "JOB_DEFAULT_GROUP_NAME";

    private static final String TRIGGER_DEFAULT_GROUP_NAME = "TRIGGER_DEFAULT_GROUP_NAME";

    private final static Logger logger = LoggerFactory.getLogger(QuartzManager.class);



    private Scheduler scheduler;
    @Value("${frame.quartz.support:false}")
    String frameQuartzSupport;
    @Autowired
    private AutowiredSpringBeanJobFactory autowiredSpringBeanJobFactory;

    public void start() {
        if (frameQuartzSupport.equals(QuartzConstant.FRAME_QUARTZ_SUPPORT_TRUE)) {
            // 启动所有任务
            try {
                this.scheduler = schedulerFactory.getScheduler();
                scheduler.setJobFactory(autowiredSpringBeanJobFactory);
                // 启动所有任务,这里获取AbstractTask的所有子类
                Map<String, AbstractJob> tasks = ApplicationBeanUtil.getMapBeansOfType(AbstractJob.class);
                tasks.forEach((k, v) -> {
                    if (v instanceof AbstractJob) {
                        AbstractJob parentJob = (AbstractJob) v;
                        String cronExpression = parentJob.getCronExpression();
                        if (cronExpression != null) {
                            addJob(k, v.getClass().getName(), cronExpression);
                        }
                    }

                });
            } catch (SchedulerException e) {
                logger.error("定时任务初始化失败:{}", e.getMessage());
                throw new BusinessException(CommonException.Proxy.QUARTZ_INIT_ERROR);
            }
        }

    }

    @SuppressWarnings("unchecked")
    public boolean addJob(String jobName, String jobClass, String cronExp) {
        boolean result = false;
        if (!CronExpression.isValidExpression(cronExp)) {
            logger.error("定时任务时间表达式格式错误({})", cronExp);
            throw new BusinessException(CommonException.Proxy.QUARTZ_CRON_ERROR);
        }
        try {
            JobDetail jobDetail = JobBuilder.newJob().withIdentity(new JobKey(jobName, JOB_DEFAULT_GROUP_NAME))
                    .ofType((Class<? extends Job>) Class.forName(jobClass)).build();
            Trigger trigger = TriggerBuilder.newTrigger().forJob(jobDetail)
                    .withSchedule(CronScheduleBuilder.cronSchedule(cronExp))
                    .withIdentity(new TriggerKey(jobName, TRIGGER_DEFAULT_GROUP_NAME)).build();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        } catch (Exception e) {
            logger.error("定时任务新增job报错:{}", e.getMessage());
            throw new BusinessException(CommonException.Proxy.QUARTZ_JOB_ADD_ERROR);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public boolean addJob(JobParameter jobParameter) {
        boolean result = false;
        String cronExp = jobParameter.getCron();
        String jobName = jobParameter.getJobName();
        String className = jobParameter.getClazz().getClass().getName();
        if (!CronExpression.isValidExpression(cronExp)) {
            logger.error("定时任务时间表达式格式错误({})", cronExp);
            throw new BusinessException(CommonException.Proxy.QUARTZ_CRON_ERROR);
        }
        try {
            JobDetail jobDetail = JobBuilder.newJob().withIdentity(new JobKey(jobName, JOB_DEFAULT_GROUP_NAME))
                    .ofType((Class<? extends Job>) Class.forName(className)).build();
            Trigger trigger = TriggerBuilder.newTrigger().forJob(jobDetail)
                    .withSchedule(CronScheduleBuilder.cronSchedule(cronExp))
                    .withIdentity(new TriggerKey(jobName, TRIGGER_DEFAULT_GROUP_NAME)).build();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        } catch (Exception e) {
            logger.error("定时任务新增job报错:{}", e.getMessage());
            throw new BusinessException(CommonException.Proxy.QUARTZ_JOB_ADD_ERROR);
        }
        return result;
    }


    public boolean updateJob(JobParameter jobParameter) {
        boolean result = false;
        String cronExp = jobParameter.getCron();
        String jobName = jobParameter.getJobName();
        if (!CronExpression.isValidExpression(cronExp)) {
            logger.error("定时任务时间表达式格式错误({})", cronExp);
            throw new BusinessException(CommonException.Proxy.QUARTZ_CRON_ERROR);
        }
        JobKey jobKey = new JobKey(jobName, JOB_DEFAULT_GROUP_NAME);
        TriggerKey triggerKey = new TriggerKey(jobName, TRIGGER_DEFAULT_GROUP_NAME);
        try {
            if (scheduler.checkExists(jobKey) && scheduler.checkExists(triggerKey)) {
                JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                Trigger newTrigger = TriggerBuilder.newTrigger().forJob(jobDetail)
                        .withSchedule(CronScheduleBuilder.cronSchedule(cronExp))
                        .withIdentity(new TriggerKey(jobName, TRIGGER_DEFAULT_GROUP_NAME)).build();
                scheduler.rescheduleJob(triggerKey, newTrigger);
                result = true;
            } else {
                logger.error("update job name:{},group name:{} or trigger name:{},group name:{} not exists..",
                        jobKey.getName(), jobKey.getGroup(), triggerKey.getName(), triggerKey.getGroup());
            }
        } catch (SchedulerException e) {
            logger.error("更新任务 name:{},group name:{} 失败!,错误原因:{}", jobKey.getName(), jobKey.getGroup(), e.getMessage());
            throw new BusinessException(CommonException.Proxy.QUARTZ_JOB_UPDATE_ERROR);
        }
        return result;
    }


    public boolean deleteJob(JobParameter jobParameter) {
        boolean result = false;
        String jobName = jobParameter.getJobName();
        JobKey jobKey = new JobKey(jobName, JOB_DEFAULT_GROUP_NAME);
        try {
            if (scheduler.checkExists(jobKey)) {
                result = scheduler.deleteJob(jobKey);
            } else {
                logger.error("删除任务 name:{},group name:{} 不存在", jobKey.getName(), jobKey.getGroup());
                throw new BusinessException(CommonException.Proxy.QUARTZ_JOB_DELETE_ERROR);
            }
        } catch (SchedulerException e) {
            logger.error("删除任务 name:{},group name:{} 错误!原因是{}", jobKey.getName(), jobKey.getGroup(), e.getMessage());
            throw new BusinessException(CommonException.Proxy.QUARTZ_JOB_DELETE_ERROR);
        }
        return result;
    }


    /**
     * @Description:关闭所有定时任务
     */
    public void shutdownJobs() {
        try {
            if (!scheduler.isShutdown()) {
                scheduler.shutdown();
            }
        } catch (Exception e) {
            logger.error("关闭所有定时任务报错:{}", e.getMessage());
            throw new BusinessException(CommonException.Proxy.QUARTZ_JOB_CLOSE_ALL_ERROR);

        }
    }
}