package com.lvmoney.mysql.separate.properties;/**
 * 描述:
 * 包名:com.lvmoney.mysql.separate.properties
 * 版本信息: 版本1.0
 * 日期:2019/9/6
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.mysql.separate.vo.DatabaseConfig;
import com.lvmoney.mysql.separate.vo.FrameMasterSlaveRule;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/9/6 16:17
 */
@Data
@ConfigurationProperties(prefix = "frame.config")
@Component
public class DatabaseConfigProp {
    private List<DatabaseConfig> database;
    private FrameMasterSlaveRule masterSlaveRule;
}
