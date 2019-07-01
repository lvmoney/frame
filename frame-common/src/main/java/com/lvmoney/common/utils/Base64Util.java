package com.lvmoney.common.utils;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/3/5
 * Copyright xxxx科技有限公司
 */

import com.lvmoney.common.exceptions.BusinessException;
import com.lvmoney.common.exceptions.CommonException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public class Base64Util {
    private final static Logger logger = LoggerFactory.getLogger(Base64Util.class);

    /**
     * @describe: 加密
     * @param: [b]
     * @return: java.lang.String
     * @author： lvmoney /xxxx科技有限公司
     * 2019/3/5
     */
    public static String encode(byte[] b) {
        String s = null;
        if (b != null) {
            String result = Base64.getEncoder().encodeToString(b);
            return result;
        }
        throw new BusinessException(CommonException.Proxy.BASE64_PARAM_IS_REQUIRED);

    }

    /**
     * @describe: 加密
     * @param: [src]
     * @return: java.lang.String
     * @author： lvmoney /xxxx科技有限公司
     * 2019/3/5
     */
    public static String encode(String src) {
        if (!StringUtils.isBlank(src)) {
            return encode(src.getBytes());
        }
        throw new BusinessException(CommonException.Proxy.BASE64_PARAM_IS_REQUIRED);
    }

    /**
     * @describe: 解密
     * @param: [s]
     * @return: byte[]
     * @author： lvmoney /xxxx科技有限公司
     * 2019/3/5
     */
    public static byte[] decode(String s) {
        byte[] result = java.util.Base64.getDecoder().decode(s);
        return result;
//        throw new BusinessException(CommonException.Proxy.BASE64_PARAM_IS_REQUIRED);

    }

    /**
     * @describe: 解密
     * @param: [s]
     * @return: java.lang.String
     * @author： lvmoney /xxxx科技有限公司
     * 2019/3/5
     */
    public static String decodeStr(String s) {
        byte[] b = decode(s);
        try {
            return b != null ? (new String(b, "UTF-8")) : null;
        } catch (UnsupportedEncodingException e) {
            logger.error("base解密报错{}", e.getMessage());
            throw new BusinessException(CommonException.Proxy.BASE64_ENCODING_ERROR);
        }
    }

}
