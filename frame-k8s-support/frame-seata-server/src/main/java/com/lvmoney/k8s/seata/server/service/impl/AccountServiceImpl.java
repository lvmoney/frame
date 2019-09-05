package com.lvmoney.k8s.seata.server.service.impl;

import com.lvmoney.k8s.seata.server.dao.AccountDao;
import com.lvmoney.k8s.seata.server.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2019/6/27.
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDao accountDao;

    @Override
    @Transactional
    public int updateAccount(String userid) {
        return accountDao.updateAccount(userid);
    }


}
