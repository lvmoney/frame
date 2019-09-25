/**
 * 描述:
 * 包名:com.lvmoney.pay.utils
 * 版本信息: 版本1.0
 * 日期:2018年10月10日  上午9:20:08
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.common.utils;

import com.lvmoney.common.constant.CommonConstant;
import com.lvmoney.common.utils.vo.ReflectVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.*;

/**
 * @describe：整理请求实体以便通过公钥校验
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月10日 上午9:20:08
 */

public class ReflectUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectUtil.class);

    /**
     * @param reflectVo
     * @return 2018年10月10日下午2:11:25
     * @describe:通过实体获得拼接后的字符串
     * @author: lvmoney /xxxx科技有限公司
     */
    public static <T> ReflectVo<T> getContent(ReflectVo<T> reflectVo) {
        Map<String, String> paraMap = getKeyValueFormVo(reflectVo.getData());
        String content = formatUrlMap(paraMap, reflectVo.isUrlEncode(), reflectVo.isKeyToUpper());
        reflectVo.setContent(content);
        return reflectVo;
    }

    /**
     * @param t
     * @return 2018年10月10日下午2:12:00
     * @describe:反射遍历获得泛型实体的key和value值。根据业务需要排除sign,serialVersionUID字段
     * @author: lvmoney /xxxx科技有限公司
     */
    public static <T> Map<String, String> getKeyValueFormVo(T t) {
        Map<String, String> result = new HashMap<String, String>(CommonConstant.MAP_DEFAULT_SIZE);
        Object clazz;
        Object pClazz;
        try {
            clazz = t.getClass().newInstance();
            pClazz = t.getClass().getSuperclass().newInstance();
            // 父实体属性
            Field[] pFields = pClazz.getClass().getDeclaredFields();
            for (int i = 0; i < pFields.length; i++) {
                Field f = pFields[i];
                // 属性名
                String key = f.getName();
                // 属性值
                String value = null;
                // 忽略序列化版本ID号
                if (!"serialVersionUID".equals(key) && !"sign".equals(key)) {
                    // 取消Java语言访问检查
                    f.setAccessible(true);
                    try {
                        Object obj = f.get(t);
                        value = obj != null ? obj.toString() : "";
                    } catch (IllegalArgumentException e) {
                        LOGGER.error(e.getMessage());
                    } catch (IllegalAccessException e) {
                        LOGGER.error(e.getMessage());
                    }
                    result.put(key, value);
                }
            }
            // 实体属性
            Field[] fields = clazz.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field f = fields[i];
                // 属性名
                String key = f.getName();
                // 属性值
                String value = null;
                // 忽略序列化版本ID号
                if (!"serialVersionUID".equals(key) && !"sign".equals(key)) {
                    // 取消Java语言访问检查
                    f.setAccessible(true);
                    try {
                        Object obj = f.get(t);
                        value = obj != null ? obj.toString() : "";
                    } catch (IllegalArgumentException e) {
                        LOGGER.error(e.getMessage());
                    } catch (IllegalAccessException e) {
                        LOGGER.error(e.getMessage());
                    }
                    result.put(key, value);
                }
            }
        } catch (InstantiationException | IllegalAccessException e1) {
            result = null;
            LOGGER.error(e1.getMessage());
        }
        return result;
    }

    /**
     * @param paraMap    要排序的Map对象 *
     * @param urlEncode  是否需要URLENCODE
     * @param keyToUpper 是否需要将Key转换为全大写 * true:key转化成大写，false:不转化
     * @return
     * @describe:对所有传入参数按照字段名的Unicode码从小到大排序（字典序），并且生成url参数串
     * @author: lvmoney /xxxx科技有限公司
     */
    public static String formatUrlMap(Map<String, String> paraMap, boolean urlEncode, boolean keyToUpper) {
        String buff = "";
        Map<String, String> tmpMap = paraMap;
        try {
            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(tmpMap.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
                @Override
                public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                    return (o1.getKey()).toString().compareTo(o2.getKey());
                }
            });
            // 构造URL 键值对的格式
            StringBuilder buf = new StringBuilder();
            for (Map.Entry<String, String> item : infoIds) {
                if (!StringUtil.isEmpty(item.getKey())) {
                    //String key = item.getKey();
                    String val = item.getValue();
                    if (urlEncode) {
                        val = URLEncoder.encode(val, "utf-8");
                    }
                    // buf.append(key + "=" + val);
                    buf.append(val);
                    buf.append("&");
                }
            }
            buff = buf.toString();
            if (buff.isEmpty() == false) {
                buff = buff.substring(0, buff.length() - 1);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
        return buff.toUpperCase();
    }


    /**
     * @param t
     * @return 2018年10月10日下午2:12:00
     * @describe:反射遍历获得泛型实体的key和value值。根据业务需要排除serialVersionUID字段
     * @author: lvmoney /xxxx科技有限公司
     */
    public static <T> Map<String, Object> getKeyValueFormBean(T t) {
        Map<String, Object> result = new HashMap<String, Object>(CommonConstant.MAP_DEFAULT_SIZE);
        Object clazz;
        Object pClazz;
        try {
            clazz = t.getClass().newInstance();
            pClazz = t.getClass().getSuperclass().newInstance();
            // 父实体属性
            Field[] pFields = pClazz.getClass().getDeclaredFields();
            for (int i = 0; i < pFields.length; i++) {
                Field f = pFields[i];
                // 属性名
                String key = f.getName();
                // 属性值
                String value = null;
                // 忽略序列化版本ID号
                if (!"serialVersionUID".equals(key)) {
                    // 取消Java语言访问检查
                    f.setAccessible(true);
                    try {
                        Object obj = f.get(t);
                        value = obj != null ? obj.toString() : "";
                    } catch (IllegalArgumentException e) {
                        LOGGER.error(e.getMessage());
                    } catch (IllegalAccessException e) {
                        LOGGER.error(e.getMessage());
                    }
                    result.put(key, value);
                }
            }
            // 实体属性
            Field[] fields = clazz.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field f = fields[i];
                // 属性名
                String key = f.getName();
                // 属性值
                String value = null;
                // 忽略序列化版本ID号
                if (!"serialVersionUID".equals(key)) {
                    // 取消Java语言访问检查
                    f.setAccessible(true);
                    try {
                        Object obj = f.get(t);
                        value = obj != null ? obj.toString() : "";
                    } catch (IllegalArgumentException e) {
                        LOGGER.error(e.getMessage());
                    } catch (IllegalAccessException e) {
                        LOGGER.error(e.getMessage());
                    }
                    result.put(key, value);
                }
            }
        } catch (InstantiationException | IllegalAccessException e1) {
            result = null;
            LOGGER.error(e1.getMessage());
        }
        return result;
    }
}
