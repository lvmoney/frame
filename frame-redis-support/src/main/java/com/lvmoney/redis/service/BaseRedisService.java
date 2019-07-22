/**
 * 描述:
 * 包名:com.lvmoney.redis.service
 * 版本信息: 版本1.0
 * 日期:2019年1月7日  下午5:19:43
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.redis.service;

import java.util.List;
import java.util.Map;

import com.lvmoney.redis.vo.PageVo;

/**
 * @param <T>
 * @param <T>
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月7日 下午5:19:43
 */

public interface BaseRedisService<T> {
    void set(String key, Object object, Long time);

    void setString(String key, Object object);

    void setSet(String key, Object object);

    String getString(String key);

    void deleteKey(String key);

    /**
     * 添加对象到redis 里面的list中
     * redis中的 list 是双向的 所以添加的时候需要注意
     * rightPush 先进先出 leftPush 先进后出 这里 需要注意
     *
     * @param key list 对应的key
     * @param obj 需要存的对象
     */
    void addList(String key, List obj, Long time);

    /**
     * 添加对象到redis 里面的list中
     * redis中的 list 是双向的 所以添加的时候需要注意
     * rightPush 先进先出 leftPush 先进后出 这里 需要注意
     *
     * @param key list 对应的key
     * @param obj 需要存的对象
     */
    void addList(String key, List obj);

    /**
     * opsForList().range(key, start, end);  取范围值  redis里面的list下标从0开始
     * 流程 拿到key 对应的list 取 0 到 5  和 mysql的limt  类似 注意下标即可
     * 从redis list 里面的获取数据分页
     *
     * @param key   redis list 对应的key
     * @param start 开始下标
     * @param end   介绍下标
     * @return 返回list给前端
     */
    @SuppressWarnings("rawtypes")
    PageVo getListPage(PageVo pageVo);

    /**
     * 删除Obj缓存
     *
     * @param o
     */
    void delObj(Object o);


    Long getListSize(String key);


    void addMap(String key, Map<String, T> obj, Long time);

    Object getValueByMapKey(String key, String mapKey);

    boolean isExistMapKey(String key, String mapKey);

    /**
     * @describe:根据key和map的key值删除数据
     * @param: [key, mapKey]
     * @return: boolean
     * @author： lvmoney /XXXXXX有限公司
     * 2019/7/18 17:29
     */
    Long deleteValueByMapKey(String key, String... mapKey);

    List getListAll(String key);



}
