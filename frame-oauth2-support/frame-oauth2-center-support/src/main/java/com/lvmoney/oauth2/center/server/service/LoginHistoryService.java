package com.lvmoney.oauth2.center.server.service;

import com.lvmoney.oauth2.center.server.vo.JsonObjects;
import com.lvmoney.oauth2.center.server.vo.LoginHistoryVo;

public interface LoginHistoryService {
    JsonObjects<LoginHistoryVo> listByUsername(String username, int pageNum,
                                               int pageSize,
                                               String sortField,
                                               String sortOrder);

    void asyncCreate(LoginHistoryVo loginHistoryVo);

}
