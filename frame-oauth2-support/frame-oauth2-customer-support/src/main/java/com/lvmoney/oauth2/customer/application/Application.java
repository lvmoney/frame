package com.lvmoney.oauth2.customer.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @describe：
 * @author: lvmoney /四川旅投智慧游大数据科技有限公司
 * @version:v1.0 2019年1月16日 下午1:33:39
 */
@SpringBootApplication(scanBasePackages = {"com.lvmoney.**"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
