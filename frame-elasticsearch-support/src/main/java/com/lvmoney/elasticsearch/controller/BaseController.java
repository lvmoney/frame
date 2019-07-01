/**
 * 描述:
 * 包名:com.lvmoney.hotel.controller
 * 版本信息: 版本1.0
 * 日期:2018年11月7日  上午10:40:05
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.elasticsearch.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lvmoney.common.utils.LocationUtil;
import com.lvmoney.elasticsearch.eo.Employee;
import com.lvmoney.elasticsearch.service.ElasticsearchService;
import com.lvmoney.elasticsearch.vo.ElasticsearchQueryVo;
import com.lvmoney.elasticsearch.vo.ElasticsearchSaveVo;


/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年11月7日 上午10:40:05
 */

@RestController
@RequestMapping("es")
@ResponseBody
public class BaseController {

    @Autowired
    private ElasticsearchService searchService;

    @RequestMapping(value = "save", method = RequestMethod.GET)
    private void esSave() {
        ElasticsearchSaveVo elasticsearchSaveVo = new ElasticsearchSaveVo();
        Employee em = new Employee();
        em.setAbout("this is love story");
        em.setAddress("things address is  chengdu");
        em.setAge(22);
        String location4 = LocationUtil.amapLocationString("四川省成都市武侯区武青南路十号");
        em.setCoordinate(location4);
        em.setFirst_name("test");
        em.setLast_name("许莽林");
        em.setId(1l);

        Employee em1 = new Employee();
        em1.setAbout("this is love story");
        em1.setAddress("things address is  chengdu");
        em1.setAge(28);
        em1.setCoordinate(location4);
        em1.setFirst_name("许莽林");
        em1.setLast_name("test");
        em1.setId(2l);
        List<Employee> list = new ArrayList<Employee>();
        elasticsearchSaveVo.setData(em1);
        ;
        searchService.save(elasticsearchSaveVo);

        elasticsearchSaveVo.setData(em);
        ;
        searchService.save(elasticsearchSaveVo);
    }


    @RequestMapping(value = "singleTitle", method = RequestMethod.GET)
    private Object singleTitle(@PageableDefault Pageable pageable) {
        ElasticsearchQueryVo queryVo = new ElasticsearchQueryVo();
        Employee em1 = new Employee();
        queryVo.setContext("许莽林");
        queryVo.setPageable(pageable);
        queryVo.setData(em1);
        return searchService.queryStringQuery(queryVo);
    }


    @RequestMapping(value = "singleMatch", method = RequestMethod.GET)
    private Object singleMatch() {
        ElasticsearchQueryVo queryVo = new ElasticsearchQueryVo();
        return searchService.matchQuery(queryVo);
    }

    @RequestMapping(value = "multiMatchQuery", method = RequestMethod.GET)
    private Object multiMatchQuery() {
        ElasticsearchQueryVo queryVo = new ElasticsearchQueryVo();
        Employee em1 = new Employee();
        queryVo.setContext("许莽林");
        queryVo.setData(em1);
        Map<String, Float> map = new HashMap<String, Float>();
        map.put("first_name", 0.5f);
        map.put("last_name", 0.5f);
        queryVo.setFields(map);
        Pageable pageable = PageRequest.of(0, 20);
        queryVo.setPageable(pageable);
        return searchService.multiMatchQuery(queryVo);
    }

}
