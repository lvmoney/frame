package com.lvmoney.seata.test.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lvmoney.seata.test.po.Stage;
import org.apache.ibatis.annotations.Update;

/**
 * Created by Administrator on 2019/6/27.
 */
public interface StageDao extends BaseMapper<Stage> {
    @Update("update stage as a set a.stage = a.stage-20 where a.user_id=#{userId}")
    int updateStage(String userId);
}