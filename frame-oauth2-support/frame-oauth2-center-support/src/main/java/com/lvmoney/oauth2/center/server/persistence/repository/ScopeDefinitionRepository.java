package com.lvmoney.oauth2.center.server.persistence.repository;

import com.lvmoney.oauth2.center.server.persistence.entity.ScopeDefinitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScopeDefinitionRepository extends JpaRepository<ScopeDefinitionEntity, Long> {
    ScopeDefinitionEntity findByScope(String scope);
}
