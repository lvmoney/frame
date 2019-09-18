package com.lvmoney.k8s.config.client.po;/**
 * 描述:
 * 包名:com.lvmoney.mysql.separate.po
 * 版本信息: 版本1.0
 * 日期:2019/9/6
 * Copyright XXXXXX科技有限公司
 */


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/9/6 15:01
 */
@Data
@TableName("test")
public class TestPo extends Model<TestPo> {
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;
    private String name;
}
