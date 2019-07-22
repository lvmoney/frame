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
import com.lvmoney.bigdata.canal.redis.annotation.ddl.AlertTableListenPoint;
import com.lvmoney.bigdata.canal.redis.annotation.ddl.CreateIndexListenPoint;
import com.lvmoney.bigdata.canal.redis.annotation.ddl.CreateTableListenPoint;
import com.lvmoney.bigdata.canal.redis.annotation.ddl.DeleteTableListenPoint;
import com.lvmoney.bigdata.canal.redis.annotation.dml.DeleteRowListenPoint;
import com.lvmoney.bigdata.canal.redis.annotation.dml.InsertListenPoint;
import com.lvmoney.bigdata.canal.redis.annotation.dml.UpdateListenPoint;
import com.lvmoney.bigdata.canal.redis.service.Canal2RedisService;
import com.lvmoney.bigdata.canal.redis.vo.DbMsgVo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @describe：canal监听数据库的基本信息
 * @author: lvmoney/xxxx科技有限公司
 * @version:v1.0 2019/2/28 10:05
 */
@CanalEventListener
public class DataHandEventListener {
    @Autowired
    Canal2RedisService canal2RedisService;

    @InsertListenPoint
    public void onEventInsertData(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        canal2RedisService.canal2Redis(dbMsgVo, rowChange);
    }

    @UpdateListenPoint
    public void onEventUpdateData(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        canal2RedisService.canal2Redis(dbMsgVo, rowChange);
    }

    @DeleteRowListenPoint
    public void onEventDeleteData(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        canal2RedisService.deleteForRedis(dbMsgVo, rowChange);
    }

    @CreateTableListenPoint
    public void onEventCreateTable(CanalEntry.RowChange rowChange) {
        System.out.println("======================注解方式（创建表操作）==========================");
        System.out.println("use " + rowChange.getDdlSchemaName() + ";\n" + rowChange.getSql());
        System.out.println("\n======================================================");
    }

    @DeleteTableListenPoint
    public void onEventDeleteTable(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        System.out.println("======================注解方式（删除表操作）==========================");
        System.out.println("use " + rowChange.getDdlSchemaName() + ";\n" + rowChange.getSql());
        System.out.println("\n======================================================");
        canal2RedisService.deleteTableForRedis(dbMsgVo);
    }

    @AlertTableListenPoint
    public void onEventAlertTable(CanalEntry.RowChange rowChange) {
        System.out.println("======================注解方式（修改表信息操作）==========================");
        System.out.println("use " + rowChange.getDdlSchemaName() + ";\n" + rowChange.getSql());
        System.out.println("\n======================================================");
    }

    @CreateIndexListenPoint
    public void onEventCreateIndex(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        System.out.println("======================注解方式（创建索引操作）==========================");
        System.out.println("use " + dbMsgVo.getSchemaName() + ";\n" + rowChange.getSql());
        System.out.println("\n======================================================");

    }


}
