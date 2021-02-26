package com.lws.demo.consumer.controller;

import com.lws.demo.service.HelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @DubboReference(version = "1.0.0")
    private HelloService helloService;

    @GetMapping("/hello")
    public String hello(@RequestParam("val") String val) {
        return helloService.hello(val);
    }
}
