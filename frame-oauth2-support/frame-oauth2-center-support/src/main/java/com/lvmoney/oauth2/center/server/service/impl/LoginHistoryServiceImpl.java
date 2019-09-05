package com.lvmoney.oauth2.center.server.service.impl;

import com.github.dozermapper.core.Mapper;
import com.lvmoney.oauth2.center.server.db.dao.LoginHistoryDao;
import com.lvmoney.oauth2.center.server.service.LoginHistoryService;
import com.lvmoney.oauth2.center.server.vo.JsonObjects;
import com.lvmoney.oauth2.center.server.db.entity.LoginHistory;
import com.lvmoney.oauth2.center.server.vo.LoginHistoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginHistoryServiceImpl implements LoginHistoryService {
    @Autowired
    LoginHistoryDao loginHistoryDao;

    @Autowired
    Mapper dozerMapper;

    @Override
    public JsonObjects<LoginHistoryVo> listByUsername(String username, int pageNum, int pageSize, String sortField, String sortOrder) {
        JsonObjects<LoginHistoryVo> jsonObjects = new JsonObjects<>();
        return jsonObjects;
    }

    @Override
    @Transactional
    @Async
    public void asyncCreate(LoginHistoryVo loginHistoryVo) {
        LoginHistory entity = dozerMapper.map(loginHistoryVo, LoginHistory.class);
        loginHistoryDao.insert(entity);
    }
}
