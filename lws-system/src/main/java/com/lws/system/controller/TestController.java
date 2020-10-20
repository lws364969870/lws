package com.lws.system.controller;


import com.lws.system.entity.System;
import com.lws.system.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author WilsonLee
 * @since 2020-10-18
 */
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/findAll")
    public List<System> find() {
        return testService.findAll();
    }

    @GetMapping("/find")
    public System find(@RequestParam("systemCode") String systemCode) {
        return testService.find(systemCode);
    }

}
