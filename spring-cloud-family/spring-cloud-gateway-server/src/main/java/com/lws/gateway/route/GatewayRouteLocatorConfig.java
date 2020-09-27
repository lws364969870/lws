package com.lws.gateway.route;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 路由配置
 */
@Configuration
public class GatewayRouteLocatorConfig {

    @Resource
    private GatewayFilter elapsedGatewayFilter;

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/abc/**")
                        .filters(f -> f.stripPrefix(1)
                                .filter(elapsedGatewayFilter))
                        .uri("lb://lws-demo-a")
                        .order(0)
                        .id("route-demo-3")
                )
                .build();
    }
}
