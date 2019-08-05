package com.lvmoney.oauth2.center.server.service;

import com.lvmoney.oauth2.center.server.vo.ScopeDefinition;

public interface ScopeDefinitionService {
    ScopeDefinition findByScope(String scope);
}
