/**
 * 动态对象实现
 *
 * @Package cn.com.intasect.MQ.SpringAmqp.Rabbitmq.comm.Dynamic
 * @author LeeBing /音泰思计算机技术(成都)有限公司
 * Aug 19, 2014 3:25:33 PM
 */
package com.lvmoney.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;


/**
 * 反射工具类. 提供访问私有变量,获取泛型类型Class, 提取集合中元素的属性, 转换字符串到对象等Util函数.
 * 提供一些可能会用到的反射方法供业务系统使用
 *
 * @author lvmoney
 */
public class ReflectionUtil {

    /**
     * 获取日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * 设置日期本地化转换器
     */
    static {
        DateLocaleConverter dc = new DateLocaleConverter();
        // dc.setPatterns(new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" });
        ConvertUtils.register(dc, Date.class);
    }

    /**
     * 反射调用Get方法获取值 TODO 暂时未对boolean的is开始的获取方法进行处理，直接根据属性名获取值
     *
     * @param target       反射对象
     * @param propertyName 方法参数
     * @return 字段值
     */
    public static Object invokeGetterMethod(Object target, String propertyName) {
        // String _getterMethodName = "get" +
        // StringUtil.Capitalize(propertyName);
        // return InvokeMethod(target, _getterMethodName, new Class[] {},
        // new Object[] {});
        return getFieldValue(target, propertyName);
    }

    /**
     * 调用Setter方法.使用value的Class来查找Setter方法.
     *
     * @param target       反射对象
     * @param propertyName 属性名
     * @param value        待设置的值
     */
    public static void invokeSetterMethod(Object target, String propertyName,
                                          Object value) {
        invokeSetterMethod(target, propertyName, value, null);
    }

    /**
     * 用于查找Setter方法,为空时使用value的Class替代.
     *
     * @param target       反射对象
     * @param propertyName 属性名
     * @param value        设置的值
     * @param propertyType 属性类型
     */
    public static void invokeSetterMethod(Object target, String propertyName,
                                          Object value, Class<?> propertyType) {
        Class<?> type = propertyType != null ? propertyType : value.getClass();
        String setterMethodName = (propertyName.startsWith("set") ? "" : "set")
                + StringUtil.capitalize(propertyName);
        invokeMethod(target, setterMethodName, new Class[]{type},
                new Object[]{value});
    }

    /**
     * 不管属性的访问限定符(private/protected)直接读取对象属性值（不通过get方法获取值）
     *
     * @param object    反射对象
     * @param fieldName 属性名
     * @return 属性值
     */
    public static Object getFieldValue(final Object object,
                                       final String fieldName) {
        Field field = getDeclaredField(object, fieldName);

        if (field == null) {
            throw new IllegalArgumentException("对象[" + object + "]中未找到属性["
                    + fieldName + "]");
        }

        makeAccessible(field);

        Object result = null;
        try {
            result = field.get(object);
        } catch (IllegalAccessException e) {
            LOGGER.error("读取对象属性值抛出的异常{}" + e.getMessage());
        }
        return result;
    }

