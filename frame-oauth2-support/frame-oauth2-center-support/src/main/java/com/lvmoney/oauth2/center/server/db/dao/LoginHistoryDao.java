package com.lvmoney.oauth2.center.server.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lvmoney.oauth2.center.server.db.entity.LoginHistory;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LoginHistoryDao extends BaseMapper<LoginHistory> {
    List<LoginHistory> findByUsername(String username, Page<LoginHistory> page);


}
