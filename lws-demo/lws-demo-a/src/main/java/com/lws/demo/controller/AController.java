package com.lws.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/a")
@RestController
public class AController {

    @Value("{server.prot}")
    private String port;

    @GetMapping("/test1")
    public String test1() {
        return "我是服务A，端口是" + port;
    }
}
