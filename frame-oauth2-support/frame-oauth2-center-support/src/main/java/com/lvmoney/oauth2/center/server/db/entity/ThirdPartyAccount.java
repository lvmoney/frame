package com.lvmoney.oauth2.center.server.db.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;


@TableName("third_party_account")
@Data
public class ThirdPartyAccount extends Model<ThirdPartyAccount> {

    private static final long serialVersionUID = -6669281176772367442L;
    /**
     *
     */
    //用于记录用户在哪个子系统进行的注册
    private String clientId;
    private String thirdParty;
    private String thirdPartyAccountId;
    //多种登陆方式合并账号使用
    private String accountOpenCode;
}
