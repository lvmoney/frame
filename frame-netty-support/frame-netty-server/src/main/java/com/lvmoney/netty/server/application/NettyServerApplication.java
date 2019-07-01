package com.lvmoney.netty.server.application;

import com.lvmoney.netty.server.listener.NettyServerListener;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * Netty服务器启动类
 *
 * @author lvmoney
 * @date 2018/7/9 - 上午9:33
 */
@SpringBootApplication(scanBasePackages = {"com.lvmoney.**"})
public class NettyServerApplication implements CommandLineRunner {

    @Resource
    private NettyServerListener nettyServerListener;

    public static void main(String[] args) {
        SpringApplication.run(NettyServerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        nettyServerListener.start();
    }
}