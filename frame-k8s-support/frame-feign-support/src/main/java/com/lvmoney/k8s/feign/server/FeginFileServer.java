package com.lvmoney.k8s.feign.server;

import com.lvmoney.k8s.feign.config.FeignConfig;
import com.lvmoney.k8s.feign.vo.resp.CommonVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Administrator on 2019/6/3.
 */
@FeignClient(name = "feginFile", url = "http://localhost:8093", configuration = FeignConfig.class)
public interface FeginFileServer {

    @RequestMapping(value = "/mongo/uploadFile", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    CommonVo uploadFile(@RequestPart("file") MultipartFile multiportFile);
}
