/**
 * 描述:
 * 包名:com.lvmoney.hotel.constant
 * 版本信息: 版本1.0
 * 日期:2018年11月8日  下午5:08:58
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.common.constant;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年11月8日 下午5:08:58
 */

public class CommonConstant {
    /**
     * 百度地图key
     */
    public final static String MAP_BAIDU_KEY = "f247cdb592eb43ebac6ccd27f796e2d2";
    /**
     * 百度地图地址
     */
    public final static String MAP_BAIDU_URL = "http://api.map.baidu.com/geocoder?address=%s&output=json&key=%s";
    /**
     * 9f0a80d521e483c3c457a80c5a6ea81a
     * 2678b8cd2e673cd9b5b29c4b95307f8d
     * 高德地图key
     */
    public final static String MAP_AMAP_KEY = "9f0a80d521e483c3c457a80c5a6ea81a";
    /**
     * 高德地图地址
     */

    public final static String MAP_AMAP_URL = "http://restapi.amap.com/v3/geocode/geo?address=%s&output=JSON&key=%s";
    /**
     * 阿里地图 type(100代表道路，010代表POI，001代表门址，111前面都是
     */
    public final static String MAP_ALIBABA_TYPE = "010";
    /**
     * 阿里地图地址
     */
    public final static String MAP_ALIBABA_URL = "http://gc.ditu.aliyun.com/geocoding?a=%s&type=%s";
}
