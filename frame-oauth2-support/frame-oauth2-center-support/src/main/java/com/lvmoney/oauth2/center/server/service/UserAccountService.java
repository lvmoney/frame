package com.lvmoney.oauth2.center.server.service;

import com.lvmoney.oauth2.center.server.vo.JsonObjects;
import com.lvmoney.oauth2.center.server.vo.UserAccount;

public interface UserAccountService {
    JsonObjects<UserAccount> listByUsername(String username,
                                            int pageNum,
                                            int pageSize,
                                            String sortField,
                                            String sortOrder);

    UserAccount findByUsername(String username);

    void loginSuccess(String username);

    void loginFailure(String username);

    UserAccount create(UserAccount userAccount);

    UserAccount retrieveById(long id);


    UserAccount updateById(UserAccount userAccount);


    void updateRecordStatus(long id, int recordStatus);
}
