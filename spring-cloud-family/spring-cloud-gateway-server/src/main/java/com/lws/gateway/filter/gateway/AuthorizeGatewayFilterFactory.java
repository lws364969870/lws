package com.lws.gateway.filter.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthorizeGatewayFilterFactory extends AbstractGatewayFilterFactory {

    @Override
    public GatewayFilter apply(Object object) {
        return (exchange, chain) -> {
            log.info("auths");
            return chain.filter(exchange);
        };
    }

    /**
     * 这个name方法 用来在yml配置中指定对应的过滤器名称
     *
     * @return
     */
    @Override
    public String name() {
        return "auths";
    }
}
