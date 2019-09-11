/**
 * 描述:
 * 包名:com.lvmoney.redis.service
 * 版本信息: 版本1.0
 * 日期:2019年1月7日  下午5:19:43
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.redis.service;

import com.lvmoney.common.vo.Page;

import java.util.List;
import java.util.Map;

/**
 * @param <T>
 * @param <T>
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月7日 下午5:19:43
 */

public interface BaseRedisService<T> {
    /**
     * 存储数据 string 或者set
     *
     * @param key:    redis key
     * @param object: 数据对象
     * @param time:   失效时间
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:39
     */
    void set(String key, Object object, Long time);

    /**
     * 存储String
     *
     * @param key:    rediskey
     * @param object: 数据对象
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:40
     */
    void setString(String key, Object object);

    /**
     * 存储set
     *
     * @param key:    redis key
     * @param object: 数据对象
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:40
     */
    void setSet(String key, Object object);

    /**
     * 设置过期时间
     *
     * @param key:  redis key
     * @param time: 过期时间
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:41
     */
    void setExpire(String key, Long time);

    /**
     * 获得string对象
     *
     * @param key: redis key
     * @return: java.lang.Object
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:41
     */
    Object getString(String key);

    /**
     * 删除指定redis数据
     *
     * @param key: redis key
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:41
     */
    void deleteKey(String key);

    /**
     * 通配符key方式删除
     *
     * @param key: redis key
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:42
     */
    void deleteWildcardKey(String key);

    /**
     * 添加对象到redis 里面的list中
     * redis中的 list 是双向的 所以添加的时候需要注意
     * rightPush 先进先出 leftPush 先进后出 这里 需要注意
     *
     * @param key:  list 对应的key
     * @param obj:  需要存的对象
     * @param time: 失效时间
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:42
     */
    void addList(String key, List obj, Long time);

    /**
     * 添加对象到redis 里面的list中
     * redis中的 list 是双向的 所以添加的时候需要注意
     * rightPush 先进先出 leftPush 先进后出 这里 需要注意
     *
     * @param key: list 对应的key
     * @param obj: 存储的对象
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:43
     */
    void addList(String key, List obj);

    /**
     * opsForList().range(key, start, end);  取范围值  redis里面的list下标从0开始
     * 流程 拿到key 对应的list 取 0 到 5  和 mysql的limt  类似 注意下标即可
     * 从redis list 里面的获取数据分页
     *
     * @param page: 分页信息
     * @param key:  redis key
     * @return: com.lvmoney.common.vo.Page
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:43
     */
    Page getListPage(Page page, String key);

    /**
     * 删除obj缓存
     *
     * @param o: 删除的对象
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:44
     */
    void delObj(Object o);

    /**
     * 获得list的长度
     *
     * @param key: redis key
     * @return: java.lang.Long
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:44
     */
    Long getListSize(String key);

    /**
     * 从存储在键中的列表中删除等于值的元素的第一个计数事件。count> 0：删除等于从左到右移动的值的第一个元素；count< 0：删除等于从右到左移动的值的第一个元素；count = 0：删除等于value的所有元素。
     *
     * @param key:   redis key
     * @param count: count< 0：删除等于从右到左移动的值的第一个元素；count = 0：删除等于value的所有元素。
     * @param obj:   删除的对象
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:44
     */
    void rmValueByList(String key, Long count, Object obj);

    /**
     * 获得map的长度
     *
     * @param key: redis key
     * @return: java.lang.Long
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:45
     */
    Long getMapSize(String key);

    /**
     * 增加map
     *
     * @param key:  redis key
     * @param obj:  对象
     * @param time: 失效时间
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:46
     */
    void addMap(String key, Map<String, T> obj, Long time);

    /**
     * 通过map的key获得数据
     *
     * @param key:    redis key
     * @param mapKey: map key
     * @return: java.lang.Object
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:46
     */
    Object getValueByMapKey(String key, String mapKey);

    /**
     * 通过redis key获得map的数据
     *
     * @param key: redis key
     * @return: java.lang.Object
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:46
     */
    Object getMapByKey(String key);

    /**
     * 根据key值获得map的值
     *
     * @param page: 分页数据
     * @param key:  redis key
     * @return: com.lvmoney.common.vo.Page
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:47
     */
    Page getValueByKey(Page page, String key);

    /**
     * 判断map key是否存在
     *
     * @param key:    redis key
     * @param mapKey: map key
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:47
     */
    boolean isExistMapKey(String key, String mapKey);

    /**
     * 根据key和map的key值删除数据
     *
     * @param key:    redis key
     * @param mapKey: map key
     * @return: java.lang.Long
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:47
     */
    Long deleteValueByMapKey(String key, String... mapKey);

    /**
     * 获取指定key的所有list数据
     *
     * @param key: redis key
     * @return: java.util.List
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:48
     */
    List getListAll(String key);

    /**
     * 重命名key
     *
     * @param key:    原来的key
     * @param newKey: 重命名后的key
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:48
     */
    void renameKey(String key, String newKey);

    /**
     * 清空所有key
     *
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:49
     */
    void flushdb();


}
