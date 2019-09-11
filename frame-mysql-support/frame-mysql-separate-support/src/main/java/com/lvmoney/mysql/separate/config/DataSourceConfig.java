package com.lvmoney.mysql.separate.config;/**
 * 描述:
 * 包名:com.lvmoney.mysql.separate.config
 * 版本信息: 版本1.0
 * 日期:2019/9/6
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.druid.pool.DruidDataSource;
import com.lvmoney.common.constant.CommonConstant;
import com.lvmoney.mysql.separate.enums.DbType;
import com.lvmoney.mysql.separate.enums.LoadBalance;
import com.lvmoney.mysql.separate.properties.DatabaseConfigProp;
import com.lvmoney.mysql.separate.properties.DruidConfigProp;
import com.lvmoney.mysql.separate.vo.DatabaseConfig;
import com.lvmoney.mysql.separate.vo.FrameMasterSlaveRule;
import io.shardingsphere.api.algorithm.masterslave.RandomMasterSlaveLoadBalanceAlgorithm;
import io.shardingsphere.api.algorithm.masterslave.RoundRobinMasterSlaveLoadBalanceAlgorithm;
import io.shardingsphere.api.config.rule.MasterSlaveRuleConfiguration;
import io.shardingsphere.core.keygen.DefaultKeyGenerator;
import io.shardingsphere.core.keygen.KeyGenerator;
import io.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/9/6 18:29
 */
@Configuration
public class DataSourceConfig {
    @Autowired
    private DatabaseConfigProp databaseConfigProp;
    @Autowired
    private DruidConfigProp druidConfigProp;

    @Bean
    public DataSource getDataSource() throws SQLException {
        return buildDataSource();
    }

    private DataSource buildDataSource() {
        AtomicReference<DataSource> dataSource = new AtomicReference();
        Map<String, DataSource> slaveDataSourceMap = new HashMap<>(CommonConstant.MAP_DEFAULT_SIZE);
        MasterSlaveRuleConfiguration masterSlaveRule = getMasterSlaveRuleConfiguration(databaseConfigProp.getMasterSlaveRule());
        Set<String> salveList = new HashSet();
        databaseConfigProp.getDatabase().stream().filter(//过滤slave
                b -> b.getType().toUpperCase().equals(DbType.SLAVE.getValue().toUpperCase())
        )
                .forEach(e -> {
                    salveList.add(e.getDatabaseName());
                    slaveDataSourceMap.put(e.getDatabaseName(), createDruidDataSource(e));
                });
        masterSlaveRule.setSlaveDataSourceNames(salveList);
        databaseConfigProp.getDatabase().stream().filter(
                //过滤master
                b -> b.getType().toUpperCase().equals(DbType.MASTER.getValue().toUpperCase())
        ).limit(1).
                //主节点只允许一个，所以默认获得第一条数据
                        forEach(e -> {
                    try {
                        masterSlaveRule.setMasterDataSourceName(e.getDatabaseName());
                        slaveDataSourceMap.put(e.getDatabaseName(), createDruidDataSource(e));
                        dataSource.set(MasterSlaveDataSourceFactory.createDataSource(slaveDataSourceMap, masterSlaveRule, new HashMap<String, Object>(CommonConstant.MAP_DEFAULT_SIZE), new Properties()));
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                });
        return dataSource.get();
    }


    @Bean
    public KeyGenerator keyGenerator() {
        return new DefaultKeyGenerator();
    }

    private DataSource createDruidDataSource(DatabaseConfig databaseConfig) {
        DruidDataSource result = druidConfigProp.getDruid();
        result.setDriverClassName(databaseConfig.getDriverClassName());
        result.setUrl(databaseConfig.getUrl());
        result.setUsername(databaseConfig.getUsername());
        result.setPassword(databaseConfig.getPassword());
        return result;
    }

    private MasterSlaveRuleConfiguration getMasterSlaveRuleConfiguration(FrameMasterSlaveRule masterSlaveRule) {
        MasterSlaveRuleConfiguration masterSlaveRuleConfiguration = new MasterSlaveRuleConfiguration();
        masterSlaveRuleConfiguration.setName(masterSlaveRule.getName());
        if (LoadBalance.random.getValue().equals(masterSlaveRule.getLoadBalanceAlgorithm())) {
            masterSlaveRuleConfiguration.setLoadBalanceAlgorithm(new RandomMasterSlaveLoadBalanceAlgorithm());
        } else if (LoadBalance.round_robin.getValue().equals(masterSlaveRule.getLoadBalanceAlgorithm())) {
            masterSlaveRuleConfiguration.setLoadBalanceAlgorithm(new RoundRobinMasterSlaveLoadBalanceAlgorithm());
        } else {
            masterSlaveRuleConfiguration.setLoadBalanceAlgorithm(new RoundRobinMasterSlaveLoadBalanceAlgorithm());
        }
        return masterSlaveRuleConfiguration;
    }
}
