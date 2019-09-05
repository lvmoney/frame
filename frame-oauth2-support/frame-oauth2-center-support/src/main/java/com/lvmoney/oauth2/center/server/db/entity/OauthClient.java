package com.lvmoney.oauth2.center.server.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@TableName("oauth_client")
@Data
public class OauthClient extends Model<OauthClient> {
    private static final long serialVersionUID = -3458903196429799548L;
    /**
     *
     */
    @TableId(value = "oauth_clientid", type = IdType.ID_WORKER_STR)
    private String oauthClientid;
    private String clientId;
    private String applicationName;
    private String resourceIds;
    private String clientSecret;
    private String scope;
    private String authorizedGrantTypes;
    private String webServerRedirectUri;
    private String authorities;
    private Integer accessTokenValidity;
    private Integer refreshTokenValidity;
    private String additionalInformation;
    private String autoApprove;
    private Date expirationDate;//客户端过期时间，比如应用于多店系统
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
