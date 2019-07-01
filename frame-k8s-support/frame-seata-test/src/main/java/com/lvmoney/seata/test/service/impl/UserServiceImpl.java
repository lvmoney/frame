package com.lvmoney.seata.test.service.impl;

import com.lvmoney.seata.test.dao.StageDao;
import com.lvmoney.seata.test.dao.UserDao;
import com.lvmoney.seata.test.po.User;
import com.lvmoney.seata.test.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2019/6/26.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    StageDao stageDao;

    @Override
    @Transactional
    public int insertUser(User user) {
        return userDao.insert(user);
    }

    @Override
    @Transactional
    public int updateStage(String userId) {
        return stageDao.updateStage(userId);
    }
}
