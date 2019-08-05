package com.lvmoney.oauth2.center.server;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lvmoney.common.utils.JacksonUtil;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;


public class OAuth2Test {

    private String host = "http://localhost:33380";

    @Test
    @Ignore
    public void encodePassword() {
        String test1 = new BCryptPasswordEncoder().encode("tgb.258");
        System.out.println(test1);
        System.out.println(new BCryptPasswordEncoder().matches("tgb.258", test1));
        String test2 = new BCryptPasswordEncoder().encode("tgb.258");
        System.out.println(test2);
        System.out.println(new BCryptPasswordEncoder().matches("tgb.258", test2));
        String test3 = "$2a$10$gcrWom7ubcRaVD1.6ZIrIeJP0mtPLH5J9V/.8Qth59lZ4B/5HMq96";
        System.out.println(new BCryptPasswordEncoder().matches("tgb.258", test3));


    }


    @Test
    @Ignore
    public void flowTest() throws IOException {
        Map<String, String> result = getToken();

        String isActive = checkToken(result.get("access_token"));
        System.out.println("isActive = " + isActive);

        String newToken = refreshToken(result.get("refresh_token"));
        System.out.println("newToken = " + newToken);
    }


    public Map<String, String> getToken() throws IOException {

        String url = host + "/oauth/token";
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
//  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//  也支持中文
        params.add("grant_type", "password");
        params.add("scope", "read");
        params.add("client_id", "SampleClientId");
        params.add("client_secret", "tgb.258");
        params.add("username", "zhangsan");
        params.add("password", "tgb.258");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
//  执行HTTP请求
        ResponseEntity<String> response = client.exchange(url, HttpMethod.POST, requestEntity, String.class);

        String jsonString = response.getBody();
        Map<String, String> result = JacksonUtil.JSONStringToObject(jsonString, new TypeReference<Map<String, String>>() {
        });
//  输出结果
        System.out.println(result);
        return result;
    }


    public String checkToken(String token) throws IOException {

        String url = host + "/oauth/check_token";
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
//  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//  也支持中文
        params.add("token", token);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
//  执行HTTP请求
        ResponseEntity<String> response = client.exchange(url, HttpMethod.POST, requestEntity, String.class);
        Map<String, Object> result = JacksonUtil.JSONStringToObject(response.getBody(), new TypeReference<Map<String, Object>>() {
        });
//  输出结果
        System.out.println(result);

        return String.valueOf(result.get("active"));
    }

    public String refreshToken(String refresh_token) throws IOException {

        String url = host + "/oauth/token";
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
//  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//  也支持中文
        params.add("client_id", "SampleClientId");
        params.add("client_secret", "tgb.258");
        params.add("grant_type", "refresh_token");
        params.add("refresh_token", refresh_token);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
//  执行HTTP请求
        ResponseEntity<String> response = client.exchange(url, HttpMethod.POST, requestEntity, String.class);
        Map<String, String> result = JacksonUtil.JSONStringToObject(response.getBody(), new TypeReference<Map<String, String>>() {
        });
//  输出结果
        System.out.println(result);

        return result.get("access_token");
    }


}
