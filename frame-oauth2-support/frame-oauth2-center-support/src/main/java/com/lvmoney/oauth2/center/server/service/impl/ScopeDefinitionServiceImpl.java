package com.lvmoney.oauth2.center.server.service.impl;

import com.github.dozermapper.core.Mapper;
import com.lvmoney.common.exceptions.BusinessException;
import com.lvmoney.common.exceptions.CommonException;
import com.lvmoney.oauth2.center.server.exception.Oauth2Exception;
import com.lvmoney.oauth2.center.server.persistence.repository.ScopeDefinitionRepository;
import com.lvmoney.oauth2.center.server.vo.ScopeDefinition;
import com.lvmoney.oauth2.center.server.persistence.entity.ScopeDefinitionEntity;
import com.lvmoney.oauth2.center.server.service.ScopeDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScopeDefinitionServiceImpl implements ScopeDefinitionService {

    @Autowired
    ScopeDefinitionRepository scopeDefinitionRepository;

    @Autowired
    Mapper dozerMapper;

    @Override
    public ScopeDefinition findByScope(String scope) {
        ScopeDefinitionEntity scopeDefinitionEntity = scopeDefinitionRepository.findByScope(scope);
        if (scopeDefinitionEntity != null) {
            return dozerMapper.map(scopeDefinitionEntity, ScopeDefinition.class);
        } else {
            throw new BusinessException(Oauth2Exception.Proxy.OAUTH2_SCOPE_DEFINITION_ERROR);
        }
    }

}
