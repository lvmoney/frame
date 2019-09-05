package com.lvmoney.oauth2.center.server.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lvmoney.oauth2.center.server.db.entity.ThirdPartyAccount;

public interface ThirdPartyAccountDao extends BaseMapper<ThirdPartyAccount> {
    ThirdPartyAccount findByThirdPartyAndThirdPartyAccountId(String thirdParty, String thirdPartyAccountId);
}
