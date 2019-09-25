1、在业务模块中extends ParentJob 方可使用    
2、在extends的类中定义service和定时任务执行的策略  
3、在application中  
@SpringBootApplication(scanBasePackages = {"com.scltzhy.**"})  
@Configuration  
@EnableScheduling  
public class Application {  
    @Autowired  
    QuartzManager quartzManager;  
    public static void main(String[] args) {  
        SpringApplication.run(Application.class, args);  
    }  
    @Bean  
    public void startJon(){  
        quartzManager.start();  
    }  
}  
4、frame.quartz.support=true表示支持定时任务  
5、很奇怪的bug，突然有一天后台兄弟告诉我定时任务出问题了，一直用着都是好好。我调查发现idea开发测试定时任务（新建的子项目不是module（modules-test里不报错））的时候  
报job中注入bean的错（具体体现QuartzManager的start 方法debug执行完后报Source code does not match the bytecode）    
但是打包成jar执行没问题或者把config包下面的两个类拷贝到子project中也会没问题。该问题持续跟进中  
6、extends AbstractJob 即可，加入执行时间：  
 @PostConstruct  
    public void init() {  
        this.cronExpression = "0/2 * * * * ? ";  
    }  

AbstractJob 借鉴了QuartzJobBean的思路，方便执行时间，或者动态的新增和删除job  

7、也可使用这种方法来实现定时任务，那么用的就不是AbstractJob了，无需关心3,4,5,6  

@Component  
@Configurable  
@EnableScheduling  
public class TestA {  
    @Autowired  
    TestService testService;  

    @Scheduled(cron = "0/1 * * * * ?")  
    public void test() {  
        testService.printTest();  
    }  
}  
