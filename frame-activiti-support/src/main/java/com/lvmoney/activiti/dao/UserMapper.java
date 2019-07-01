package com.lvmoney.activiti.dao;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/1/22
 * Copyright xxxx科技有限公司
 */


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lvmoney.activiti.po.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user")
    List<User> selectAll();

    @Select("select * from user where name = #{name}")
    List<User> selectByName(String name);
}
