package com.lvmoney.bigdata.canal.redis.listener;
/**
 * 描述:
 * 包名:com.lvmoney.common.config
 * 版本信息: 版本1.0
 * 日期:2019/2/28
 * Copyright xxxx科技有限公司
 */

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.lvmoney.bigdata.canal.redis.annotation.ddl.AlertTableListenPoint;
import com.lvmoney.bigdata.canal.redis.annotation.ddl.CreateIndexListenPoint;
import com.lvmoney.bigdata.canal.redis.annotation.ddl.CreateTableListenPoint;
import com.lvmoney.bigdata.canal.redis.annotation.ddl.DeleteTableListenPoint;
import com.lvmoney.bigdata.canal.redis.annotation.dml.DeleteRowListenPoint;
import com.lvmoney.bigdata.canal.redis.annotation.dml.InsertListenPoint;
import com.lvmoney.bigdata.canal.redis.annotation.dml.UpdateListenPoint;
import com.lvmoney.bigdata.canal.redis.vo.DbMsgVo;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @describe：canal监听数据库的基本信息
 * @author: lvmoney/xxxx科技有限公司
 * @version:v1.0 2019/2/28 10:05
 */
public class DataHandEventListener2 {

    @InsertListenPoint
    public void onEventInsertData(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        System.out.println("======================注解方式（新增数据操作）==========================");
        List<CanalEntry.RowData> rowDatasList = rowChange.getRowDatasList();
        for (CanalEntry.RowData rowData : rowDatasList) {
            String sql = "use " + dbMsgVo.getSchemaName() + ";\n";
            StringBuffer colums = new StringBuffer();
            StringBuffer values = new StringBuffer();
            rowData.getAfterColumnsList().forEach((c) -> {
                colums.append(c.getName() + ",");
                values.append("'" + c.getValue() + "',");
            });


            sql += "INSERT INTO " + dbMsgVo.getTableName() + "(" + colums.substring(0, colums.length() - 1) + ") VALUES(" + values.substring(0, values.length() - 1) + ");";
            System.out.println(sql);
        }
        System.out.println("\n======================================================");

    }

    @UpdateListenPoint
    public void onEventUpdateData(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        System.out.println("======================注解方式（更新数据操作）==========================");
        List<CanalEntry.RowData> rowDatasList = rowChange.getRowDatasList();
        for (CanalEntry.RowData rowData : rowDatasList) {

            String sql = "use " + dbMsgVo.getSchemaName() + ";\n";
            StringBuffer updates = new StringBuffer();
            StringBuffer conditions = new StringBuffer();
            rowData.getAfterColumnsList().forEach((c) -> {
                if (c.getIsKey()) {
                    conditions.append(c.getName() + "='" + c.getValue() + "'");
                } else {
                    updates.append(c.getName() + "='" + c.getValue() + "',");
                }
            });
            sql += "UPDATE " + dbMsgVo.getTableName() + " SET " + updates.substring(0, updates.length() - 1) + " WHERE " + conditions;
            System.out.println(sql);
        }
        System.out.println("\n======================================================");
    }

    @DeleteRowListenPoint
    public void onEventDeleteData(CanalEntry.RowChange rowChange, DbMsgVo dbMsgVo) {

        System.out.println("======================注解方式（删除数据操作）==========================");
        List<CanalEntry.RowData> rowDatasList = rowChange.getRowDatasList();
        for (CanalEntry.RowData rowData : rowDatasList) {

            if (!CollectionUtils.isEmpty(rowData.getBeforeColumnsList())) {
                String sql = "use " + dbMsgVo.getSchemaName() + ";\n";

                sql += "DELETE FROM " + dbMsgVo.getTableName() + " WHERE ";
                StringBuffer idKey = new StringBuffer();
                StringBuffer idValue = new StringBuffer();
                for (CanalEntry.Column c : rowData.getBeforeColumnsList()) {
                    if (c.getIsKey()) {
                        idKey.append(c.getName());
                        idValue.append(c.getValue());
                        break;
                    }


                }

                sql += idKey + " =" + idValue + ";";
                System.out.println(sql);
            }
            System.out.println("\n======================================================");

        }
    }

    @CreateTableListenPoint
    public void onEventCreateTable(CanalEntry.RowChange rowChange) {
        System.out.println("======================注解方式（创建表操作）==========================");
        System.out.println("use " + rowChange.getDdlSchemaName() + ";\n" + rowChange.getSql());
        System.out.println("\n======================================================");
    }

    @DeleteTableListenPoint
    public void onEventDropTable(CanalEntry.RowChange rowChange) {
        System.out.println("======================注解方式（删除表操作）==========================");
        System.out.println("use " + rowChange.getDdlSchemaName() + ";\n" + rowChange.getSql());
        System.out.println("\n======================================================");
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