    /**
     * 不管属性的访问限定符(private/protected)直接设置对象属性值（不通过set方法设置值）
     *
     * @param object    反射对象
     * @param fieldName 属性名
     * @param value     属性值
     */
    public static void setFieldValue(final Object object,
                                     final String fieldName, final Object value) {
        Field field = getDeclaredField(object, fieldName);

        if (field == null) {
            throw new IllegalArgumentException("对象[" + object + "]中未找到属性["
                    + fieldName + "]");
        }

        makeAccessible(field);

        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            LOGGER.error("读取对象属性值抛出的异常:{}" + e.getMessage());
        }
    }

    /**
     * 直接调用对象的方法，不理会访问限定符(private/protected)
     *
     * @param object         反射对象
     * @param methodName     方法名
     * @param parameterTypes 参数列表
     * @param parameters     参数值
     * @return 返回结果
     */
    public static Object invokeMethod(final Object object,
                                      final String methodName, final Class<?>[] parameterTypes,
                                      final Object[] parameters) {
        Method method = getDeclaredMethod(object, methodName, parameterTypes);
        if (method == null) {
            throw new IllegalArgumentException("对象[" + object + "]中未找到方法["
                    + methodName + "(" + parameterTypes + ")");
        }

        method.setAccessible(true);

        try {
            return method.invoke(object, parameters);
        } catch (Exception e) {
            throw convertReflectionExceptionToUnchecked(e);
        }
    }

    /**
     * 递归获取对象的Declared<code>Field</code> 如向上转型到Object仍无法找到, 返回null.
     *
     * @param object    反射对象
     * @param fieldName 属性名
     * @return 字段
     */
    protected static Field getDeclaredField(final Object object,
                                            final String fieldName) {
        Assert.notNull(object, "object不能为空");
        Assert.hasText(fieldName, "fieldName");
        for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
                .getSuperclass()) {
            try {
                return superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {// NOSONAR
                LOGGER.error("找不到对应的属性[" + fieldName + "]", e);
                // Field不在当前类定义,继续向上转型
            }
        }
        return null;
    }

    /**
     * 强行设置字段可以访问（去除private、protected访问限定符）
     *
     * @param field 字段
     */
    protected static void makeAccessible(final Field field) {
        if (!Modifier.isPublic(field.getModifiers())
                || !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
            field.setAccessible(true);
        }
    }

    /**
     * 递归获取对象的Declared<code>Method</code>. 如向上转型到Object仍无法找到, 返回null.
     *
     * @param object     反射对象
     * @param methodName 方法名
     * @return 方法对象
     */
    protected static Method getDeclaredMethod(Object object, String methodName,
                                              Class<?>[] parameterTypes) {
        Assert.notNull(object, "object不能为空");

        for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
                .getSuperclass()) {
            try {
                return superClass
                        .getDeclaredMethod(methodName, parameterTypes);
            } catch (NoSuchMethodException e) {// NOSONAR
                LOGGER.error("找不到对应的方法[" + methodName + "]", e);
                // Method不在当前类定义,继续向上转型
            }
        }
        return null;
    }

    /**
     * 通过反射, 获得Class定义中声明的父类的泛型参数的类型. 如无法找到, 返回Object.class. eg. public UserDao
     * extends HibernateDao<User>
     *
     * @param clazz The class to introspect
     * @return the first generic declaration, or Object.class if cannot be
     * determined
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> Class<T> getSuperClassGenricType(final Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    /**
     * 通过反射, 获得定义Class时声明的父类的泛型参数的类型. 如无法找到, 返回Object.class.
     * <p>
     * 如public UserDao extends HibernateDao<User,Long>
     *
     * @param clazz clazz The class to introspect
     * @param index the Index of the generic ddeclaration,start from 0.
     * @return the index generic declaration, or Object.class if cannot be
     * determined
     */
    @SuppressWarnings({"rawtypes"})
    public static Class getSuperClassGenricType(final Class clazz,
                                                final int index) {
        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            LOGGER.warn(clazz.getSimpleName()
                    + "'s superclass not ParameterizedType");
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType)
                .getActualTypeArguments();

        if (index >= params.length || index < 0) {
            LOGGER.warn("Index: " + index + ", Size of "
                    + clazz.getSimpleName() + "'s Parameterized Type: "
                    + params.length);
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            LOGGER.warn(clazz.getSimpleName()
                    + " not set the actual class on superclass generic parameter");
            return Object.class;
        }

        return (Class) params[index];
    }

    /**
     * 提取集合中的对象的属性(通过getter函数), 组合成List.
     *
     * @param collection   来源集合.
     * @param propertyName 要提取的属性名.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static List convertElementPropertyToList(
            final Collection collection, final String propertyName) {
        List list = new ArrayList();

        try {
            for (Object obj : collection) {
                list.add(PropertyUtils.getProperty(obj, propertyName));
            }
        } catch (Exception e) {
            throw convertReflectionExceptionToUnchecked(e);
        }

        return list;
    }

    /**
     * 提取集合中的对象的属性(通过getter函数), 组合成由分割符分隔的字符串.
     *
     * @param collection   来源集合.
     * @param propertyName 要提取的属性名.
     * @param separator    分隔符.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static String convertElementPropertyToString(
            final Collection collection, final String propertyName,
            final String separator) {
        List list = convertElementPropertyToList(collection, propertyName);
        return StringUtil.join(list, separator);
    }

    /**
     * 转换字符串到相应类型.
     *
     * @param value  待转换的字符串
     * @param toType 转换目标类型
     */
    @SuppressWarnings("unchecked")
    public static <T> T convertStringToObject(String value, Class<T> toType) {
        try {
            return (T) ConvertUtils.convert(value, toType);
        } catch (Exception e) {
            throw convertReflectionExceptionToUnchecked(e);
        }
    }

    /**
     * 将反射时的checked exception转换为unchecked exception.
     *
     * @param e 异常对象
     * @return uncheched exception
     */
    public static RuntimeException convertReflectionExceptionToUnchecked(
            Exception e) {
        return convertReflectionExceptionToUnchecked(null, e);
    }

    /**
     * 将反射时的checked exception转换为unchecked exception.
     *
     * @param desc exception message
     * @param e    Exception
     * @return <code>RuntimeException</code>
     */
    public static RuntimeException convertReflectionExceptionToUnchecked(
            String desc, Exception e) {
        desc = (desc == null) ? "Unexpected Checked Exception." : desc;
        if (e instanceof IllegalAccessException
                || e instanceof IllegalArgumentException
                || e instanceof NoSuchMethodException) {
            return new IllegalArgumentException(desc, e);
        } else if (e instanceof InvocationTargetException) {
            return new RuntimeException(desc,
                    ((InvocationTargetException) e).getTargetException());
        } else if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        }
        return new RuntimeException(desc, e);
    }

    /**
     * 获取一个实例化
     *
     * @param cls 反射实例化对象
     * @return 实例化对象
     */
    public static final <T> T getNewInstance(Class<T> cls) {
        try {
            return cls.newInstance();
        } catch (InstantiationException e) {
            LOGGER.error("获取一个实例化报错{}", e.getMessage());
        } catch (IllegalAccessException e) {
            LOGGER.error("获取一个实例化报错{}", e.getMessage());
        }
        return null;
    }

    /**
     * 拷贝 source 指定的porperties 属性 到 dest中
     *
     * @return void
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void copyPorperties(Object dest, Object source,
                                      String[] porperties) throws InvocationTargetException,
            IllegalAccessException {
        for (String por : porperties) {
            Object srcObj = invokeGetterMethod(source, por);
            LOGGER.debug("属性名[" + por + "]属性值[" + srcObj + "]");
            if (srcObj != null) {
                try {
                    BeanUtils.setProperty(dest, por, srcObj);
                } catch (IllegalArgumentException e) {
                    LOGGER.error("拷贝 source 指定的porperties 属性 到 dest中报错:{}", e.getMessage());
                } catch (IllegalAccessException e) {
                    throw e;
                } catch (InvocationTargetException e) {
                    throw e;
                }
            }
        }
    }

    /**
     * 两者属性名一致时，拷贝source里的属性到dest里
     *
     * @return void
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @SuppressWarnings({"rawtypes"})
    public static void copyPorperties(Object dest, Object source)
            throws IllegalAccessException, InvocationTargetException {
        Class srcCla = source.getClass();
        Field[] fsField = srcCla.getDeclaredFields();

        for (Field s : fsField) {
            String name = s.getName();
            Object srcObj = invokeGetterMethod(source, name);
            try {
                BeanUtils.setProperty(dest, name, srcObj);
            } catch (IllegalArgumentException e) {
                LOGGER.error("两者属性名一致时，拷贝source里的属性到dest里报错:{}", e.getMessage());
            } catch (IllegalAccessException e) {
                throw e;
            } catch (InvocationTargetException e) {
                throw e;
            }
        }
        // BeanUtils.copyProperties(dest, orig);
    }

    /**
     * 两个对象比较是否相等
     *
     * @param obj1
     * @param obj2
     * @return Boolean
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static Boolean compare(Object obj1, Object obj2) throws Exception {

        if (obj1 == obj2) {
            return true;
        }

        Field[] fs = obj1.getClass().getDeclaredFields();
        Boolean flag = false;
        for (Field f : fs) {
            f.setAccessible(true);
            Object v1 = f.get(obj1);
            Object v2 = f.get(obj2);
            flag = equals(v1, v2);
            if (flag == false) {
                break;
            }
        }
        return flag;
    }

    /**
     * 比较两个对象是否一致
     *
     * @param obj1
     * @param obj2
     * @return
     * @author LeeBing
     */
    public static boolean equals(Object obj1, Object obj2) {

        if (obj1 == obj2) {
            return true;
        }
        if (obj1 == null || obj2 == null) {
            return false;
        }
        return obj1.equals(obj2);
    }

}