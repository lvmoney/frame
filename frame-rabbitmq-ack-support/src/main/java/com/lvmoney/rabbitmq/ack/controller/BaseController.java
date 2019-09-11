package com.lvmoney.rabbitmq.ack.controller;


import com.lvmoney.rabbitmq.ack.provider.SimpleSender;
import com.lvmoney.rabbitmq.ack.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@RestController
@RequestMapping("/send")
public class BaseController {
    @Autowired
    private SimpleSender simpleSender;

    @RequestMapping(value = "one2one", method = RequestMethod.GET)
    public void one2one() {
        MessageVo messageVo = new MessageVo();
        Date date = new Date();
        long dateTime = date.getTime();
        messageVo.setDate(dateTime);
        messageVo.setMsgType("simple");
        simpleSender.send(messageVo);
    }
}

