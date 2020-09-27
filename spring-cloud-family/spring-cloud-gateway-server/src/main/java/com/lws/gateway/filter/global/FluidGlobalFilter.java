package com.lws.gateway.filter.global;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 全局限流过滤器
 * <p>
 * 每分钟只能访问5次服务
 * 实现GlobalFilter接口，和Ordered接口即可。
 */
@Slf4j
@Component
public class FluidGlobalFilter implements GlobalFilter, Ordered {

    int capacity = 5;//桶的最大容量，即能装载 Token 的最大数量
    int refillTokens = 1; //每次 Token 补充量
    Duration duration = Duration.ofSeconds(1); //补充 Token 的时间间隔
    private static final Map<String, Bucket> BUCKET_CACHE = new ConcurrentHashMap<>();

    private Bucket createNewBucket() {
        Refill refill = Refill.greedy(refillTokens, duration);
        Bandwidth limit = Bandwidth.classic(capacity, refill);
        return Bucket4j.builder().addLimit(limit).build();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String ip = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        Bucket bucket = BUCKET_CACHE.computeIfAbsent(ip, k -> createNewBucket());
        log.info("IP: " + ip + "，has Tokens: " + bucket.getAvailableTokens());
        if (bucket.tryConsume(1)) {
            return chain.filter(exchange);
        } else {
            exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
            return exchange.getResponse().setComplete();
        }
    }

    @Override
    public int getOrder() {
        return -2;
    }
}