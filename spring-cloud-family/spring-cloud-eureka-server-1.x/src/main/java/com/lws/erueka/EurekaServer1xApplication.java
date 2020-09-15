package com.lws.erueka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@Slf4j
@EnableEurekaServer
@SpringBootApplication
public class EurekaServer1xApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer1xApplication.class, args);
        log.info("Eureka 启动成功");
    }
}
