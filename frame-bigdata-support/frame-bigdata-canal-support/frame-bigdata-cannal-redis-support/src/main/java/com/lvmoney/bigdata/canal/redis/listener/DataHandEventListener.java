package com.lvmoney.bigdata.canal.redis.listener;
/**
 * 描述:
 * 包名:com.lvmoney.common.config
 * 版本信息: 版本1.0
 * 日期:2019/2/28
 * Copyright xxxx科技有限公司
 */

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.lvmoney.bigdata.canal.redis.annotation.CanalEventListener;
import com.lvmoney.bigdata.canal.redis.annotation.ddl.*;
import com.lvmoney.bigdata.canal.redis.annotation.dml.DeleteRowListenPoint;
import com.lvmoney.bigdata.canal.redis.annotation.dml.InsertListenPoint;
import com.lvmoney.bigdata.canal.redis.annotation.dml.UpdateListenPoint;
import com.lvmoney.bigdata.canal.redis.service.Canal2RedisService;
import com.lvmoney.bigdata.canal.redis.vo.DbMsgVo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @describe：canal监听数据库的基本信息，注意这里暂未考虑分库分区的情况
 * @author: lvmoney/xxxx科技有限公司
 * @version:v1.0 2019/2/28 10:05
 */
@CanalEventListener
public class DataHandEventListener {
    @Autowired
    Canal2RedisService canal2RedisService;

    /**
     * @describe: 插入数据
     * @param: [dbMsgVo, rowChange]
     * @return: void
     * @author： lvmoney /XXXXXX有限公司
     * 2019/7/19 16:21
     */
    @InsertListenPoint
    public void onEventInsertData(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        canal2RedisService.canal2Redis(dbMsgVo, rowChange);
    }

    /**
     * @describe: 更新数据
     * @param: [dbMsgVo, rowChange]
     * @return: void
     * @author： lvmoney /XXXXXX有限公司
     * 2019/7/19 16:21
     */
    @UpdateListenPoint
    public void onEventUpdateData(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        canal2RedisService.canal2Redis(dbMsgVo, rowChange);
    }

    /**
     * @describe: 删除数据
     * @param: [dbMsgVo, rowChange]
     * @return: void
     * @author： lvmoney /XXXXXX有限公司
     * 2019/7/19 16:22
     */
    @DeleteRowListenPoint
    public void onEventDeleteData(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        canal2RedisService.deleteForRedis(dbMsgVo, rowChange);
    }

    /**
     * @describe: 创建表，redis中没必要做数据同步
     * @param: [rowChange]
     * @return: void
     * @author： lvmoney /XXXXXX有限公司
     * 2019/7/19 16:22
     */
    @CreateTableListenPoint
    public void onEventCreateTable(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        System.out.println("======================注解方式（创建表操作）==========================");
        System.out.println("use " + rowChange.getDdlSchemaName() + ";\n" + rowChange.getSql());
        System.out.println("\n======================================================");
    }

    /**
     * @describe: 删除表
     * @param: [dbMsgVo, rowChange]
     * @return: void
     * @author： lvmoney /XXXXXX有限公司
     * 2019/7/19 16:22
     */
    @DropTableListenPoint
    public void onEventDropTable(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        canal2RedisService.deleteTableForRedis(dbMsgVo);
    }

    /**
     * @describe: 清空表，操作redis的key直接清空所有数据
     * @param: [dbMsgVo, rowChange]
     * @return: void
     * @author： lvmoney /XXXXXX有限公司
     * 2019/7/19 16:22
     */
    @DropTableListenPoint
    public void onEventTruncateTable(DbMsgVo dbMsgVo) {
        canal2RedisService.deleteTableForRedis(dbMsgVo);
    }

    @AlertTableListenPoint
    public void onEventAlertTable(CanalEntry.RowChange rowChange) {
        System.out.println("======================注解方式（修改表信息操作）==========================");
        System.out.println("use " + rowChange.getDdlSchemaName() + ";\n" + rowChange.getSql());
        System.out.println("\n======================================================");
    }

    /**
     * @describe:创建索引，redis中没必要做操作
     * @param: [dbMsgVo, rowChange]
     * @return: void
     * @author： lvmoney /XXXXXX有限公司
     * 2019/7/19 16:36
     */
    @CreateIndexListenPoint
    public void onEventCreateIndex(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        System.out.println("======================注解方式（创建索引操作）==========================");
        System.out.println("use " + dbMsgVo.getSchemaName() + ";\n" + rowChange.getSql());
        System.out.println("\n======================================================");

    }


    @DeleteDbListenPoint
    public void onEventDeleteDb(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        canal2RedisService.deleteDbRorRedis(dbMsgVo, rowChange);
    }


    @RenameTableListenPoint
    public void onEventRenameTable(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        canal2RedisService.renameTableForRedis(dbMsgVo, rowChange);

    }

}
