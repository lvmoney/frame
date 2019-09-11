/**
 * 描述:
 * 包名:com.lvmoney.hotel.constant
 * 版本信息: 版本1.0
 * 日期:2018年11月8日  下午5:08:58
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.common.constant;

import java.io.File;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年11月8日 下午5:08:58
 */

public class CommonConstant {
    /**
     * 百度地图key
     */
    public static final String MAP_BAIDU_KEY = "f247cdb592eb43ebac6ccd27f796e2d2";
    /**
     * 百度地图地址
     */
    public static final String MAP_BAIDU_URL = "http://api.map.baidu.com/geocoder?address=%s&output=json&key=%s";
    /**
     * 9f0a80d521e483c3c457a80c5a6ea81a
     * 2678b8cd2e673cd9b5b29c4b95307f8d
     * 高德地图key
     */
    public static final String MAP_AMAP_KEY = "9f0a80d521e483c3c457a80c5a6ea81a";
    /**
     * 高德地图地址
     */

    public static final String MAP_AMAP_URL = "http://restapi.amap.com/v3/geocode/geo?address=%s&output=JSON&key=%s";
    /**
     * 阿里地图 type(100代表道路，010代表POI，001代表门址，111前面都是
     */
    public static final String MAP_ALIBABA_TYPE = "010";
    /**
     * 阿里地图地址
     */
    public static final String MAP_ALIBABA_URL = "http://gc.ditu.aliyun.com/geocoding?a=%s&type=%s";

    /**
     * 为了区分token类型加前缀 jwt
     */
    public static final String TOKEM_JWT_PREFIX = "JWT:";
    /**
     * 为了区分token类型加前缀 oauth2
     */
    public static final String TOKEM_OAUTH2_PREFIX = "OAUTH2:";
    /**
     * 文件分隔符
     */
    public static final String FILE_SEPARATOR = File.separator;

    /**
     * localhost 对应的ip
     */
    public static final String LOCALHOST_IP = "127.0.0.1";

    /**
     * http协议
     */
    public static final String HTTP_SCHEMA = "http";
    /**
     * https协议
     */
    public static final String HTTPS_SCHEMA = "https";

    /**
     * saas服务组rediskey
     */
    public static final String REDIS_SERVER_CROUP_KEY = "SAAS_SERVER";
    /**
     * map默认长度
     */
    public static final int MAP_DEFAULT_SIZE = 16;
    /**
     * 字符U
     */
    public static final String STRING_U_UPPER = "U";

    /**
     * 字符,
     */
    public static final String CHAR_COMMA = ",";
    /**
     * 字符%
     */
    public static final String CHAR_PERCENT = "%";
    /**
     * 字符∞
     */
    public static final String CHAR_INFINITE = "∞";

    /**
     * unknown的请求结果
     */
    public static final String IP_UNKNOWN = "unknown";
    /**
     * 默认字符编码UTF-8
     */
    public static final String CHAR_ENCODEING_DEFAULT = "UTF-8";

    /**
     * ISO-8859-1
     */
    public static final String CHAR_ENCODEING_ISO88591 = "ISO-8859-1";
    /**
     * GBK
     */
    public static final String CHAR_ENCODEING_GBK = "GBK";
    /**
     * 字符A
     */
    public static final char CHAR_A_UPPER = 'A';
    /**
     * 字符a
     */
    public static final char CHAR_A_LOWER = 'a';
    /**
     * 字符Z
     */
    public static final char CHAR_Z_UPPER = 'Z';
    /**
     * 字符z
     */
    public static final char CHAR_Z_LOWER = 'z';
    /**
     * 字符b
     */
    public static final String STRING_B_LOWER = "b";


}
