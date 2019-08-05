package com.lvmoney.oauth2.center.server.service;

import com.lvmoney.oauth2.center.server.vo.Role;

public interface RoleService {
    Role findByRoleName(String roleName);
}
