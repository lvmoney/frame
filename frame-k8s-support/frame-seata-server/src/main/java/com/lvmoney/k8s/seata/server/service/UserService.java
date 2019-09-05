package com.lvmoney.k8s.seata.server.service;

/**
 * Created by Administrator on 2019/6/26.
 */


import com.lvmoney.k8s.seata.server.po.User;

/**
 * Created by Administrator on 2019/6/20.
 */
public interface UserService {
    int insertUser(User user);
}
