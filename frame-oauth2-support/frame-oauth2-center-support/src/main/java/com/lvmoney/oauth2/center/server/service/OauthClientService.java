package com.lvmoney.oauth2.center.server.service;

import com.lvmoney.oauth2.center.server.vo.JsonObjects;
import com.lvmoney.oauth2.center.server.vo.OauthClient;

public interface OauthClientService {
    OauthClient findByClientId(String clientId);

    JsonObjects<OauthClient> list(int pageNum, int pageSize, String sortField, String sortOrder);

    OauthClient create(OauthClient oauthClient);

    OauthClient retrieveById(long id);

    OauthClient updateById(OauthClient oauthClient);

    void updateRecordStatus(long id, int recordStatus);
}
