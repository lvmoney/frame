package com.lvmoney.k8s.feign.server;

import com.lvmoney.k8s.feign.config.FeignConfig;
import com.lvmoney.k8s.feign.vo.req.LoginVoReq;
import com.lvmoney.k8s.feign.vo.resp.CommonVo;
import feign.Logger;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by Administrator on 2019/5/23.
 */
//请求第三方ip:port
@FeignClient(name = "fegin", url = "http://localhost:8071", configuration = FeignConfig.class)
//@RequestMapping(produces="application/json;charset=UTF-8")
public interface FeginConfigApi {

    /**
     * 类似postman表单的形式提交，接收参数实体不用@RequsetBody修饰
     *
     * @param loginVoReq
     * @return
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    CommonVo login(LoginVoReq loginVoReq);

    /**
     * 类似postmanjson形式提交，接收参数实体用@RequsetBody修饰
     *
     * @param loginVoReq
     * @return
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    CommonVo login2(LoginVoReq loginVoReq);

    /**
     * rest 风格
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/patient/{id}", method = RequestMethod.PUT)
    Map<String, Object> updatePatientInfo(@PathVariable(value = "id") String id);


    /**
     * @return
     */
    @RequestMapping(value = "/api", method = RequestMethod.GET)
    String jaeger();

}
