package com.lvmoney.oauth2.center.server.persistence.repository;

import com.lvmoney.oauth2.center.server.persistence.entity.ThirdPartyAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThirdPartyAccountRepository extends JpaRepository<ThirdPartyAccountEntity, Long> {
    ThirdPartyAccountEntity findByThirdPartyAndThirdPartyAccountId(String thirdParty, String thirdPartyAccountId);
}
