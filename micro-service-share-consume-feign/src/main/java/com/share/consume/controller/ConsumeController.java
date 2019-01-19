package com.share.consume.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.share.consume.entity.User;
import com.share.consume.service.ConsumeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class ConsumeController {

    @Autowired
    private ConsumeService consumeService;

    @HystrixCollapser(batchMethod = "findAll",collapserProperties = {
            @HystrixProperty(name = "timerDelayInMilliseconds",value = "100")
    })
    @RequestMapping("/get/{userId}")
    public User get(@PathVariable("userId") Integer userId){
        return consumeService.get(userId);
    }

    @RequestMapping("/findAll")
    @HystrixCommand
    public List<User> findAll(List<Integer> userIdList){
        return consumeService.findAll(StringUtils.join(userIdList,","));
    }


}
