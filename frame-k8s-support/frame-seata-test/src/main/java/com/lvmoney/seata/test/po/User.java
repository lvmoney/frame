package com.lvmoney.seata.test.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

/**
 * Created by Administrator on 2019/6/20.
 */
@Data
@TableName("user")
public class User extends Model<User> {
    @TableId(value = "tid", type = IdType.ID_WORKER_STR)
    private String tid;
    private int stage;
    private String userId;
}
