package com.lws.demo.provider.service.impl;

import com.lws.demo.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

@Slf4j
@DubboService(version = "1.0.0")
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String val) {
        log.info(val + "访问provider");
        return val + "进入provider";
    }
}
