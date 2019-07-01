package com.lvmoney.k8s.seata.server.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * Created by Administrator on 2019/6/20.
 */
@Data
@TableName("user")
public class User extends Model<User> {
    @TableId(value = "user_id", type = IdType.ID_WORKER_STR)
    private String userId;
    private String username;
    private String password;
}
