package com.lvmoney.mysql.separate.config;/**
 * 描述:
 * 包名:com.lvmoney.mysql.separate.application.config
 * 版本信息: 版本1.0
 * 日期:2019/9/6
 * Copyright XXXXXX科技有限公司
 */

import com.lvmoney.mysql.separate.aspect.DataSourceAspect;
import com.lvmoney.mysql.separate.enums.DSNames;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/9/6 14:48
 */
@Configuration
@EnableTransactionManagement(order = 100)
@ConditionalOnClass(JdbcOperations.class)
@EnableConfigurationProperties({MutiDataSourceProperties.class})
@ConditionalOnProperty(prefix = "spring.datasource", name = "master.jdbc-url")
public class DataSourceConfig {

    @Resource
    MutiDataSourceProperties mutiDataSourceProperties;

    @Bean
    public DataSourceAspect dataSourceAspect() {
        List<String> slaves = new ArrayList<>();
        for (Map<String, Object> prop : mutiDataSourceProperties.getSlave()) {
            slaves.add(prop.get("pool-name").toString());
        }
        return new DataSourceAspect(slaves);
    }

    @Bean(name = "masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "dataSource")
    @Qualifier(value = "dataSource")
    @DependsOn({"masterDataSource"})
    public DataSource dynamicDataSource() {

        HikariDataSource masterDataSource = (HikariDataSource) masterDataSource();

        Map<Object, Object> targetDataSources = new HashMap<>(3);
        targetDataSources.put(DSNames.MASTER.name(), masterDataSource);

        // 添加读库
        for (Map<String, Object> prop : mutiDataSourceProperties.getSlave()) {
            HikariDataSource ds = buildDataSource(prop, true);
            targetDataSources.put(ds.getPoolName(), ds);
        }

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
        dynamicDataSource.setTargetDataSources(targetDataSources);

        return dynamicDataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }

    private HikariDataSource buildDataSource(Map<String, Object> map, Boolean readOnly) {

        String poolNameKey = "pool-name";
        String driverClassNameKey = "driver-class-name";
        String jdbcUrlKey = "jdbc-url";
        String usernameKey = "username";
        String passwordKey = "password";
        String maximumPoolSizeKey = "maximum-pool-size";
        String minimumIdleKey = "minimum-idle";

        HikariDataSource dataSource = new HikariDataSource();
        if (map.containsKey(poolNameKey)) {
            dataSource.setPoolName(map.get(poolNameKey).toString());
        }
        if (map.containsKey(driverClassNameKey)) {
            dataSource.setDriverClassName(map.get(driverClassNameKey).toString());
        }
        if (map.containsKey(jdbcUrlKey)) {
            dataSource.setJdbcUrl(map.get(jdbcUrlKey).toString());
        }
        if (map.containsKey(usernameKey)) {
            dataSource.setUsername(map.get(usernameKey).toString());
        }
        if (map.containsKey(passwordKey)) {
            dataSource.setPassword(map.get(passwordKey).toString());
        }
        if (map.containsKey(maximumPoolSizeKey)) {
            dataSource.setMaximumPoolSize(Integer.parseInt(map.get(maximumPoolSizeKey).toString()));
        }
        if (map.containsKey(minimumIdleKey)) {
            dataSource.setMinimumIdle(Integer.parseInt(map.get(minimumIdleKey).toString()));
        }
        dataSource.setReadOnly(readOnly);

        return dataSource;
    }

}

@ConfigurationProperties(prefix = "spring.datasource")
class MutiDataSourceProperties {

    private Map<String, Object> master;
    private List<Map<String, Object>> slave;

    public Map<String, Object> getMaster() {
        return master;
    }

    public void setMaster(Map<String, Object> master) {
        this.master = master;
    }

    public List<Map<String, Object>> getSlave() {
        return slave;
    }

    public void setSlave(List<Map<String, Object>> slave) {
        this.slave = slave;
    }

}