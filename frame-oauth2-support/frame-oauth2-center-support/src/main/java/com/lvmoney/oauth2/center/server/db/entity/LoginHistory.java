package com.lvmoney.oauth2.center.server.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;


@TableName("login_history")
@Data
public class LoginHistory extends Model<LoginHistory> {

    private static final long serialVersionUID = 3535846612888945217L;
    /**
     *
     */
    @TableId(value = "history_id", type = IdType.ID_WORKER_STR)
    private String historyId;
    //用于记录用户在哪个子系统进行的登陆
    private String clientId;
    private String username;
    private String ip;
    private String device;
    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 修改时间
     */
    private LocalDateTime updateDate;

    /**
     * 删除标记
     */
    private int valid;

    private String remarks;
    private int sortPriority;

}
