/**
 * 描述:
 * 包名:com.lvmoney.mongo.service
 * 版本信息: 版本1.0
 * 日期:2019年1月10日  上午9:22:39
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.mongo.service;

import java.util.List;

import com.lvmoney.mongo.vo.BaseMongoCollective;
import com.lvmoney.mongo.vo.BaseMongoVo;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月10日 上午9:22:39
 */

public interface BaseMongoService {
    /**
     * @param baseVo data mongo的mo
     * @param baseVo 2019年1月10日上午10:00:23
     * @describe:保存数据对象，集合为数据对象中@Document 注解所配置的collection
     * @author: lvmoney /xxxx科技有限公司
     */
    void save(BaseMongoVo baseVo);

    /**
     * @param baseVo data 数据对象
     * @param baseVo 2019年1月10日上午10:02:48
     * @describe:根据数据对象中的id删除数据，集合为数据对象中@Document 注解所配置的collection
     * @author: lvmoney /xxxx科技有限公司
     */
    void remove(BaseMongoVo baseVo);

    /**
     * @param baseVo data 数据对象 collectionName 集合名
     * @param baseVo 2019年1月10日上午10:03:54
     * @describe:指定集合 根据数据对象中的id删除数据
     * @author: lvmoney /xxxx科技有限公司
     */
    void removeByCollectionName(BaseMongoVo baseVo);

    /**
     * @param baseVo key 键 data 值 collectionName 集合名
     * @param baseVo 2019年1月10日上午10:05:21
     * @describe:根据key，value到指定集合删除数据
     * @author: lvmoney /xxxx科技有限公司
     */
    void removeById(BaseMongoVo baseVo);

    /**
     * @param baseCollection key 修改条件 key keys 修改内容 key数组 data 修改条件 value datas 修改内容
     *                       value数组 collectionName 集合名
     * @param baseCollection 2019年1月10日上午9:58:40
     * @describe:指定集合 修改数据，且仅修改找到的第一条数据
     * @author: lvmoney /xxxx科技有限公司
     */
    void updateFirst(BaseMongoCollective baseCollection);

    /**
     * @param baseCollection key 修改条件 key keys 修改内容 key数组 data 修改条件 value datas 修改内容
     *                       value数组 collectionName 集合名 2019年1月10日上午10:07:57
     * @describe:指定集合 修改数据，且修改所找到的所有数据
     * @author: lvmoney /xxxx科技有限公司
     */
    void updateMulti(BaseMongoCollective baseCollection);

    /**
     * @param baseCollection data 数据对象 keys 查询条件 key datas 查询条件 value
     * @return 2019年1月10日上午10:11:21
     * @describe:根据条件查询出所有结果集 集合为数据对象中@Document 注解所配置的collection
     * @author: lvmoney /xxxx科技有限公司
     */
    List<?> find(BaseMongoCollective baseCollection);

    /**
     * @param baseCollection keys 查询条件 key data 数据对象 datas 查询条件 value collectionName 集合名
     * @return 2019年1月10日上午10:17:13
     * @describe:指定集合 根据条件查询出所有结果集
     * @author: lvmoney /xxxx科技有限公司
     */
    List<?> findByCollectionName(BaseMongoCollective baseCollection);

    /**
     * @param baseCollection * @param baseCollection keys 查询条件 key data 数据对象 datas 查询条件
     *                       value collectionName 集合名 sort 排序字段 sortType 排序类型
     * @return 2019年1月10日上午10:21:03
     * @describe:指定集合 根据条件查询出所有结果集 并排倒序
     * @author: lvmoney /xxxx科技有限公司
     */
    List<?> sortFindByCollectionName(BaseMongoCollective baseCollection);

    /**
     * @param baseCollection keys 查询条件 key data 数据对象 datas 查询条件
     * @param <T>
     * @return 2019年1月10日上午10:44:32
     * @describe: 根据条件查询出符合的第一条数据 集合为数据对象中 @Document 注解所配置的collection
     * @author: lvmoney /xxxx科技有限公司
     */
    Object findOne(BaseMongoCollective baseCollection);

    /**
     * @param baseCollection
     * @return 2019年1月10日上午11:04:16
     * @describe:指定集合 根据条件查询出符合的第一条数据 * @param baseCollection * @param
     * baseCollection keys 查询条件 key data 数据对象 datas 查询条件 value
     * collectionName 集合名
     * @author: lvmoney /xxxx科技有限公司
     */
    Object findFirstOne(BaseMongoCollective baseCollection);

    /**
     * @param baseVo
     * @return 2019年1月10日上午11:04:52
     * @describe:查询出所有结果集 集合为数据对象中 @Document 注解所配置的collection
     * @author: lvmoney /xxxx科技有限公司
     */
    List<?> findAll(BaseMongoVo baseVo);

    /**
     * @param baseVo
     * @param collectionName
     * @return 2019年1月10日上午11:05:45
     * @describe:指定集合 查询出所有结果集
     * @author: lvmoney /xxxx科技有限公司
     */
    List<?> findAllByCollectionName(BaseMongoVo baseVo);
}
