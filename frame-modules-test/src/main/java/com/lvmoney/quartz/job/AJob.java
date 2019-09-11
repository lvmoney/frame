/**
 * 描述:
 * 包名:com.lvmoney.quartz.task
 * 版本信息: 版本1.0
 * 日期:2019年1月3日  下午3:08:37
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.quartz.job;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月3日 下午3:08:37
 */

@Component("aJob")
public class AJob extends AbstractJob {

    private static final Logger logger = LoggerFactory.getLogger(AJob.class);

    @PostConstruct
    public void init() {
        this.cronExpression = "0/2 * * * * ? ";
    }

    @Override
    protected void executeInternal(JobExecutionContext context) {
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("this is A task");
        } catch (InterruptedException e) {
            logger.info("test task execute interrupted.");
        }
    }
}
