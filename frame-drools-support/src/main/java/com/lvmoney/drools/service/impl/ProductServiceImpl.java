/**
 * 描述:
 * 包名:com.lvmoney.hotel.produce.drools
 * 版本信息: 版本1.0
 * 日期:2018年11月23日  下午2:10:56
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.drools.service.impl;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import com.lvmoney.drools.service.ProductService;
import com.lvmoney.drools.vo.ResMessage;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年11月23日 下午2:10:56
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {
    public double getProductPrice(double originalPrice) {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("ksession-rules");
        ResMessage resMessage = new ResMessage();
        resMessage.setOriginalPrice(originalPrice);
        kSession.insert(resMessage);//插入
        kSession.fireAllRules();//执行规则
        kSession.dispose();
        return originalPrice - resMessage.getCost();
    }

    @Override
    public boolean isPassProduct(double price, double cost, double charge, double profit) {
        double contrast = price * (1 - charge);
        double income = contrast - cost;
        return income - profit >= 0 ? true : false;
    }
}
