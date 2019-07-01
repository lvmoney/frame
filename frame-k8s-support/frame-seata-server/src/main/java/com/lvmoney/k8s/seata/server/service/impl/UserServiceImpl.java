package com.lvmoney.k8s.seata.server.service.impl;

import com.lvmoney.k8s.seata.server.dao.UserDao;
import com.lvmoney.k8s.seata.server.po.User;
import com.lvmoney.k8s.seata.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/6/26.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public int insertUser(User user) {
        return userDao.insert(user);
    }
}
