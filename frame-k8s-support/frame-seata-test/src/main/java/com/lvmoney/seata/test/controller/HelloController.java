package com.lvmoney.seata.test.controller;

import com.lvmoney.seata.test.po.User;
import com.lvmoney.seata.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    UserService userService;

    @RequestMapping("/test")
    public String printDate() {
        return "this is v1";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public int save(User user) {
        return userService.insertUser(user);
    }

    @RequestMapping(value = "/updateStage/{id}", method = RequestMethod.PUT)
    public int update(@PathVariable(value = "id") String id) {
        return userService.updateStage(id);
    }


}