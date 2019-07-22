package com.lvmoney.bigdata.canal.redis.service.impl;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.lvmoney.bigdata.canal.redis.service.Canal2RedisService;
import com.lvmoney.bigdata.canal.redis.vo.DbMsgVo;
import com.lvmoney.redis.service.BaseRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Administrator on 2019/7/5.
 */
@Service
public class Canal2RedisServiceImpl implements Canal2RedisService {
    @Autowired
    BaseRedisService baseRedisService;


    private Map<String, Object> build2Map(List<CanalEntry.Column> columns) {
        Map<String, Object> result = new HashMap<>();
        for (CanalEntry.Column column : columns) {
            result.put(column.getName(), column.getValue());
        }
        return result;
    }

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
    public void canal2Redis(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange, Object obj) {

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
}
