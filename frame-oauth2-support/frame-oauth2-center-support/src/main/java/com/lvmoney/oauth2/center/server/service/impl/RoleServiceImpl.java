package com.lvmoney.oauth2.center.server.service.impl;

import com.github.dozermapper.core.Mapper;
import com.lvmoney.oauth2.center.server.persistence.entity.RoleEntity;
import com.lvmoney.oauth2.center.server.vo.Role;
import com.lvmoney.oauth2.center.server.persistence.repository.RoleRepository;
import com.lvmoney.oauth2.center.server.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    Mapper dozerMapper;

    @Override
    public Role findByRoleName(String roleName) {
        RoleEntity roleEntity = roleRepository.findByRoleName(roleName);
        if (roleEntity != null) {
            return dozerMapper.map(roleEntity, Role.class);
        } else {
            return null;
        }
    }

}
