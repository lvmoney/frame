package com.lvmoney.oauth2.center.server.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lvmoney.oauth2.center.server.db.entity.UserAccount;
import org.apache.ibatis.annotations.Select;

public interface UserAccountDao extends BaseMapper<UserAccount> {
    @Select("select * from user_account u where u.username=#{username}")
    UserAccount findByUsername(String username);

}
