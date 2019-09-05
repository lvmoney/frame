package com.lvmoney.oauth2.center.server.service;

import com.lvmoney.oauth2.center.server.vo.JsonObjects;
import com.lvmoney.oauth2.center.server.vo.UserAccountVo;

public interface UserAccountService {
    JsonObjects<UserAccountVo> listByUsername(String username,
                                              int pageNum,
                                              int pageSize,
                                              String sortField,
                                              String sortOrder);

    UserAccountVo findByUsername(String username);

    void loginSuccess(String username);

    void loginFailure(String username);

    UserAccountVo create(UserAccountVo userAccountVo);

    UserAccountVo retrieveById(long id);


    UserAccountVo updateById(UserAccountVo userAccountVo);


    void updateRecordStatus(long id, int recordStatus);
}
