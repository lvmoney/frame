package com.lvmoney.k8s.seata.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lvmoney.k8s.seata.server.dao.AccountDao;
import com.lvmoney.k8s.seata.server.po.Account;
import com.lvmoney.k8s.seata.server.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
