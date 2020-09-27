package com.lws.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/b")
@RestController
public class BController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/test")
    public String test1() {
        return "我是服务B，端口是" + port;
    }
}
