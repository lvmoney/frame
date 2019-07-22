package com.lvmoney.bigdata.canal.redis.client;

import com.alibaba.otter.canal.client.CanalConnector;
import com.lvmoney.bigdata.canal.redis.annotation.CanalEventListener;
import com.lvmoney.bigdata.canal.redis.spring.HandListenerContext;
import com.lvmoney.bigdata.canal.redis.util.ContextBeanUtil;
import com.lvmoney.bigdata.canal.redis.vo.ListenerPointVo;
import com.lvmoney.bigdata.canal.redis.properties.CanalProp;
import com.lvmoney.bigdata.canal.redis.util.ApplicationBeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 通过线程池处理
 */
public class SimpleCanalClient extends AbstractCanalClient {
    private final static Logger logger = LoggerFactory.getLogger(SimpleCanalClient.class);
    /**
     * 声明一个线程池
     */
//	private ThreadPoolExecutor executor;
    private ExecutorService executorService;
    /**
     * 通过实现接口的监听器
     */
    protected final List<CanalEventListener> listeners = new ArrayList<>();

    /**
     * 通过注解的方式实现的监听器
     */
    private final List<ListenerPointVo> annoListeners = new ArrayList<>();

    HandListenerContext handListenerContext;

    /**
     * 构造方法，进行一些基本信息初始化
     *
     * @param canalProp
     * @return
     */
    public SimpleCanalClient(CanalProp canalProp, HandListenerContext handListenerContext) {
        super(canalProp);
        //这边上可能需要调整，紧跟德叔脚步走，默认核心线程数5个，最大线程数20个，线程两分钟分钟不执行就。。。
//		executor = new ThreadPoolExecutor(5, 20,
//				120L, TimeUnit.SECONDS,
//				new SynchronousQueue<>(), Executors.defaultThreadFactory());
        executorService = Executors.newFixedThreadPool(canalProp.getPoolSize());
        //初始化监听器
        this.handListenerContext = handListenerContext;
        initListeners();
    }

    /**
     * @param connector
     * @param config
     * @return
     */
    @Override
    protected void process(CanalConnector connector, Map.Entry<String, CanalProp.Instance> config) {
//		executor.submit(factory.newTransponder(connector, config, listeners, annoListeners));
        executorService.submit(factory.newTransponder(connector, config, listeners, annoListeners));
    }

    /**
     * 关闭 canal 客户端
     *
     * @param
     * @return
     */
    @Override
    public void stop() {
        //停止 canal 客户端
        super.stop();
        //优雅的线程池关闭
        executorService.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!executorService.awaitTermination(30, TimeUnit.SECONDS)) {
                executorService.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!executorService.awaitTermination(30, TimeUnit.SECONDS))
                    logger.warn("{Pool did not terminate ");

            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            executorService.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 初始化监听器
     *
     * @param
     * @return
     */
    private void initListeners() {
        logger.info("{}: 监听器正在初始化....", Thread.currentThread().getName());
        //获取监听器,这里代码暂时没用用到的意义
        //        List<CanalEventListener> list = ApplicationBeanUtil.getBeansOfType(CanalEventListener.class);
//        //若没有任何监听的，我也不知道引入这个 jar 干什么，直接返回吧
//        if (list != null) {
//            //若存在目标监听，放入 listenerMap
//            listeners.addAll(list);
//        }

        //若是你喜欢通过注解的方式去监听的话。。
//        Map<String, Object> listenerMap = ApplicationBeanUtil.getBeansWithAnnotation(CanalEventListener.class);
//        //也放入 map
//        if (listenerMap != null) {
//            for (Object target : listenerMap.values()) {
//                //方法获取
//                Method[] methods = target.getClass().getDeclaredMethods();
//                if (methods != null && methods.length > 0) {
//                    for (Method method : methods) {
//                        //获取监听的节点
//                        ListenPoint l = AnnotationUtils.findAnnotation(method, ListenPoint.class);
//                        if (l != null) {
//                            annoListeners.add(new ListenerPointVo(target, method, l));
//                        }
//                    }
//                }
//            }
//        }

        annoListeners.addAll(ContextBeanUtil.getDataHandEventListener(handListenerContext));
        //初始化监听结束
        logger.info("{}: 监听器初始化完成.", Thread.currentThread().getName());
        //整个项目上下文都没发现监听器。。。
//        if (logger.isWarnEnabled() && listeners.isEmpty() && annoListeners.isEmpty()) {
//            logger.warn("{}: 该项目中没有任何监听的目标! ", Thread.currentThread().getName());
//        }

        if (logger.isWarnEnabled() && annoListeners.isEmpty()) {
            logger.warn("{}: 该项目中没有任何监听的目标! ", Thread.currentThread().getName());
        }
    }
}
