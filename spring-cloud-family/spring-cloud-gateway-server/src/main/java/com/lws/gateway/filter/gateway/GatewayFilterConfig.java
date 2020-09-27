package com.lws.gateway.filter.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

/**
 * 局部过滤器
 */
@Slf4j
@Configuration
public class GatewayFilterConfig {

    @Bean
    @Order(-100)
    public GatewayFilter elapsedGatewayFilter() {
        return (exchange, chain) -> {
            //调用请求之前统计时间   pre filter
            Long startTime = System.currentTimeMillis();
            return chain.filter(exchange).then().then(Mono.fromRunnable(() -> {
                //调用请求之后统计时间   post filter
                Long endTime = System.currentTimeMillis();
                log.info(exchange.getRequest().getURI().getRawPath() + ", cost time : " + (endTime - startTime) + "ms");
            }));
        };
    }
}
