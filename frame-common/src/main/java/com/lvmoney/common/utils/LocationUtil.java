/**
 * 描述:
 * 包名:com.lvmoney.hotel.test
 * 版本信息: 版本1.0
 * 日期:2018年11月6日  下午2:04:42
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lvmoney.common.constant.CommonConstant;
import com.lvmoney.common.utils.vo.BaiDuMapVoResp;
import com.lvmoney.common.utils.vo.LocationVo;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年11月6日 下午2:04:42
 */

public class LocationUtil {
    private static final Logger logger = LoggerFactory.getLogger(LocationUtil.class);

    /**
     * @param addr
     * @return
     * @throws IOException 2018年11月8日下午1:34:51
     * @describe:根据地址获得经纬度-阿里
     * @author: lvmoney /xxxx科技有限公司
     */
    public static LocationVo aLiBaBaMapCoordinate(String addr) {
        /**
         *    * 阿里云根据经纬度获取地区名接口：    *
         * http://gc.ditu.aliyun.com/regeocoding?l=39.938133,116.395739&type=001
         *    * 阿里云根据地区名获取经纬度接口：    * http://gc.ditu.aliyun.com/geocoding?a=苏州市
         * type(100代表道路，010代表POI，001代表门址，111前面都是   
         */
        LocationVo locationVo = new LocationVo();
        String urlString = String.format(CommonConstant.MAP_ALIBABA_URL, addr, CommonConstant.MAP_ALIBABA_TYPE);
        String res = "";
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                res += line + "\n";
            }
            in.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        locationVo = JSONObject.parseObject(res, LocationVo.class);
        return locationVo;
    }

    /**
     * @param addr
     * @return 2018年11月8日下午1:35:07
     * @describe:转化成elasticsearch能够检索的字段lat,lon,方便用于附近的检索
     * @author: lvmoney /xxxx科技有限公司
     */
    public static String aLiBaBaMapLocationString(String addr) {
        LocationVo locationVo = aLiBaBaMapCoordinate(addr);
        return locationVo.getLat() + "," + locationVo.getLon();
    }

    /**
     * @param addr
     * @return 2018年11月8日下午1:35:07
     * @describe:转化成elasticsearch能够检索的字段lat,lon,方便用于附近的检索
     * @author: lvmoney /xxxx科技有限公司
     */
    public static String amapLocationString(String addr) {
        LocationVo locationVo = amapCoordinate(addr);
        return locationVo.getLat() + "," + locationVo.getLon();
    }

    /**
     * @param addr
     * @return 2018年11月8日下午1:35:07
     * @describe:转化成elasticsearch能够检索的字段lat,lon,方便用于附近的检索
     * @author: lvmoney /xxxx科技有限公司
     */
    public static String baiduMapLocationString(String addr) {
        LocationVo locationVo = baiDuMapCoordinate(addr);
        return locationVo.getLat() + "," + locationVo.getLon();
    }

    /**
     * @param addr
     * @return 2018年11月8日下午4:50:53
     * @describe:根据地址获得经纬度-百度
     * @author: lvmoney /xxxx科技有限公司
     */
    public static LocationVo baiDuMapCoordinate(String addr) {
        LocationVo locationVo = new LocationVo();
        // 经度
        String lng = null;
        // 纬度
        String lat = null;
        String address = null;
        try {
            address = java.net.URLEncoder.encode(addr, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String url = String.format(CommonConstant.MAP_BAIDU_URL, address, CommonConstant.MAP_BAIDU_KEY);
        URL myUrl = null;
        URLConnection httpsConn = null;
        try {
            myUrl = new URL(url);
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
        }
        InputStreamReader insr = null;
        BufferedReader br = null;
        try {
            // 不使用代理
            httpsConn = (URLConnection) myUrl.openConnection();
            if (httpsConn != null) {
                insr = new InputStreamReader(httpsConn.getInputStream(), "UTF-8");
                br = new BufferedReader(insr);
                String data = null;
                int count = 1;
                while ((data = br.readLine()) != null) {
                    if (count == 5) {
                        // 经度
                        lng = (String) data.subSequence(data.indexOf(":") + 1, data.indexOf(","));
                        count++;
                    } else if (count == 6) {
                        // 纬度
                        lat = data.substring(data.indexOf(":") + 1);
                        count++;
                    } else {
                        count++;
                    }
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            if (insr != null) {
                try {
                    insr.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }
        locationVo.setLat(lat);
        locationVo.setLon(lng);
        return locationVo;
    }

    public static LocationVo amapCoordinate(String addr) {
        LocationVo locationVo = new LocationVo();
        //String urlString = "http://restapi.amap.com/v3/geocode/geo?address=上海市东方明珠&output=JSON&key=xxxxxxxxx";
        String urlString = String.format(CommonConstant.MAP_AMAP_URL, addr, CommonConstant.MAP_AMAP_KEY);
        String res = "";
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                res += line + "\n";
            }
            in.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        JSONObject jsonObject = JSONObject.parseObject(res);
        // 获取到key为shoppingCartItemList的值
        String result = jsonObject.getString("geocodes");
        List<BaiDuMapVoResp> baiDuMapVoRespList = JSONObject.parseObject(result, new TypeReference<List<BaiDuMapVoResp>>() {
        });
        if (baiDuMapVoRespList != null && baiDuMapVoRespList.size() > 0) {
            BaiDuMapVoResp baiDuMapVoResp = baiDuMapVoRespList.get(0);
            String location = baiDuMapVoResp.getLocation();
            String[] locationArr = location.split(",");
            locationVo.setLat(locationArr[1]);
            locationVo.setLon(locationArr[0]);
        }
        return locationVo;
    }

    public static void main(String[] args) {
        String addr = "北京昌平区昌平区北七家镇冠雅苑小区西侧，近立汤路。 （ 小汤山温泉度假区）北京山水人间快捷酒店";
        addr = addr.replaceAll(" +", "");
        LocationVo locationVo = aLiBaBaMapCoordinate(addr);
        System.out.println(locationVo);
        String locationString = LocationUtil.amapLocationString(addr);
        addr = addr.replaceAll(" +", "");
        System.out.println(addr);
        System.out.println(locationString);
        String addr2 = "北京昌平区小汤山温泉度假区北京山水人间快捷酒店";
        addr2 = addr2.replaceAll(" +", "");
        System.out.println(addr2);
        LocationVo locationVo2 = aLiBaBaMapCoordinate(addr2);
        System.out.println(locationVo2);
        String locationVo1 = LocationUtil.amapLocationString(addr2);
        System.out.println(locationVo1);

    }


}
