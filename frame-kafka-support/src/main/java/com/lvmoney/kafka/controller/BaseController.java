package com.lvmoney.kafka.controller;


import com.lvmoney.kafka.provider.SimpleSender;
import com.lvmoney.kafka.provider.SynchronousSender;
import com.lvmoney.kafka.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
@RequestMapping("/send")
public class BaseController {
    @Autowired
    SimpleSender simpleSender;
    @Autowired
    SynchronousSender synchronousSender;

    @RequestMapping(value = "/simple", method = RequestMethod.GET)
    public void WarnInfo() throws Exception {
        int count = 10;
        for (int i = 0; i < count; i++) {
            MessageVo messageVo = new MessageVo();
            messageVo.setMsgType("simple");
            Date date = new Date();
            Long dateTime = date.getTime();
            messageVo.setDate(dateTime);
            simpleSender.send(messageVo);
        }
    }

    @RequestMapping(value = "/synchronous", method = RequestMethod.GET)
    public void synchronous() throws Exception {
        int count = 10;
        for (int i = 0; i < count; i++) {
            MessageVo messageVo = new MessageVo();
            messageVo.setMsgType("simple");
            Date date = new Date();
            Long dateTime = date.getTime();
            messageVo.setDate(dateTime);
            synchronousSender.send(messageVo);
        }
    }

}

