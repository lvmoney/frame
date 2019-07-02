/**
 * 描述:
 * 包名:com.lvmoney.httpclient.service
 * 版本信息: 版本1.0
 * 日期:2018年10月30日  下午3:29:38
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.httpclient.service;

import java.io.File;
import java.net.URLConnection;
import java.util.Map;

import com.lvmoney.httpclient.vo.HttpFResult;
import com.lvmoney.httpclient.vo.HttpResult;
import org.springframework.web.multipart.MultipartFile;


/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */

public interface HttpApiService {
    /**
     * @param url
     * @return
     * @throws Exception 2018年10月30日下午3:32:43
     * @describe:不带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     * @author: lvmoney /xxxx科技有限公司
     */
    String doGet(String url) throws Exception;

    /**
     * @param url
     * @param map
     * @return
     * @throws Exception 2018年10月30日下午3:32:51
     * @describe:带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     * @author: lvmoney /xxxx科技有限公司
     */
    String doGet(String url, Map<String, Object> map) throws Exception;

    /**
     * @param url
     * @param map
     * @return
     * @throws Exception 2018年10月30日下午3:32:56
     * @describe:带参数的post请求
     * @author: lvmoney /xxxx科技有限公司
     */
    HttpResult doPost(String url, Map<String, Object> map) throws Exception;

    /**
     * @param url
     * @return
     * @throws Exception 2018年10月30日下午3:33:01
     * @describe:不带参数post请求
     * @author: lvmoney /xxxx科技有限公司
     */
    HttpResult doPost(String url) throws Exception;

    /**
     * @param url
     * @return
     * @throws Exception 2018年10月30日下午3:33:01
     * @describe:post json
     * @author: lvmoney /xxxx科技有限公司
     */
    HttpResult doJPost(String url, String json);

    /**
     * @param url
     * @return
     * @throws Exception 2018年10月30日下午3:33:01
     * @describe:post file
     * @author: lvmoney /xxxx科技有限公司
     */
    HttpResult doFPost(String url, Map<String, File> fileParam, Map<String, Object> map);


    /**
     * @param url
     * @return
     * @throws Exception 2018年10月30日下午3:33:01
     * @describe:post file
     * @author: lvmoney /xxxx科技有限公司
     */
    HttpFResult doJFPost(String url, String json);

    /**
     * @param url
     * @return
     * @throws Exception 2018年10月30日下午3:33:01
     * @describe:get connection msg
     * @author: lvmoney /xxxx科技有限公司
     */
    URLConnection getGConnection(String url, String param);
}
