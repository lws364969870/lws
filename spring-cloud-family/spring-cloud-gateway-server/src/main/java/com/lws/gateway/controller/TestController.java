package com.lws.gateway.controller;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;

@RestController
public class TestController {

    @Resource
    private RedisTemplate redisTemplate;

    @GetMapping("/hello")
    public String heelo() {
        return "hello";
    }


    @GetMapping("/add")
    public void add() {
        redisTemplate.opsForHash().put("aa:aa", "bb:bb", "hello");
        redisTemplate.opsForHash().put("aa:aa", "cc:cc", "hello");
    }

    @GetMapping("/find")
    public void find() {
        redisTemplate.opsForHash().put("aa:aa", "bb:bb", "hello");
        redisTemplate.opsForHash().put("aa:aa", "cc:cc", "hello");
        ScanOptions scanOptions = ScanOptions.scanOptions().match("cc:*").build();
        Cursor<Map.Entry<String, String>> result = redisTemplate.opsForHash().scan("aa:aa", scanOptions);
        if(result.hasNext()){
            Map.Entry<String, String> s =   result.next();
            System.out.println(s.getKey());
        }
    }

}
