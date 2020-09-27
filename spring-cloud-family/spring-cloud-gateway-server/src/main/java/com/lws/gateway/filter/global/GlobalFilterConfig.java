package com.lws.gateway.filter.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

/**
 * 统计接口调用时间
 * <p>
 * 全局过滤器，使用配置类形式，直接构造bean，使用注解完成Ordered接口功能
 */
@Slf4j
@Configuration
public class GlobalFilterConfig {

    @Bean
    @Order(-100)
    public GlobalFilter elapsedGlobalFilter() {
        return (exchange, chain) -> {
            //调用请求之前统计时间   pre filter
            Long startTime = System.currentTimeMillis();
            return chain.filter(exchange).then().then(Mono.fromRunnable(() -> {
                //调用请求之后统计时间   post filter
                Long endTime = System.currentTimeMillis();
                log.debug(exchange.getRequest().getURI().getRawPath() + ", cost time : " + (endTime - startTime) + "ms");
            }));
        };
    }
}