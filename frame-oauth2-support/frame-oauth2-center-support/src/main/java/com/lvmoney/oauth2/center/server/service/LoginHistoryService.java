package com.lvmoney.oauth2.center.server.service;

import com.lvmoney.oauth2.center.server.vo.JsonObjects;
import com.lvmoney.oauth2.center.server.vo.LoginHistory;

public interface LoginHistoryService {
    JsonObjects<LoginHistory> listByUsername(String username, int pageNum,
                                             int pageSize,
                                             String sortField,
                                             String sortOrder);

    void asyncCreate(LoginHistory loginHistory);

}
