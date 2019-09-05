package com.lvmoney.k8s.seata.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lvmoney.k8s.seata.server.po.Account;
import org.apache.ibatis.annotations.Update;

/**
 * Created by Administrator on 2019/6/27.
 */
public interface AccountDao extends BaseMapper<Account> {
    @Update("update account as a set a.money = a.money-20 where a.user_id=#{userId}")
    int updateAccount(String userId);
}
