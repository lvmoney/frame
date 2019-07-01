package com.lvmoney.activiti.service;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/1/23
 * Copyright xxxx科技有限公司
 */


import com.lvmoney.activiti.vo.*;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public interface BaseActivitiService {
    /**
     * @describe: 通过bpmnid开始流程，bpmnId 流程图id，formId用来记录流程详情表id，variables流程参数
     * @param: [startProcessVo]
     * @return: void
     * @author： lvmoney /xxxx科技有限公司
     * 2019/1/30
     */
    void startProcessInstanceByKey(StartProcessVo startProcessVo);

    /**
     * @describe: 通过formId获得task
     * @param: [queryTaskVo]
     * @return: java.util.List<org.activiti.engine.task.Task>
     * @author： lvmoney /xxxx科技有限公司
     * 2019/1/30
     */
    List<Task> findTasksByFormId(QueryTaskVo queryTaskVo);

    /**
     * @describe: 通过bpmnId和userId查询task
     * @param: [queryTaskVo]
     * @return: java.util.List<org.activiti.engine.task.Task>
     * @author： lvmoney /xxxx科技有限公司
     * 2019/1/30
     */
    List<Task> findTasksByUserId(QueryTaskVo queryTaskVo);

    /**
     * @describe: 通过bpmnid查询task
     * @param: [queryTaskVo]
     * @return: java.util.List<org.activiti.engine.task.Task>
     * @author： lvmoney /xxxx科技有限公司
     * 2019/1/30
     */
    List<Task> findTasksByKey(QueryTaskVo queryTaskVo);

    /**
     * @describe: 通过taskId获得任务
     * @param: [queryTaskVo]
     * @return: org.activiti.engine.task.Task
     * @author： lvmoney /xxxx科技有限公司
     * 2019/1/30
     */
    Task findTaskByTaskId(QueryTaskVo queryTaskVo);

    /**
     * @describe: 通过taskId完成任务并把任务claim给userId
     * @param: [completeTaskVo]
     * @return: void
     * @author： lvmoney /xxxx科技有限公司
     * 2019/1/30
     */
    void completeTaskByTaskId(CompleteTaskVo completeTaskVo);

    /**
     * @describe: 通过formId获得历史记录
     * @param: [historyVo]
     * @return: java.util.List<com.lvmoney.activiti.ro.HistoryVo>
     * @author： lvmoney /xxxx科技有限公司
     * 2019/1/30
     */
    List<HistoryVo> findHistoryByFormId(HistoryVo historyVo);

    /**
     * @describe: 通过bpmnId获得历史记录
     * @param: [historyVo]
     * @return: java.util.List<com.lvmoney.activiti.ro.HistoryVo>
     * @author： lvmoney /xxxx科技有限公司
     * 2019/1/30
     */
    List<HistoryVo> findHistoryByKey(HistoryVo historyVo);

    /**
     * @describe: 根据formId获得流程图二进制
     * @param: [imgVo]
     * @return: com.lvmoney.activiti.ro.ImgVo
     * @author： lvmoney /xxxx科技有限公司
     * 2019/1/30
     */
    ImgVo getProcessImgByFormId(ImgVo imgVo);

    /**
     * @describe: 根据bpmnId获得流程图二进制
     * @param: [imgVo]
     * @return: com.lvmoney.activiti.ro.ImgVo
     * @author： lvmoney /xxxx科技有限公司
     * 2019/1/30
     */
    ImgVo getProcessImgByKey(ImgVo imgVo);

    /**
     * @describe: 任务进行委派
     * 委派：是将任务节点分给其他人处理，等其他人处理好之后，委派任务会自动回到委派人的任务中
     * @param: [delegateVo]
     * @return: boolean
     * @author： lvmoney /xxxx科技有限公司
     * 2019/1/31
     */
    boolean delegateTask(DelegateVo delegateVo);

    /**
     * @describe: 被委派人办理任务后
     * @param: [delegateVo]
     * @return: boolean
     * @author： lvmoney /xxxx科技有限公司
     * 2019/1/31
     */
    boolean resolveTask(DelegateVo delegateVo);

    /**
     * @describe: 转办
     * 直接将办理人assignee 换成别人，这时任务的拥有着不再是转办人，而是为空，相当与将任务转出
     * @param: [assigneeVo]
     * @return: boolean
     * @author： lvmoney /xxxx科技有限公司
     * 2019/1/31
     */
    boolean assigneeTask(AssigneeVo assigneeVo);

    /**
     * @describe: 通过当前任务taskId，把任务跳转到指定的节点id->returnId,并记录跳转原因reason
     * reason是一个实体，type表示类型撤回或者驳回在ActivitiConstant有默认值选择，reason为描述
     * 即处理人小王完成环节A的任务(id=6000)后，流程走到下一环节B生成任务(id=6004)，
     * 任务(id=6004)处理人小张审核不通过执行驳回，
     * 流程流转回环节A，环节A重新生成一条id=6000的待处理人为小王的任务。
     * @param: [jumpVo]
     * @return: void
     * @author： lvmoney /xxxx科技有限公司
     * 2019/1/31
     */
    void jump2NodeId(JumpVo jumpVo);

}
