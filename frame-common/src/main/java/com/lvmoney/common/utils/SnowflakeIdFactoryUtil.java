/**
 * 描述:
 * 包名:com.lvmoney.pay.utils
 * 版本信息: 版本1.0
 * 日期:2018年10月9日  下午5:26:20
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * @describe：Twitter-snowflake 实现
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月9日 下午5:37:52
 */
public class SnowflakeIdFactoryUtil {
    private final long twepoch = 1288834974657L;
    private final long workerIdBits = 5L;
    private final long datacenterIdBits = 5L;
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    private final long sequenceBits = 12L;
    private final long workerIdShift = sequenceBits;
    private final long datacenterIdShift = sequenceBits + workerIdBits;
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);
    private long workerId;
    private long datacenterId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    public SnowflakeIdFactoryUtil(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(
                    String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /**
     * @return 2018年10月10日下午2:45:22
     * @describe:获得唯一key值
     * @author: lvmoney /xxxx科技有限公司
     */
    public synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            // 服务器时钟被调整了,ID生成器停止服务.
            throw new RuntimeException(String.format(
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift) | sequence;
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }

    public static void testProductIdByMoreThread(int dataCenterId, int workerId, int n) throws InterruptedException {
        List<Thread> tlist = new ArrayList<>();
        Set<Long> setAll = new HashSet<>();
        CountDownLatch cdLatch = new CountDownLatch(10);
        int threadNo = dataCenterId;
        Map<String, SnowflakeIdFactoryUtil> idFactories = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            // 用线程名称做map key.
            idFactories.put("snowflake" + i, new SnowflakeIdFactoryUtil(workerId, threadNo++));
        }
        for (int i = 0; i < 10; i++) {
            Thread temp = new Thread(new Runnable() {
                @Override
                public void run() {
                    Set<Long> setId = new HashSet<>();
                    SnowflakeIdFactoryUtil idWorker = idFactories.get(Thread.currentThread().getName());
                    for (int j = 0; j < n; j++) {
                        Long test = idWorker.nextId();
                        System.out.println(test);
                        setId.add(test);
                    }
                    synchronized (setAll) {
                        setAll.addAll(setId);
                    }
                    cdLatch.countDown();
                }
            }, "snowflake" + i);
            tlist.add(temp);
        }
        for (int j = 0; j < 10; j++) {
            tlist.get(j).start();
        }
        cdLatch.await();
    }

    public static void testProductId(int dataCenterId, int workerId, int n) {
        SnowflakeIdFactoryUtil idWorker = new SnowflakeIdFactoryUtil(workerId, dataCenterId);
        SnowflakeIdFactoryUtil idWorker2 = new SnowflakeIdFactoryUtil(workerId + 1, dataCenterId);
        Set<Long> setOne = new HashSet<>();
        Set<Long> setTow = new HashSet<>();
        for (int i = 0; i < n; i++) {
            setOne.add(idWorker.nextId());// 加入set
        }
        for (int i = 0; i < n; i++) {
            setTow.add(idWorker2.nextId());// 加入set
        }
        setOne.addAll(setTow);
    }

    public static void testPerSecondProductIdNums() {
        SnowflakeIdFactoryUtil idWorker = new SnowflakeIdFactoryUtil(1, 2);
        long start = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; System.currentTimeMillis() - start < 1000; i++, count = i) {
            /** 测试方法一: 此用法纯粹的生产ID,每秒生产ID个数为300w+ */
            idWorker.nextId();
            /**
             * 测试方法二: 在log中打印,同时获取ID,此用法生产ID的能力受限于log.error()的吞吐能力. *
             * 每秒徘徊在10万左右.
             */
            // log.error("{}",idWorker.nextId());
        }
        long end = System.currentTimeMillis() - start;
        System.out.println(end);
        System.out.println(count);
    }
}
