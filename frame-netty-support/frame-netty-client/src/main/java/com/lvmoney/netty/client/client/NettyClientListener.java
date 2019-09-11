package com.lvmoney.netty.client.client;

import com.lvmoney.common.exceptions.BusinessException;
import com.lvmoney.common.exceptions.CommonException;
import com.lvmoney.netty.client.action.MainAction;
import com.lvmoney.netty.client.config.NettyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.*;

/**
 * netty客户端监听器
 * <p>
 * 主要用于延迟测试RPC和启动NettyClient
 *
 * @author lvmoney
 * @date 2018/11/1-17:03
 */
@Order(0)
@Component
public class NettyClientListener implements CommandLineRunner {
    /**
     * NettyClientListener 日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyClientListener.class);

    /**
     * netty客户端配置
     */
    @Resource
    private NettyConfig nettyConfig;
    /**
     * 主要用于测试RPC场景的类。集成到自己的业务中就不需要此依赖
     */
    @Resource
    private MainAction mainAction;

    /**
     * 声明一个线程池
     */
    private ExecutorService executorService;

    /**
     * 失效时间
     */
    private static final Long TIMEOUT = 30L;

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("{} -> [准备进行与服务端通信]", this.getClass().getName());
        executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //根据alibaba代码要求，进行了线程池改造，暂未测试
                try {
                    mainAction.call();
                } catch (InterruptedException e) {
                    LOGGER.error("执行监听线程报错:", e.getMessage());
                    executorService.shutdown();
                    try {
                        // Wait a while for existing tasks to terminate
                        if (!executorService.awaitTermination(TIMEOUT, TimeUnit.SECONDS)) {
                            // Cancel currently executing tasks
                            executorService.shutdownNow();
                            // Wait a while for tasks to respond to being cancelled
                            if (!executorService.awaitTermination(TIMEOUT, TimeUnit.SECONDS)) {
                                LOGGER.warn("{Pool did not terminate ");
                            }

                        }
                    } catch (InterruptedException ie) {
                        // (Re-)Cancel if current thread also interrupted
                        executorService.shutdownNow();
                        // Preserve interrupt status
                        Thread.currentThread().interrupt();
                    }
                    throw new BusinessException(CommonException.Proxy.THREAD_POOL_EXE_ERROR);

                }
            }
        });

        /**改成线程池的方式了
         // region 模拟RPC场景
         Thread t1 = new Thread(() -> {
         try {
         mainAction.call();
         } catch (InterruptedException e) {
         e.printStackTrace();
         }
         });
         // 使用一个线程模拟Client启动完毕后RPC的场景
         t1.start();
         */
        // endregion
        // 获取服务器监听的端口
        int port = nettyConfig.getPort();
        // 获取服务器IP地址
        String url = nettyConfig.getUrl();
        // 启动NettyClient
        new NettyClient(port, url).start();
    }
}
