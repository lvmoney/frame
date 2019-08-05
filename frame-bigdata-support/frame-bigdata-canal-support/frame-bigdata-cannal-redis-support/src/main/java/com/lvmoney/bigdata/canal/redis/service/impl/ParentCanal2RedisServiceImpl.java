package com.lvmoney.bigdata.canal.redis.service.impl;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.lvmoney.bigdata.canal.redis.service.Canal2RedisService;
import com.lvmoney.bigdata.canal.redis.vo.DbMsgVo;
import com.lvmoney.common.utils.BeanUtil;
import com.lvmoney.common.vo.Page;
import com.lvmoney.redis.service.BaseRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Administrator on 2019/7/5.
 */
public abstract class ParentCanal2RedisServiceImpl implements Canal2RedisService {
    @Autowired
    BaseRedisService baseRedisService;


    @Override
    public void canal2Redis(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        List<CanalEntry.RowData> rowDatasList = rowChange.getRowDatasList();
        Map<String, Object> allMap = new HashMap<>();
        for (CanalEntry.RowData rowData : rowDatasList) {
            Map<String, Object> map = new HashMap<>();
            AtomicReference<String> key = new AtomicReference<>("nul");
            rowData.getAfterColumnsList().forEach((c) -> {
                if (c.getIsKey()) {
                    key.set(c.getValue());
                }
                map.put(c.getName(), c.getValue());
            });
            allMap.put(key.toString(), map);
        }
        String dbTableName = getDbTableName(dbMsgVo);
        baseRedisService.addMap(dbTableName, allMap, null);
    }


    @Override
    public void deleteForRedis(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        List<CanalEntry.RowData> rowDatasList = rowChange.getRowDatasList();
        String dbTableName = getDbTableName(dbMsgVo);
        for (CanalEntry.RowData rowData : rowDatasList) {
            rowData.getBeforeColumnsList().forEach((c) -> {
                if (c.getIsKey()) {
                    baseRedisService.deleteValueByMapKey(dbTableName, dbTableName, c.getValue());
                }
            });
        }

    }

    @Override
    public void deleteTableForRedis(DbMsgVo dbMsgVo) {
        String dbTableName = getDbTableName(dbMsgVo);
        baseRedisService.deleteKey(dbTableName);
    }

    @Override
    public void renameTableForRedis(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        String dbTableName = getDbTableName(dbMsgVo);
        String oldDbTableName = dbMsgVo.getSchemaName().toUpperCase() + "_" + getTableNameBySql(rowChange.getSql());
        baseRedisService.renameKey(oldDbTableName, dbTableName);
    }

    @Override
    public void deleteDbRorRedis(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        String sql = rowChange.getSql().toUpperCase();
        if (sql.contains("DROP") && sql.contains("DROP DATABASE") && sql.startsWith("DROP DATABASE")) {
            String dbTableName = getDbName(dbMsgVo.getSchemaName());
            baseRedisService.deleteWildcardKey(dbTableName + "*");
        }
    }

    @Override
    public Page getData(Page page, String key) {
        return baseRedisService.getValueByKey(page, key);
    }

    /**
     * @describe:获得对应的数据库_表的名称，并大写处理
     * @param: [dbMsgVo]
     * @return: java.lang.String
     * @author： lvmoney /XXXXXX有限公司
     * 2019/7/18 17:37
     */
    private String getDbTableName(DbMsgVo dbMsgVo) {
        return dbMsgVo.getSchemaName().toUpperCase() + "_" + dbMsgVo.getTableName().toUpperCase();
    }

    /**
     * @describe:仅适合类似sql:RENAME TABLE `product2` TO `product`
     * 获得更新前的表名,实例中就是:product2
     * @param: [sql]
     * @return: java.lang.String
     * @author： lvmoney /XXXXXX有限公司
     * 2019/7/19 17:52
     */
    private String getTableNameBySql(String sql) {
        String step1 = sql.substring(sql.indexOf("`") + 1);
        String step2 = step1.substring(0, step1.indexOf("`"));
        return step2.toUpperCase();
    }

    /**
     * @describe:清除数据库名称外面的`
     * @param: [sql]
     * @return: java.lang.String
     * @author： lvmoney /XXXXXX有限公司
     * 2019/7/19 22:12
     */
    private String getDbName(String sql) {
        return sql.replaceAll("`", "").toUpperCase();
    }
}
