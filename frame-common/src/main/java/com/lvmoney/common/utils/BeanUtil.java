package com.lvmoney.common.utils;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/3/27
 * Copyright xxxx科技有限公司
 */


import com.alibaba.fastjson.JSON;
import com.lvmoney.common.exceptions.BusinessException;
import com.lvmoney.common.exceptions.CommonException;
import com.lvmoney.common.utils.vo.EcdsaVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanMap;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public class BeanUtil {
    private final static Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    public static <T> List<T> deepCopy(List<T> src) {
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(src);

            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            @SuppressWarnings("unchecked")
            List<T> dest = (List<T>) in.readObject();
            return dest;
        } catch (IOException e) {
            logger.error("list bean 拷贝io报错:{}", e.getMessage());
            throw new BusinessException(CommonException.Proxy.BEAN_LIST_COPY_ERROR);
        } catch (ClassNotFoundException e) {
            logger.error("list bean 拷贝报错:{}", e.getMessage());
            throw new BusinessException(CommonException.Proxy.BEAN_LIST_COPY_ERROR);
        }
    }

    /**
     * @param bean 要转换的bean
     * @return Map
     * @throws IOException 2018年11月8日下午1:34:51
     * @describe:bean 转换成map对象
     * @author: lvmoney /xxxx科技有限公司
     */
    public static Map bean2Map(Object bean) {
        return BeanMap.create(bean);
    }

    /**
     * @param clazz 要转换的bean
     * @param map   要转换的map
     * @return
     * @throws IOException 2018年11月8日下午1:34:51
     * @describe:map 转换成对应的bean实体
     * @author: lvmoney /xxxx科技有限公司
     */
    public static <T> T map2Bean(Class<T> clazz, Map<String, Object> map) {
        T obj = null;
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            obj = clazz.newInstance(); // 创建 JavaBean 对象
            // 给 JavaBean 对象的属性赋值
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (map.containsKey(propertyName)) {
                    // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                    Object value = map.get(propertyName);
                    if ("".equals(value)) {
                        value = null;
                    }
                    Object[] args = new Object[1];
                    args[0] = value;
                    descriptor.getWriteMethod().invoke(obj, args);
                }
            }
        } catch (IllegalAccessException e) {
            logger.error("convertMapToBean 实例化JavaBean失败 Error{}", e);
            throw new BusinessException(CommonException.Proxy.BEAN_MAP_2_BEAN_ERROR);
        } catch (IntrospectionException e) {
            logger.error("convertMapToBean 分析类属性失败 Error{}", e);
            throw new BusinessException(CommonException.Proxy.BEAN_MAP_2_BEAN_ERROR);
        } catch (IllegalArgumentException e) {
            logger.error("convertMapToBean 映射错误 Error{}", e);
            throw new BusinessException(CommonException.Proxy.BEAN_MAP_2_BEAN_ERROR);
        } catch (InstantiationException e) {
            logger.error("convertMapToBean 实例化 JavaBean 失败 Error{}", e);
            throw new BusinessException(CommonException.Proxy.BEAN_MAP_2_BEAN_ERROR);
        } catch (InvocationTargetException e) {
            logger.error("convertMapToBean字段映射失败 Error{}", e);
            throw new BusinessException(CommonException.Proxy.BEAN_MAP_2_BEAN_ERROR);
        } catch (Exception e) {
            logger.error("convertMapToBean Error{}", e);
            throw new BusinessException(CommonException.Proxy.BEAN_MAP_2_BEAN_ERROR);
        }
        return (T) obj;
    }

    /**
     * @param clazz 要转换的bean
     * @param map   要转换的map
     * @return
     * @throws IOException 2018年11月8日下午1:34:51
     * @describe:map转换成json在转换成bean， 只对简单没有嵌套的bean有效, map的key和bean的属性名称可以通过@JSONField(name = "test3")做对应，
     * 以解决不对应的情况，用了@JSONField(做了处理后，JsonUtil.t2JsonString(T)后属性是bean的属性，
     * JsonUtil.t2JsonString(map)，key值是@JSONField(name = "test3")里面的值
     * @author: lvmoney /xxxx科技有限公司
     */
    public static <T> T map2BeanJ(Class<T> clazz, Map<String, Object> map) {
        T t = JSON.parseObject(JsonUtil.t2JsonString(map), clazz);
        return t;
    }

    public static void main(String[] args) {
        EcdsaVo ecdsaVo = new EcdsaVo();
        ecdsaVo.setCiphertext("a");
        ecdsaVo.setPlaintext("b");
        ecdsaVo.setPrivateKey("c");
        ecdsaVo.setPublicKey("d");
        Map map = bean2Map(ecdsaVo);
//        System.out.println("map=" + JsonUtil.t2JsonString(map));
//
//
//        EcdsaVo ecdsaVo2 = map2Bean(EcdsaVo.class, map);
//        System.out.println(ecdsaVo2);
//        System.out.println(JsonUtil.t2JsonString(ecdsaVo2));

//        EcdsaVo ecdsaVo3 = JSON.parseObject(JsonUtil.t2JsonString(ecdsaVo2), EcdsaVo.class);
//        System.out.println("bean:" + JsonUtil.t2JsonString(ecdsaVo3));
        Map map1 = new HashMap();
        map1.put("test3", "3333");
        map1.put("privateKey", "3333");
        map1.put("publicKey", "3333");
        map1.put("plaintext", "3333");
        map1.put("ciphertext", "3333");
        map1.forEach((k, v) -> {
            System.out.println("k=" + k);
            System.out.println("v=" + v);
        });
        map.put("test", "rew");
        map.forEach((k, v) -> {
            System.out.println("k=" + k);
            System.out.println("v=" + v);
        });
        EcdsaVo ecdsaVo9 = JSON.parseObject(JsonUtil.t2JsonString(map), EcdsaVo.class);
        System.out.println("bean9:" + JsonUtil.t2JsonString(ecdsaVo9));
        EcdsaVo ecdsaVo4 = JSON.parseObject(JsonUtil.t2JsonString(map1), EcdsaVo.class);
        System.out.println("bean:" + JsonUtil.t2JsonString(ecdsaVo4));
        System.out.println(ecdsaVo4.getTest());
        EcdsaVo ecdsaVo91 = map2BeanJ(EcdsaVo.class, map1);
        System.out.println(JsonUtil.t2JsonString(ecdsaVo91));
        System.out.println(ecdsaVo91.getTest());
    }


}
