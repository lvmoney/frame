package com.lvmoney.common.utils;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/3/27
 * Copyright xxxx科技有限公司
 */


import com.lvmoney.common.exceptions.BusinessException;
import com.lvmoney.common.exceptions.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

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
}
