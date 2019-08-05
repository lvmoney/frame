package com.lvmoney.oauth2.center.server.persistence.repository;

import com.lvmoney.oauth2.center.server.persistence.entity.UserAccountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccountEntity, Long> {
    UserAccountEntity findByUsername(String username);

    Page<UserAccountEntity> findByUsernameLike(String username, Pageable page);
}
