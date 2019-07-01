package com.lvmoney.k8s.seata.server.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * Created by Administrator on 2019/6/27.
 */
@Data
@TableName("account")
public class Account extends Model<Account> {
    @TableId(type = IdType.ID_WORKER_STR)
    private int tid;
    private int money;
    private String userId;
}
