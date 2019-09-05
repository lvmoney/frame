package com.lvmoney.oauth2.center.server.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;


@TableName("scope_definition")
@Data
public class ScopeDefinition extends Model<ScopeDefinition> {
    /**
     *
     */
    @TableId(value = "definition_id", type = IdType.ID_WORKER_STR)
    private String definitionId;
    private String scope;
    /*定义 解释*/
    private String definition;
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
