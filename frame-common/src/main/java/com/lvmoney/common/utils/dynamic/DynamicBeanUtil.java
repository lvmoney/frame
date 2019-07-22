/**
 * 动态对象实现
 *
 * @Package cn.com.intasect.MQ.SpringAmqp.Rabbitmq.comm.Dynamic
 * @author LeeBing /音泰思计算机技术(成都)有限公司
 * Aug 19, 2014 3:25:33 PM
 */
package com.lvmoney.common.utils.dynamic;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 定义基本的动态bean处理工具类
 * @author LeeBing
 */
public class DynamicBeanUtil<T> {


    /**
     * 根据传入的属性名称列表，返回一个按照默认类型设置的属性列表
     * @param properties
     * @return
     */
    public static Map<String, Class<?>> getDefaultPropertyMap(Collection<String> properties, Class<?> defaultClazz) {

        Map<String, Class<?>> _proMap = new HashMap<String, Class<?>>();
        for (String _key : properties) {
            _proMap.put(_key, defaultClazz);//默认都按照String类型处理
        }

        return null;
    }

    /**
     * 根据传入的属性名称列表，返回一个按照默认类型设置的属性列表
     * 属性都默认设置为String类型
     * {@link #getDefaultPropertyMap(Collection, Class)}
     * @param properties
     * @return
     */
    public static Map<String, Class<?>> getDefaultStringPropertyMap(Collection<String> properties) {
        return getDefaultPropertyMap(properties, String.class);
    }


    /**
     * 根据传入的字段映射列表(一个字段转换为新java类型的字段名(keyField)，一个作为值(valueField))
     * 返回一个生成好的pojo对象
     * @param _proMap 字段映射列表<code>Map<String,Class<?>> _proMap</code>
     * @return
     */
    public static DynamicBean getDynamicBean(Map<String, Class<?>> _proMap) {
        //生成对象
        DynamicBean _dynBean = new DynamicBean(_proMap);
        return _dynBean;
    }
}
