package com.lvmoney.oauth2.center.server.persistence.repository;

import com.lvmoney.oauth2.center.server.persistence.entity.OauthClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthClientRepository extends JpaRepository<OauthClientEntity, Long> {
    OauthClientEntity findByClientId(String clientId);
}
