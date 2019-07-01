package com.lvmoney.seata.test.service;

/**
 * Created by Administrator on 2019/6/26.
 */


import com.lvmoney.seata.test.po.User;

/**
 * Created by Administrator on 2019/6/20.
 */
public interface UserService {
    int insertUser(User user);

    int updateStage(String userId);
}
