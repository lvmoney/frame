package com.lvmoney.office.config;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/3/18
 * Copyright xxxx科技有限公司
 */


import com.lvmoney.office.constant.OfficeConstant;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;
import java.io.File;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Configuration
public class DefaultConfig {
    /**
     * @describe: 由于模板处理的特性需要临时文件目录
     * @param: []
     * @return: javax.servlet.MultipartConfigElement
     * @author： lvmoney /xxxx科技有限公司
     * 2019/3/6
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        String location = OfficeConstant.TEMP_FILE_PATH;
        File tmpFile = new File(location);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
        factory.setLocation(location);
        return factory.createMultipartConfig();
    }
}
