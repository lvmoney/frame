package com.lvmoney.oauth2.center.server.persistence.repository;

import com.lvmoney.oauth2.center.server.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByRoleName(String roleName);
}
