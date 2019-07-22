package com.lvmoney.bigdata.canal.redis.service;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.lvmoney.bigdata.canal.redis.vo.DbMsgVo;

import java.util.List;

/**
 * Created by Administrator on 2019/7/5.
 */
public interface Canal2RedisService {

    /**
     * @describe: 把获得的canal有变更的数据保存到redis中，list的形式.key:数据库名称_表名。list里面存的是map的json数据，这个json数据可以 通过工具转换成bean（系统提供工具），在其他模块使用的时候，可根据redis里面的数据自定义bean用工具把json转为bean
     * @author: lvmoney /xxxx科技有限公司
     * @param: [dbMsgVo, rowChange]
     * @return: void
     * @author： lvmoney /XXXXXX有限公司
     * 2019/7/18 15:58
     */
    void canal2Redis(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange);

    /**
     * @describe:把获得的canal有变更的数据保存到redis中，list的形式.key:数据库名称_表名。list里面存的是obj对应的bean
     * @param: [dbMsgVo, rowChange, obj],数据库基本信息，变更的数据，转换成的bean对象
     * @return: void
     * @author： lvmoney /XXXXXX有限公司
     * 2019/7/18 15:58
     */
    void canal2Redis(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange, Object obj);

    /**
     * @describe:删除指定的数据
     * @param: [key, mapKey]
     * @return: void
     * @author： lvmoney /XXXXXX有限公司
     * 2019/7/18 17:35
     */
    void deleteForRedis(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange);

    /**
     * @describe:删除表数据
     * @param: [dbMsgVo]
     * @return: void
     * @author： lvmoney /XXXXXX有限公司
     * 2019/7/19 15:33
     */
    void deleteTableForRedis(DbMsgVo dbMsgVo);
}
