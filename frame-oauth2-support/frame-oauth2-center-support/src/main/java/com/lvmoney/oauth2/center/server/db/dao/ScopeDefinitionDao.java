package com.lvmoney.oauth2.center.server.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lvmoney.oauth2.center.server.db.entity.ScopeDefinition;
import org.apache.ibatis.annotations.Select;

public interface ScopeDefinitionDao extends BaseMapper<ScopeDefinition> {
    @Select("select * from scope_definition u where u.scope=#{scope}")
    ScopeDefinition findByScope(String scope);
}
