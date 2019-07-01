/**
 * 描述:
 * 包名:com.lvmoney.router.controller
 * 版本信息: 版本1.0
 * 日期:2018年12月29日  下午5:02:46
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.mongo.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.lvmoney.mongo.mo.MongodbTestMo;
import com.lvmoney.mongo.service.BaseGridFsService;
import com.lvmoney.mongo.service.BaseMongoService;
import com.lvmoney.mongo.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年12月29日 下午5:02:46
 */
//@RestController
//@RequestMapping("mongo")
public class MongoController {
    @Autowired
    BaseMongoService baseMongoService;

    @Autowired
    BaseGridFsService baseGridFsService;
    @Value("${file.size.max:1000000}")
    String fileMaxSize;

    @RequestMapping(value = "save")
    public void save() {
        MongodbTestMo mo = new MongodbTestMo();
        mo.setMid("123");
        mo.setName("MongodbTestModel");
        mo.setAge("22");
        BaseMongoVo bVo = new BaseMongoVo();
        bVo.setData(mo);
        baseMongoService.save(bVo);
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public BaseGridFsOutVo uploadFile(@RequestParam("file") MultipartFile multiportFile) throws Exception {
        BaseGridFsVo grd = new BaseGridFsVo();
        DBObject metaData = new BasicDBObject();
        metaData.put("createdDate", new Date());
        grd.setFile(multiportFile);
        grd.setDbObj(metaData);
        grd.setMaxSize(Long.parseLong(fileMaxSize));
        return baseGridFsService.save(grd);
    }

    @RequestMapping(value = "/downloadFile")
    @ResponseBody
    public void downLoadFile(@RequestParam(name = "file_id") String fileId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BaseGridFsQueryVo baseGridFsQueryVo = new BaseGridFsQueryVo();
        baseGridFsQueryVo.setMongoFileId(fileId);
        BaseGridFsByteOutVo baseGridFsByteOutVo = baseGridFsService.getByMongoId(baseGridFsQueryVo);
        String fileName = baseGridFsByteOutVo.getFileName();
        if (request.getHeader("User-Agent").toUpperCase().contains("MSIE") ||
                request.getHeader("User-Agent").toUpperCase().contains("TRIDENT")
                || request.getHeader("User-Agent").toUpperCase().contains("EDGE")) {
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        } else {
            //非IE浏览器的处理：
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }
        // 通知浏览器进行文件下载

        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
        response.getOutputStream().write(baseGridFsByteOutVo.getFileByte());
    }


}
