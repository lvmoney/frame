package com.lvmoney.oauth2.center.server.service;

import com.lvmoney.oauth2.center.server.vo.ScopeDefinitionVo;

public interface ScopeDefinitionService {
    ScopeDefinitionVo findByScope(String scope);
}
