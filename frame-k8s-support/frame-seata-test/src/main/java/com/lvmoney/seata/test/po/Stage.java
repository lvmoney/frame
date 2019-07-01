package com.lvmoney.seata.test.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Created by Administrator on 2019/6/27.
 */
@Data
@TableName("stage")
public class Stage {
    @TableId(type = IdType.ID_WORKER_STR)
    private String tid;
    private int stage;
    private String userId;
}
