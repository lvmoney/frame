/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/1/24
 * Copyright xxxx科技有限公司
 */


import com.lvmoney.activiti.application.Application;
import com.lvmoney.activiti.dao.UserMapper;
import com.lvmoney.activiti.dao.VacationFormMapper;
import com.lvmoney.activiti.po.User;
import com.lvmoney.activiti.po.VacationForm;
import com.lvmoney.activiti.service.BaseActivitiService;
import com.lvmoney.activiti.service.MiaoService;
import com.lvmoney.activiti.vo.*;
import com.lvmoney.common.constant.CommonConstant;
import com.lvmoney.common.utils.JsonUtil;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@RunWith(SpringJUnit4ClassRunner.class)

@SpringBootTest(classes = Application.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ActivitiTest {
    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    HistoryService historyService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    VacationFormMapper vacationFormMapper;
    @Autowired
    MiaoService miaoService;
    @Autowired
    BaseActivitiService baseActivitiService;

    // @Test
    public void testo0() {//工作流状态

        String formId = "10";
        HashMap<String, String> map = new HashMap<String, String>(CommonConstant.MAP_DEFAULT_SIZE);
        map = miaoService.getCurrentState(formId);
        h2String(map);

    }


    //@Test
    public void test4() {//分管领导
        String formid = "1";
        String operator = "gwgl";
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(formid).singleResult();
        Map<String, Object> variables = new HashMap<String, Object>();
        List<User> users = userMapper.selectAll();
        String managers = "";
        //获取所有具有审核权限的用户
        for (User user : users) {
            if (user.getType().equals(4)) {
                managers += user.getName() + ",";
            }
        }
        managers = managers.substring(0, managers.length() - 1);
        variables.put("gwgl", operator);
        variables.put("fgld", managers);
        taskService.claim(task.getId(), operator);
        taskService.complete(task.getId(), variables);
    }

    @Test
    public void startActiviti() {
        VacationForm form = new VacationForm();
        String approver = "未知审批者";
        form.setTitle("会签任务");
        form.setContent("会签任务");
        form.setApplicant("wang");
        form.setApprover(approver);
        vacationFormMapper.insert(form);

        Map<String, Object> variables = new HashMap<String, Object>();
//        String[]v={"shareniu1","shareniu2","shareniu3","shareniu4"};
//        variables.put("assigneeList",  Arrays.asList(v));
        variables.put("user", "wang");
        variables.put("employee", "li");
        //开始请假流程，使用formId作为流程的businessKey
        runtimeService.startProcessInstanceByKey("test10", form.getId().toString(), variables);
    }

    @Test
    public void history() {
        HistoryVo historyVo = new HistoryVo();
        historyVo.setFormId("3");
        baseActivitiService.findHistoryByFormId(historyVo);
    }

    // @Test
    public void img() {
        QueryTaskVo queryTaskVo = new QueryTaskVo();
        queryTaskVo.setFormId("1");
        List<Task> task = baseActivitiService.findTasksByFormId(queryTaskVo);
        queryTaskVo.setBpmnId("cx2");
        List<Task> tasks = baseActivitiService.findTasksByKey(queryTaskVo);
        queryTaskVo.setUserId("shareniu1");
        List<Task> tasks2 = baseActivitiService.findTasksByUserId(queryTaskVo);
        HistoryVo historyVo = new HistoryVo();
        historyVo.setFormId("1");
        baseActivitiService.findHistoryByFormId(historyVo);
        historyVo.setBpmnId("cx2");
        baseActivitiService.findHistoryByKey(historyVo);
        ImgVo imgVo = new ImgVo();
        imgVo.setFormId("1");
        imgVo.setBpmnId("cx2");
        baseActivitiService.getProcessImgByFormId(imgVo);
        baseActivitiService.getProcessImgByKey(imgVo);
    }

    @Test
    public void revoke() {
        JumpVo jumpVo = new JumpVo();
        jumpVo.setTaskId("17502");
        baseActivitiService.jump2NodeId(jumpVo);
    }

    @Test
    public void endActiviti() {
        Map<String, Object> variables = new HashMap<String, Object>();
        String taskId = "15007";
//        String[] v = {"shareniu1", "shareniu2", "shareniu3", "shareniu4"};
//        variables.put("assigneeList", Arrays.asList(v));
        taskService.complete(taskId);

    }

    @Test
    public void assigneeTask() {
        AssigneeVo assigneeVo = new AssigneeVo();
        assigneeVo.setTaskId("12502");
        assigneeVo.setUserId("test");
        baseActivitiService.assigneeTask(assigneeVo);
    }

    // @Test
    public void test5() {//分管领导
        String formid = "1";
        String operator = "cjsq";
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(formid).singleResult();
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("cjsq", operator);
        String[] assigneeList = {"hq", "hq2"};
        variables.put("assigneeList", Arrays.asList(assigneeList));
        taskService.claim(task.getId(), operator);
        taskService.complete(task.getId(), variables);
    }


    private void h2String(Object t) {
        System.out.println(JsonUtil.t2JsonString(t));
    }

    public void user2JsonString() {
        List<UserVo> list = new ArrayList<UserVo>();
        UserVo userVo = new UserVo();
        userVo.setId("1234");
        userVo.setName("xml");
        userVo.setRole("manage");
        userVo.setPosition("pm");
        list.add(userVo);
        System.out.println(JsonUtil.t2JsonString(list));
    }

//    public static void main(String[] args) {
//        ActivitiTest at=new ActivitiTest();
//        at.user2JsonString();
//    }
}


