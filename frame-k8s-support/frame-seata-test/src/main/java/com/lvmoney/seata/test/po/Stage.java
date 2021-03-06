package com.lvmoney.seata.test.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Data
@TableName("stage")
public class Stage {
    @TableId(type = IdType.ID_WORKER_STR)
    private String tid;
    private int stage;
    private String userId;
}
