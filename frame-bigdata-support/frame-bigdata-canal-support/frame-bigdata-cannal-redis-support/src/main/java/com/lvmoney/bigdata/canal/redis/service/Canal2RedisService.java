package com.lvmoney.bigdata.canal.redis.service;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.lvmoney.bigdata.canal.redis.vo.DbMsgVo;
import com.lvmoney.common.vo.Page;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public interface Canal2RedisService {

    /**
     * 把获得的canal有变更的数据保存到redis中，list的形式.key:数据库名称_表名。list里面存的是map的json数据，这个json数据可以 通过工具转换成bean（系统提供工具），在其他模块使用的时候，可根据redis里面的数据自定义bean用工具把json转为bean
     *
     * @param dbMsgVo:   数据库信息实体
     * @param rowChange: 更改的行数据
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:51
     */
    void canal2Redis(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange);


    /**
     * 删除指定的数据
     *
     * @param dbMsgVo:   数据库信息实体
     * @param rowChange: 更改的行数据
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:51
     */
    void deleteForRedis(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange);

    /**
     * 删除表数据
     *
     * @param dbMsgVo: 数据库信息实体
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:52
     */
    void deleteTableForRedis(DbMsgVo dbMsgVo);

    /**
     * 重命名数据库表
     *
     * @param dbMsgVo:   数据库信息实体
     * @param rowChange: 更改的行数据
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:53
     */
    void renameTableForRedis(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange);

    /**
     * 删除数据库操作
     *
     * @param dbMsgVo:   数据库信息实体
     * @param rowChange: 更改的行数据
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:53
     */
    void deleteDbRorRedis(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange);

    /**
     * @describe: 分页获得数据
     * @param: [page, key]
     * @return: com.lvmoney.common.vo.Page
     * @author： lvmoney /XXXXXX有限公司
     * 2019/7/21 20:32
     */
    /**
     * 分页获得数据
     *
     * @param page: 获得分页数据
     * @param key:  key
     * @return: com.lvmoney.common.vo.Page
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:53
     */
    Page getData(Page page, String key);


}
