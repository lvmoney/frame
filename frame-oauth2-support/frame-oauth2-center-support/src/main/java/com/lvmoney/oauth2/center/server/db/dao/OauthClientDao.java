package com.lvmoney.oauth2.center.server.db.dao;/**
 * 描述:
 * 包名:com.lvmoney.oauth2.center.server.db.dao
 * 版本信息: 版本1.0
 * 日期:2019/8/5
 * Copyright XXXXXX科技有限公司
 */


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lvmoney.oauth2.center.server.db.entity.OauthClient;
import org.apache.ibatis.annotations.Select;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/5 17:45
 */
public interface OauthClientDao extends BaseMapper<OauthClient> {
    @Select("select * from oauth_client u where u.client_id=#{clientId}")
    OauthClient findByClientId(String clientId);
}
