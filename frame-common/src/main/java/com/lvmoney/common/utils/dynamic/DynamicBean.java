/**
 * 动态对象实现
 *
 * @Package cn.com.intasect.MQ.SpringAmqp.Rabbitmq.comm.Dynamic
 * @author LeeBing /音泰思计算机技术(成都)有限公司
 * Aug 19, 2014 3:25:33 PM
 */
package com.lvmoney.common.utils.dynamic;

import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.cglib.beans.BeanMap;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * 动态生成Bean 如根据不同的属性动态的生成Java类型
 *
 * @author lvmoney
 */
public class DynamicBean {

    /**
     * 根据传入的属性列表创建一个动态的java对象
     * 属性列表map结构应为：<code>HashMap<String,Class<?>></code>
     * 实例化可通过以下方式使用：
     * 1、实例化对象
     * <code>
     * HashMap<String,Class<?>> _propertyMap = new HashMap<String,Class<?>>();
     * DynamicBean _bean = new DynamicBean(propertyMap);
     * _propertyMap.put("id", Integer.class);
     * </code>
     * 2、设置属性值
     * <code>
     * _bean.setValue("id", 123);
     * </code>
     * 3、获取属性值
     * <code>
     * Object _value = _bean.getValue("id");
     * </code>
     * 4、反射遍历对象
     * <code>
     * Object _object = _bean.getObject();
     * Class<? extends Object> _clazz = object.getClass();
     * Method[] _methods = _clazz.getDeclaredMethods();
     * for (int i = 0; i < _methods.length; i++) {
     * System.out.println(_methods[i].getName());  //这里可以输出方法名，通过反射器反射调用即可
     * }
     * </code>
     *
     * @param propertyMap 属性列表
     */
    protected DynamicBean(Map<String, Class<?>> propertyMap) {
        this.object = generateBean(propertyMap);
        this.bean = BeanMap.create(this.object);
    }

    /**
     * 根据传入的属性map构造一个对象
     *
     * @param propertyMap 对象属性map
     * @return 动态创建的java对象
     */
    private Object generateBean(Map<String, Class<?>> propertyMap) {
        BeanGenerator generator = new BeanGenerator();
        Set<String> keySet = propertyMap.keySet();
        //给需要生成的java对象动态添加属性
        for (Iterator<String> i = keySet.iterator(); i.hasNext(); ) {
            String key = (String) i.next();
            generator.addProperty(key, (Class<?>) propertyMap.get(key));
        }
        return generator.create();
    }

    /**
     * 给bean属性赋值
     *
     * @param property 属性名
     * @param value    值
     */
    public void setValue(String property, Object value) {
        bean.put(property, value);
    }

    /**
     * 通过属性名得到属性值
     *
     * @param property 属性名
     * @return 值
     */
    public Object getValue(String property) {
        return bean.get(property);
    }

    /**
     * 动态生成的对象
     */
    private Object object = null;

    /**
     * Bean对象
     */
    private BeanMap bean = null;

    /**
     * @return 获取参数 object 的值
     */
    public Object getObject() {
        return object;
    }

    /**
     * @param object 将参数 的值赋给属性 object
     */
    public void setObject(Object object) {
        this.object = object;
        //重新創建bean對象
        this.bean = BeanMap.create(this.object);
    }

    /**
     * @return 获取参数 bean 的值
     */
    public BeanMap getBean() {
        return bean;
    }

    /**
     * @param bean 将参数的值赋给属性 bean
     */
    public void setBean(BeanMap bean) {
        this.bean = bean;
    }

}
