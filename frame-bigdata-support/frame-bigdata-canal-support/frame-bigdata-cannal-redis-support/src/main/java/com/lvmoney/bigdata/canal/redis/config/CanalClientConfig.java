package com.lvmoney.bigdata.canal.redis.config;

import com.lvmoney.bigdata.canal.redis.client.SimpleCanalClient;
import com.lvmoney.bigdata.canal.redis.properties.CanalProp;
import com.lvmoney.bigdata.canal.redis.spring.HandListenerContext;
import com.lvmoney.bigdata.canal.redis.util.ApplicationBeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public class CanalClientConfig {
    /**
     * 记录日志
     */
    private static final Logger logger = LoggerFactory.getLogger(CanalClientConfig.class);

    /**
     * canal 配置
     */
    @Autowired
    private CanalProp canalProp;
    @Autowired
    HandListenerContext handListenerContext;

    /**
     * 返回 bean 工具类
     *
     * @param
     * @return
     */
//    @Bean
//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    public ApplicationBeanUtil beanUtil() {
//        return new ApplicationBeanUtil();
//    }

    /**
     * 返回 canal 的客户端
     */
    @Bean
    private SimpleCanalClient canalClient() {
        logger.info("初始化canal服务");
        //连接 canal 客户端
//        CanalClient canalClient = new SimpleCanalClient(canalConfig, MessageTransponders.defaultMessageTransponder());
        SimpleCanalClient canalClient = new SimpleCanalClient(canalProp, handListenerContext);
        logger.info("开始连接cannal服务");
        //开启 canal 客户端
        canalClient.start();
        logger.info("连接canal服务成功");
        //返回结果
        return canalClient;
    }
}
