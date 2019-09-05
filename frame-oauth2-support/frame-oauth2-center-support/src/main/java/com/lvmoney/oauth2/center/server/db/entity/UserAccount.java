package com.lvmoney.oauth2.center.server.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;


@TableName("user_account")
@Data
public class UserAccount extends Model<UserAccount> {

    private static final long serialVersionUID = -941643106520924961L;
    /**
     *
     */
    //用于记录用户在哪个子系统进行的注册
    @TableId(value = "user_accid", type = IdType.ID_WORKER)
    private String userAccid;
    private String clientId;
    private String username;
    private String password;
    private String nickName;
    private String avatarUrl;
    private String email;
    private String mobile;
    private String province;
    private String city;
    private String address;
    private Date birthday;
    private String gender;
    private Date failureTime;
    private int failureCount;


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
