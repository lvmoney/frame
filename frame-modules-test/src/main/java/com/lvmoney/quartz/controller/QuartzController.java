/**
 * 描述:
 * 包名:com.lvmoney.router.controller
 * 版本信息: 版本1.0
 * 日期:2018年12月29日  下午5:02:46
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.quartz.controller;

import com.lvmoney.quartz.config.QuartzManager;
import com.lvmoney.quartz.vo.JobParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年12月29日 下午5:02:46
 */
@RestController
@RequestMapping("quartz")
public class QuartzController {
    @Autowired
    private QuartzManager quartzManager;

    @RequestMapping(value = "test", method = RequestMethod.POST)
    public void postProxy() throws Exception {
        JobParameter jobParameter = new JobParameter();
        quartzManager.start();
        TimeUnit.SECONDS.sleep(10);
        System.out.println("start update job");
        //修改任务
        jobParameter.setJobName("testTask");
        jobParameter.setCron("0/3 * * * * ? ");
        quartzManager.updateJob(jobParameter);
        System.out.println("end update job");
        TimeUnit.SECONDS.sleep(10);
        System.out.println("start delete job");
        //删除任务
        jobParameter.setJobName("testTask");
        quartzManager.deleteJob(jobParameter);
        System.out.println("end delete job");
        TimeUnit.SECONDS.sleep(10);
        TimeUnit.SECONDS.sleep(10);
        //修改任务
        jobParameter.setJobName("testTask");
        jobParameter.setCron("0/3 * * * * ? ");
        System.out.println("start update job");
        quartzManager.updateJob(jobParameter);
        System.out.println("end update job");
        TimeUnit.SECONDS.sleep(10);
        //删除任务
        System.out.println("start delete job");
        jobParameter.setJobName("testTask");
        quartzManager.deleteJob(jobParameter);
        System.out.println("end delete job");
        System.out.println("end.");
    }
}
